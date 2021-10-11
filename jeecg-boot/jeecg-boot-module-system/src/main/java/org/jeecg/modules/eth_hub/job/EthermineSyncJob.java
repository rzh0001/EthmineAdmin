package org.jeecg.modules.eth_hub.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.eth_hub.dao.EtherPayoutRepository;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.jeecg.modules.eth_hub.service.EtherminePersistService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Ethermine Api 同步任务
 */

@Slf4j
public class EthermineSyncJob implements Job {

    @Autowired
    private EtherminePersistService persistService;

    @Autowired
    private IEtherMinerService minerService;

    @Autowired
    private EthermineSettleJob settleJob;

    @Autowired
    private EtherPayoutRepository payoutDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- {} 任务调度开始 --- {} ", this.getClass().getName(), DateUtils.now());

        List<EtherMiner> miners = minerService.list();
        miners.forEach(miner -> {
            persistService.persistMiner(miner);

            persistService.persistPayout(miner);
        });

        boolean result = payoutDao.existsBySettleStatus(0);
        log.info(" --- {} 检查是否有支付账单 ", this.getClass().getName());
        if (result) {
            log.info(" --- {} 有支付账单，开始执行结算任务 ", this.getClass().getName());
            settleJob.settle();
        }

        log.info(" --- {} 任务执行完毕 --- {} ", this.getClass().getName(), DateUtils.now());
    }

}
