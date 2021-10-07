package org.jeecg.modules.eth_hub.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.eth_hub.service.IPlatformConfigService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PowerSettleJob implements Job {

    @Autowired
    private IPlatformConfigService configService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- {} 任务调度开始 --- {} ", this.getClass().getName(), DateUtils.now());

        /**
         * 整体结算逻辑：
         * 1-将ether_worker_online矿机在线表按照 账户-每日-用电量 整理到app_member_power用户电力消耗表
         * 2-将整理过的ether_worker_online记录更新为已结算
         * 3-按周期生成电费账单app_member_bill
         */
        String powerPrice = configService.getByConfigKey("power_price");

        log.info(" --- {} 任务执行完毕 --- {} ", this.getClass().getName(), DateUtils.now());
    }
}
