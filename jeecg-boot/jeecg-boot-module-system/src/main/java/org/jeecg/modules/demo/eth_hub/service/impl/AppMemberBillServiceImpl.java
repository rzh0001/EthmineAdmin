package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberWalletRepository;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberBill;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.mapper.AppMemberBillMapper;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberBillService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.jeecg.modules.ruan.RuanTool;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * @Description: app_member_bill
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
@Service
public class AppMemberBillServiceImpl extends ServiceImpl<AppMemberBillMapper, AppMemberBill> implements IAppMemberBillService {

    @Autowired
    private IAppMemberWalletService walletService;

    @Autowired
    private AppMemberWalletRepository walletDao;

    @Transactional
    @Override
    public void settleEthermineBill(AppMember member, EtherPayout payout) {

        Optional<AppMemberWallet> wallet = walletDao.findByMemberUsernameAndCurrency(member.getUsername(), "ETH");
        if (!wallet.isPresent()) {
            throw new JeecgBootException("Member Wallet has something wrong. member:" + member.getUsername());
        }


        AppMemberBill bill = newBill(member);

        // 计算会员收益
        BigDecimal rate = BigDecimal.ONE.subtract(member.getChargeRate());
        BigDecimal amount = payout.getAmount().multiply(rate).setScale(4, RoundingMode.DOWN);
        bill.setAmount(amount);
        bill.setCharge(payout.getAmount().subtract(amount));
        bill.setBalance(wallet.get().getBalance());
        bill.setCurrency("ETH");
        bill.setType("SETTLE");
        bill.setDetailId(payout.getId());
        bill.setDetail(RuanTool.concat("结算{}的入账", payout.getPaidOn()));
        bill.setSettleDate(DateUtil.today());

        save(bill);

        walletService.income(wallet.get().getId(), amount);

    }

    @NotNull
    private AppMemberBill newBill(AppMember member) {
        AppMemberBill bill = new AppMemberBill();
        bill.setMemberId(member.getId());
        bill.setMemberUsername(member.getUsername());
        bill.setMemberNickname(member.getNickname());
        return bill;
    }

    @Transactional
    @Override
    public void cashoutWalletBalanceBill(AppMember member, BigDecimal amount) {

        Optional<AppMemberWallet> wallet = walletDao.findByMemberUsernameAndCurrency(member.getUsername(), "ETH");
        if (!wallet.isPresent()) {
            throw new JeecgBootException("Member Wallet has something wrong. member:" + member.getUsername());
        }

//        if (RuanTool.compare(amount, wallet.get().getBalance())) {
//            throw new JeecgBootException("提现金额大于钱包余额");
//        }


        AppMemberBill bill = newBill(member);
        bill.setAmount(amount.negate());
        bill.setBalance(wallet.get().getBalance());
        bill.setCurrency("ETH");
        bill.setType("CASHOUT");

        save(bill);

        walletService.payout(wallet.get().getId(), amount);


    }
}
