package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberBill;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;

import java.math.BigDecimal;

/**
 * @Description: app_member_bill
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
public interface IAppMemberBillService extends IService<AppMemberBill> {

    /**
     * 结算矿工收益
     *
     * @param member 会员
     * @param payout 矿工收益账单
     */
    void settleEthermineBill(AppMember member, EtherPayout payout);

    /**
     * 会员提现
     *
     * @param member 会员
     * @param amount 提现金额
     */
    void cashoutWalletBalanceBill(AppMember member, BigDecimal amount);

}
