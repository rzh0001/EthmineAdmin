package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;

import java.math.BigDecimal;

/**
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IAppMemberWalletService extends IService<AppMemberWallet> {


    IPage<AppMemberWallet> page(Page<AppMemberWallet> page, QueryWrapper<AppMemberWallet> queryWrapper);

    void newWallet(AppMember member, String currency);


    /**
     * 收入
     *
     * @param walletId 会员钱包ID
     * @param amount   金额
     */
    void income(String walletId, BigDecimal amount);

    /**
     * 支出
     *
     * @param walletId 会员钱包ID
     * @param amount   金额
     */
    void payout(String walletId, BigDecimal amount);

}
