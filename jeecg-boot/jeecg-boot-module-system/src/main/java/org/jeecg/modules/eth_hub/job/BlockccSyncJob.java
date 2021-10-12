package org.jeecg.modules.eth_hub.job;

import org.jeecg.modules.demo.block_cc.service.BlockccApi;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class BlockccSyncJob implements Job {

    @Autowired
    private BlockccApi api;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        api.exchangeRate();
        api.price();

    }
}
