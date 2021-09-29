package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.eth_hub.entity.EtherWorker;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;
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

        log.info("unpaid: {}", res.getData().getCurrentStatistics().getUnpaid());

        BeanUtil.copyProperties(res.getData().getCurrentStatistics(), miner);
        miner.setTime(Timestamp.valueOf(LocalDateTimeUtil.of(res.getData().getCurrentStatistics().getTime() * 1000)));
        miner.setLastSeen(Timestamp.valueOf(LocalDateTimeUtil.of(res.getData().getCurrentStatistics().getLastSeen() * 1000)));
        miner.setUnpaid(BigDecimal.valueOf(res.getData().getCurrentStatistics().getUnpaid()));
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
