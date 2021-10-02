package org.jeecg.modules.eth_hub.job;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.eth_hub.dao.EtherPayoutRepository;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberBillService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.demo.eth_hub.service.IEtherMinerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class EthermineSettleJob implements Job {

    @Autowired
    private EtherPayoutRepository payoutDao;

    @Autowired
    private IEtherMinerService minerService;

    @Autowired
    private IAppMemberService memberService;

    @Autowired
    private IAppMemberBillService billService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- {} 任务调度开始 --- {} ", this.getClass().getName(), DateUtils.now());

        List<EtherMiner> miners = minerService.list();
        for (EtherMiner miner : miners) {

            log.info("miner[{}] start settle", miner.getMinerName());

            if (StrUtil.isEmpty(miner.getMemberUsername())) {
                log.info("该矿工[{}]未配置所属用户，暂不结算！", miner.getMinerName());
                continue;
            }

            AppMember member = memberService.getById(miner.getMemberId());
            if (null == member.getChargeRate()) {
                log.info("该用户[{}]未配置手续费率，暂不结算！", member.getUsername());
                continue;
            }

            List<EtherPayout> unSettle = payoutDao.findAllBySettleStatusAndMinerId(0, miner.getId());
            if (BeanUtil.isEmpty(unSettle)) {
                log.info("该用户[{}]无未结算转账！", member.getUsername());
                continue;
            }

            for (EtherPayout unit : unSettle) {

                billService.settleEthermineBill(member, unit);
                unit.setSettleStatus(2);
                unit.setSettleTime(DateUtil.date());
//                payoutDao.save(unit);
            }

            payoutDao.saveAll(unSettle);


        }

        log.info(" --- {} 任务执行完毕 --- {} ", this.getClass().getName(), DateUtils.now());
    }

}
