package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberBillRepository;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberWalletRepository;
import org.jeecg.modules.demo.eth_hub.dao.EtherMinerRepository;
import org.jeecg.modules.demo.eth_hub.dao.EtherPayoutRepository;
import org.jeecg.modules.demo.eth_hub.entity.*;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.jeecg.modules.eth_hub.dao.EtherWorkerRepository;
import org.jeecg.modules.eth_hub.entity.AppMemberBillData;
import org.jeecg.modules.eth_hub.entity.AppMemberMiningData;
import org.jeecg.modules.eth_hub.entity.AppUser;
import org.jeecg.modules.eth_hub.service.AppMemberApiService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AppMemberApiServiceImpl implements AppMemberApiService {
    @Autowired
    private IAppMemberService memberService;

    @Autowired
    private AppMemberRepository dao;

    @Autowired
    private EtherMinerRepository minerDao;

    @Autowired
    private AppMemberWalletRepository walletDao;


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IAppMemberWalletService walletService;

    @Autowired
    private EtherWorkerRepository workerDao;

    @Autowired
    private AppMemberBillRepository billDao;
    @Autowired
    private EtherPayoutRepository payoutDao;


    @Override
    public void register(AppUser register) {

        // check email
        AppMember emailUser = dao.findByEmail(register.getEmail());
        Assert.isNull(emailUser);

        //check invite code
        AppMember inviteUser = dao.findByInviteCode(register.getInviteBy());
        if (BeanUtil.isEmpty(inviteUser)) {
            throw new JeecgBootException("wrong invite code");
        }
        // che

        AppMember newMember = new AppMember();
        BeanUtil.copyProperties(register, newMember);
        newMember.setUsername(register.getEmail());
        newMember.setStatus(0); // 未激活

        memberService.save(newMember);

        // wallet
//        AppMemberWallet wallet = new AppMemberWallet();
//        wallet.setMemberId(newMember.get)


    }

    @Override
    public String login(AppUser user) {
        // find user
        AppMember member = dao.findByEmail(user.getEmail());
        if (BeanUtil.isEmpty(member)) {
            throw new JeecgBootException("account or password is wrong");
        }
        // check password
        if (StrUtil.compare(user.getPassword(), member.getPassword(), false) != 0) {
            throw new JeecgBootException("account or password is wrong");
        }

        // gen token
        String token = JwtUtil.sign(user.getEmail(), user.getPassword());
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        return token;
    }

    @Override
    public void logout(AppUser user) {

    }

    @Override
    public AppMemberMiningData miningData(String username) {
        AppMember member = getAppMemberByUsername(username);

        AppMemberMiningData data = new AppMemberMiningData();

        EtherMiner miner = minerDao.findByMemberUsername(username);
        BeanUtil.copyProperties(miner, data);

        // 系统通过定时任务处理账单，矿池支付后待入账金额变成0,在账单结算前，待入账金额应加上为结算账单的金额
        Optional<List<EtherPayout>> unSettle = payoutDao.findAllBySettleStatusAndMinerId(0, miner.getId());
        BigDecimal unSettleAmount = BigDecimal.ZERO;
        if (unSettle.isPresent()) {
            for (EtherPayout payout : unSettle.get()) {
                unSettleAmount = unSettleAmount.add(payout.getAmount());
            }
        }

        // 折扣率
        BigDecimal rate = BigDecimal.ONE.subtract(member.getChargeRate());
        // 矿池待入账收益 + 系统未结算收益
        BigDecimal resultAmount = miner.getUnpaid().add(unSettleAmount);
        // 会员打折后真实收益

        int scale = 4;
        data.setUnpaid(resultAmount.multiply(rate).setScale(scale, RoundingMode.DOWN));

        Optional<AppMemberWallet> wallet = walletDao.findByMemberUsernameAndCurrency(username, "ETH");
        if (!wallet.isPresent()) {
            throw new JeecgBootException("Member Wallet has something wrong");
        }
        data.setBalance(wallet.get().getBalance().setScale(scale, RoundingMode.DOWN));
        data.setTotalEarnings(wallet.get().getTotalEarnings().setScale(scale, RoundingMode.DOWN));

        // 算力统一打折
        data.setCurrentHashrate(BigDecimal.valueOf(miner.getCurrentHashrate() / 1000 * 0.975).setScale(2, RoundingMode.DOWN).doubleValue());
        data.setReportedHashrate(BigDecimal.valueOf(miner.getReportedHashrate() / 1000 * 0.975).setScale(2, RoundingMode.DOWN).doubleValue());

        //TODO 会员矿机总数
        Integer workers = workerDao.countAllByMinerId(miner.getId());
        Integer activeWorkers = workerDao.countByMinerIdAndOnlineStatus(miner.getId(), 1);

        data.setWorkers(workers);
        data.setActiveWorkers(activeWorkers); // 接口返回的在线矿机数是错误的
        data.setInactiveWorkers(workers - activeWorkers);

        return data;
    }

    @NotNull
    private AppMember getAppMemberByUsername(String username) {
        AppMember member = dao.findByUsername(username);
        if (BeanUtil.isEmpty(member)) {
            throw new JeecgBootException("用户不存在");
        }
        if (member.getStatus() != 1) {
            throw new JeecgBootException("用户未激活");
        }
        return member;
    }

    @Override
    public List<AppMemberBillData> bill(String username) {
        AppMember member = getAppMemberByUsername(username);
        Iterable<AppMemberBill> bills = billDao.findAllByMemberIdOrderByCreateTimeDesc(member.getId());
        List<AppMemberBillData> list = new ArrayList<>();
        bills.forEach(bill -> {
            AppMemberBillData data = new AppMemberBillData();
            BeanUtils.copyProperties(bill, data);
            data.setAmount(bill.getAmount().setScale(5, RoundingMode.DOWN));
            data.setBalance(bill.getBalance().setScale(5, RoundingMode.DOWN));
            list.add(data);
        });
        return list;
    }


}
