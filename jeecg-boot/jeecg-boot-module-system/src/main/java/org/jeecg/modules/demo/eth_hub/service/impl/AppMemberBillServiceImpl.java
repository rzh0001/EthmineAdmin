package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.eth_hub.dao.AppMemberWalletRepository;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberBill;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.mapper.AppMemberBillMapper;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberBillService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.jeecg.modules.ruan.RuanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public void settleEthermineBill(AppMember member, EtherPayout payout) {

        AppMemberWallet wallet = walletDao.findByMemberUsernameAndCurrency(member.getUsername(), "ETH");


        BigDecimal rate = BigDecimal.ONE.subtract(member.getChargeRate());

        AppMemberBill bill = new AppMemberBill();
        BigDecimal amount = payout.getAmount().multiply(rate);
        bill.setAmount(amount);
        bill.setCharge(payout.getAmount().subtract(amount));
        bill.setBalance(wallet.getBalance());
        bill.setCurrency("ETH");
        bill.setType("SETTLE");
        bill.setDetailId(payout.getId());
        bill.setDetail(RuanTool.concat("结算{}的入账", payout.getPaidOn()));
        bill.setMemberId(member.getId());
        bill.setMemberUsername(member.getUsername());
        bill.setSettleDate(DateUtil.today());

        save(bill);

        walletService.income(member, "ETH", amount);

    }
}
