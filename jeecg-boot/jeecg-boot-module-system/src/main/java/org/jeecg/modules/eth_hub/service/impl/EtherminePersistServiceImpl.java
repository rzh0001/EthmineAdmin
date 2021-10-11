package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.eth_hub.dao.EtherPayoutRepository;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.demo.eth_hub.service.IEtherPayoutService;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerService;
import org.jeecg.modules.eth_hub.dao.EtherWorkerRepository;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;
import org.jeecg.modules.eth_hub.entity.Payout;
import org.jeecg.modules.eth_hub.service.EthermineApi;
import org.jeecg.modules.eth_hub.service.EtherminePersistService;
import org.jeecg.modules.ruan.RuanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EtherminePersistServiceImpl implements EtherminePersistService {
    @Autowired
    private EthermineApi api;

    @Autowired
    private IEtherMinerService minerService;

    @Autowired
    private IEtherWorkerService workerService;
    @Autowired
    private EtherWorkerRepository workerDao;

    @Override
    public void persistMiner(EtherMiner miner) {
        log.info("persist miner start [{}]:[{}]", miner.getMinerName(), miner.getMinerAddress());
        MinerDashboard res = api.minerDashboard(miner.getMinerAddress());

        if (!"OK".equals(res.getStatus())) {
            log.error("ethermine api error, please check");
            return;
        }

        MinerDashboard.DataDTO.CurrentStatisticsDTO current = res.getData().getCurrentStatistics();
        if (BeanUtil.isEmpty(current)) {
            log.error("CurrentStatistics 无数据，请检查该地址是否有机器上线？");
            return;
        }

        log.info("unpaid: {}", current.getUnpaid());

        BeanUtil.copyProperties(current, miner);
        // 刚提现走时，矿池API不返回unpaid字段
        String unpaid = current.getUnpaid() == null ? "0" : current.getUnpaid();

        miner.setUnpaid(RuanTool.convertEtherAmount(new BigDecimal(unpaid)));
        miner.setCurrentHashrate(current.getCurrentHashrate() / 1000000);
        miner.setReportedHashrate(current.getReportedHashrate() / 1000000);
        miner.setTime(RuanTool.convertTime(current.getTime()));
        miner.setLastSeen(RuanTool.convertTime(current.getLastSeen()));


        res.getData().getWorkers().forEach(value -> workerService.saveWorker(miner, value));

        // 当矿机离线超过一定时长，接口中的数据将不再有此矿机的数据，将这些矿机的状态改为无数据
        workerService.updateInactiveWorkers(miner);

        // 更新矿机总数、在线矿机数
        Integer activeWorkers = workerDao.countByMinerIdAndOnlineStatus(miner.getId(), 1);
        Integer workers = workerDao.countAllByMinerId(miner.getId());
        miner.setWorkers(workers);
        miner.setActiveWorkers(activeWorkers);
        minerService.saveOrUpdate(miner);

        log.info("persist miner end [{}]:[{}]", miner.getMinerName(), miner.getMinerAddress());

    }

    @Override
    public void persistWorker(EtherMiner miner) {

    }

    @Autowired

    private EtherPayoutRepository payoutDao;

    @Autowired
    private IEtherPayoutService payoutService;

    @Override
    public void persistPayout(EtherMiner miner) {
        log.info("persist miner payout start [{}]:[{}]", miner.getMinerName(), miner.getMinerAddress());
        Payout data = api.minerPayouts(miner.getMinerAddress());

        // 更新预测数据 estimates
        Payout.DataDTO.EstimatesDTO estimates = data.getData().getEstimates();
        if (BeanUtil.isNotEmpty(estimates)) {
            miner.setAverageHashrate(estimates.getAverageHashrate() / 1000000);
            miner.setCoinsPerDay(estimates.getCoinsPerMin() * 60 * 24);
            miner.setUsdPerDay(estimates.getUsdPerMin() * 60 * 24);
            miner.setBtcPerDay(estimates.getBtcPerMin() * 60 * 24);
            minerService.updateById(miner);
        }

        // 同步支付账单
        List<EtherPayout> list = new ArrayList<>();
        List<Payout.DataDTO.PayoutsDTO> payouts = data.getData().getPayouts();
        if (BeanUtil.isEmpty(payouts)) {
            log.info("persist miner payout end [{}]:[{}] 无账单，跳过", miner.getMinerName(), miner.getMinerAddress());
            return;
        }
        for (Payout.DataDTO.PayoutsDTO unit : payouts) {
            log.info("data = {}", unit.getAmount());
            // check
            // 生成唯一标识
            String payoutNo = miner.getId() + "-" + unit.getTxHash() + "-" + unit.getPaidOn();
            EtherPayout byPayoutNo = payoutDao.findByPayoutNo(payoutNo);

            if (BeanUtil.isNotEmpty(byPayoutNo)) {
                log.info("该记录已存储，跳过[{}]", payoutNo);
                continue;
            }

            EtherPayout payout = new EtherPayout();
            BeanUtil.copyProperties(unit, payout);
            payout.setMinerId(miner.getId());
            payout.setMinerName(miner.getMinerName());
            payout.setMinerAddress(miner.getMinerAddress());
            payout.setPayoutNo(payoutNo);
            payout.setAmount(RuanTool.convertEtherAmount(new BigDecimal(unit.getAmount())));
            if (unit.getTxCost() != null) {
                payout.setTxCost(RuanTool.convertEtherAmount(BigDecimal.valueOf(unit.getTxCost())));
            }
            payout.setPaidOn(RuanTool.convertTime(unit.getPaidOn()));
            payout.setSettleStatus(0);
            log.info("payout = {}", payout.getAmount());

            list.add(payout);

        }

        payoutService.saveOrUpdateBatch(list);
        log.info("persist miner payout end [{}]:[{}] 同步完成", miner.getMinerName(), miner.getMinerAddress());
    }
}
