package org.jeecg.modules.eth_hub.job;

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
import org.jeecg.modules.demo.eth_hub.service.IEtherPayoutService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class EthermineSettleJob implements Job {

    @Autowired
    private EtherPayoutRepository payoutDao;

    @Autowired
    private IEtherMinerService minerService;

    @Autowired
    private IAppMemberService memberService;

    @Autowired
    private IAppMemberBillService billService;

    @Autowired
    private IEtherPayoutService payoutService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- {} 任务调度开始 --- {} ", this.getClass().getName(), DateUtils.now());

        settle();

        log.info(" --- {} 任务执行完毕 --- {} ", this.getClass().getName(), DateUtils.now());
    }

    public void settle() {
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

            Optional<List<EtherPayout>> unSettle = payoutDao.findAllBySettleStatusAndMinerId(0, miner.getId());
            if (!unSettle.isPresent()) {
                log.info("该用户[{}]无未结算转账！", member.getUsername());
                continue;
            }

            for (EtherPayout unit : unSettle.get()) {
                billService.settleEthermineBill(member, unit);
                unit.setSettleStatus(2);
                unit.setSettleTime(DateUtil.date());
            }

            payoutService.saveOrUpdateBatch(unSettle.get());
        }
    }

}
