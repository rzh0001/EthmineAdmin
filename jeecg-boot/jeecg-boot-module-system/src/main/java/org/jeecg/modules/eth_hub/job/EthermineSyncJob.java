package org.jeecg.modules.eth_hub.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
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

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- {} 任务调度开始 --- {} ", this.getClass().getName(), DateUtils.now());

        List<EtherMiner> miners = minerService.list();
        miners.forEach(miner -> {
            persistService.persistMiner(miner);
        });

        log.info(" --- {} 任务执行完毕 --- {} ", this.getClass().getName(), DateUtils.now());
    }

}
