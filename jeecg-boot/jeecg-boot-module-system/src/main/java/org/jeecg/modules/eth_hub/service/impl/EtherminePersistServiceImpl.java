package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.eth_hub.dao.EtherPayoutRepository;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.demo.eth_hub.service.IEtherPayoutService;
import org.jeecg.modules.eth_hub.entity.EtherWorker;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;
import org.jeecg.modules.eth_hub.entity.Payout;
import org.jeecg.modules.eth_hub.service.EthermineApi;
import org.jeecg.modules.eth_hub.service.EtherminePersistService;
import org.jeecg.modules.eth_hub.service.IEtherWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @Override
    public void persistMiner(EtherMiner miner) {
        log.info("persist miner start [{}]:[{}]", miner.getMinerName(), miner.getMinerAddress());
        MinerDashboard res = api.minerDashboard(miner.getMinerAddress());

        if (!"OK".equals(res.getStatus())) {
            log.error("ethermine api error, please check");
            return;
        }

        MinerDashboard.DataDTO.CurrentStatisticsDTO current = res.getData().getCurrentStatistics();
        log.info("unpaid: {}", current.getUnpaid());

        BeanUtil.copyProperties(current, miner);
        miner.setUnpaid(new BigDecimal(current.getUnpaid()).divide(new BigDecimal("1000000000000000000")));
        miner.setCurrentHashrate(current.getCurrentHashrate() / 1000000);
        miner.setReportedHashrate(Double.valueOf(current.getReportedHashrate() / 1000000));
        miner.setTime(Timestamp.valueOf(LocalDateTimeUtil.of(Long.valueOf(current.getTime()) * 1000)));
        miner.setLastSeen(Timestamp.valueOf(LocalDateTimeUtil.of(Long.valueOf(current.getLastSeen()) * 1000)));

        minerService.saveOrUpdate(miner);


        List<EtherWorker> list = new ArrayList<>();
        res.getData().getWorkers().forEach(value -> {
            EtherWorker worker = new EtherWorker();
            BeanUtil.copyProperties(value, worker);
//            worker.setTime(Timestamp.valueOf(LocalDateTimeUtil.of(value.getTime() * 1000)));
//            worker.setLastSeen(Timestamp.valueOf(LocalDateTimeUtil.of(value.getLastSeen() * 1000)));
            worker.setWorkerName(value.getWorker());
            worker.setWorkerId(miner.getMinerAddress() + ":" + value.getWorker());
            worker.setMinerId(miner.getId());
            worker.setMinerAddress(miner.getMinerAddress());
            worker.setMinerName(miner.getMinerName());
            list.add(worker);
        });
        workerService.saveOrUpdateBatch(list);

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


        List<EtherPayout> list = new ArrayList<>();
        List<Payout.DataDTO.PayoutsDTO> payouts = data.getData().getPayouts();
        for (Payout.DataDTO.PayoutsDTO unit : payouts) {
            log.info("data = {}", unit.getAmount());
            // check
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
            payout.setAmount(new BigDecimal(unit.getAmount()).divide(new BigDecimal("1000000000000000000")));
            if (unit.getTxCost() != null) {
                payout.setTxCost(BigDecimal.valueOf(unit.getTxCost()).divide(new BigDecimal("1000000000000000000")));
            }
            payout.setPaidOn(Timestamp.valueOf(LocalDateTimeUtil.of(Long.valueOf(unit.getPaidOn()) * 1000)));
            payout.setSettleStatus(0);
            log.info("payout = {}", payout.getAmount());

            list.add(payout);

        }

        payoutService.saveOrUpdateBatch(list);
        log.info("persist miner payout end [{}]:[{}]", miner.getMinerName(), miner.getMinerAddress());
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.date().getTime());
        System.out.println(1632886800);
        System.out.println(1632892800);
        System.out.println(LocalDateTimeUtil.of(DateUtil.date().getTime()).toString());
        System.out.println(LocalDateTimeUtil.of(1632886800).toString());
        System.out.println(LocalDateTimeUtil.of(Long.valueOf("1632893287286")).toString());
        System.out.println(LocalDateTimeUtil.of(1632886800 * 1000).toString());
//        System.out.println(DateUtil.date(BigDecimal.valueOf(1632886800).plus(BigDecimal.valueOf(1000))));

//        Timestamp
    }
}
