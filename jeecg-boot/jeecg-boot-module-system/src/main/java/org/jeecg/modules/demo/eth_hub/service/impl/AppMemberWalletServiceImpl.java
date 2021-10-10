package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberWalletRepository;
import org.jeecg.modules.demo.eth_hub.dao.EtherMinerRepository;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.mapper.AppMemberWalletMapper;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Slf4j
@Service
public class AppMemberWalletServiceImpl extends ServiceImpl<AppMemberWalletMapper, AppMemberWallet> implements IAppMemberWalletService {

    @Autowired
    private AppMemberWalletRepository dao;

    @Autowired
    private EtherMinerRepository minerDao;

    @Autowired
    private AppMemberRepository memberDao;

    @Override
    public IPage<AppMemberWallet> page(Page<AppMemberWallet> page, QueryWrapper<AppMemberWallet> queryWrapper) {
        Page<AppMemberWallet> walletPage = getBaseMapper().selectPage(page, queryWrapper);
        for (AppMemberWallet wallet : walletPage.getRecords()) {
            EtherMiner miner = minerDao.findByMemberUsername(wallet.getMemberUsername());
            if (BeanUtil.isEmpty(miner)) {
                continue;
            }
            AppMember member = memberDao.findByUsername(wallet.getMemberUsername());

            wallet.setUnpaid(miner.getUnpaid().multiply(BigDecimal.ONE.subtract(member.getChargeRate())));
        }
        return walletPage;
    }

    @Override
    public void newWallet(AppMember member, String currency) {

        // check
        Optional<AppMemberWallet> check = dao.findByMemberUsernameAndCurrency(member.getUsername(), currency);
        if (check.isPresent()) {
            log.info("该用户已有" + currency + "钱包账户,不再新开钱包");
            return;
        }

        AppMemberWallet wallet = new AppMemberWallet();
        wallet.setMemberId(member.getId());
        wallet.setMemberUsername(member.getUsername());
        wallet.setMemberNickname(member.getNickname());
        wallet.setCurrency(currency);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setTotalEarnings(BigDecimal.ZERO);
        save(wallet);
    }

    @Override
    public void income(String walletId, BigDecimal amount) {

        boolean result = baseMapper.income(walletId, amount);
        if (!result) {
            throw new JeecgBootException("更新余额失败！");
        }
    }

    @Override
    public void payout(String walletId, BigDecimal amount) {
        boolean result = baseMapper.payout(walletId, amount);
        if (!result) {
            throw new JeecgBootException("更新余额失败！");
        }
    }
}
