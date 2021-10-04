package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberWalletRepository;
import org.jeecg.modules.demo.eth_hub.dao.EtherMinerRepository;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.jeecg.modules.eth_hub.entity.AppMemberMiningData;
import org.jeecg.modules.eth_hub.entity.AppUser;
import org.jeecg.modules.eth_hub.service.AppMemberApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        AppMember member = dao.findByUsername(username);
        if (BeanUtil.isEmpty(member)) {
            throw new JeecgBootException("用户不存在");
        }
        if (member.getStatus() != 1) {
            throw new JeecgBootException("用户未激活");
        }

        AppMemberMiningData data = new AppMemberMiningData();

        EtherMiner miner = minerDao.findByMemberUsername(username);
        BeanUtil.copyProperties(miner, data);
        data.setUnpaid(miner.getUnpaid().multiply(BigDecimal.ONE.subtract(member.getChargeRate())).setScale(5, RoundingMode.DOWN));

        AppMemberWallet wallet = walletDao.findByMemberUsernameAndCurrency(username, "ETH");
        data.setBalance(wallet.getBalance().setScale(5, RoundingMode.DOWN));
        data.setTotalEarnings(wallet.getTotalEarnings().setScale(5, RoundingMode.DOWN));


        // 算力统一打折
        data.setCurrentHashrate(new BigDecimal(miner.getCurrentHashrate() / 1000 * 0.975).setScale(2, RoundingMode.DOWN).doubleValue());
        data.setReportedHashrate(new BigDecimal(miner.getReportedHashrate() / 1000 * 0.975).setScale(2, RoundingMode.DOWN).doubleValue());

        //TODO 会员矿机总数
        data.setWorkers(data.getActiveWorkers());
        data.setInactiveWorkers(0);


        return data;
    }


}
