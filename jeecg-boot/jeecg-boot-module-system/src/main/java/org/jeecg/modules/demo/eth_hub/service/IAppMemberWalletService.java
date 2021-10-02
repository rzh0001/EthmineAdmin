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


    void income(AppMember member, String currency, BigDecimal amount);

}
