package org.jeecg.modules.eth_hub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherPayout;
import org.jeecg.modules.demo.eth_hub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/eth_hub/appMember")
public class AppMemberAdminController {

    @Autowired
    private IAppMemberService appMemberService;

    @Autowired
    private IAppMemberWalletService walletService;

    @Autowired
    private IAppMemberBillService billService;

    @Autowired
    private IEtherMinerService minerService;

    @Autowired
    private IEtherPayoutService payoutService;

    @GetMapping(value = "/getMultiMember")
    public List<AppMember> getMultiMember(AppMember member) {
        QueryWrapper<AppMember> queryWrapper = QueryGenerator.initQueryWrapper(member, null);
        List<AppMember> ls = appMemberService.list(queryWrapper);
        for (AppMember user : ls) {
            user.setPassword(null);
//            user.setSalt(null);
        }
        return ls;
    }

    //getActiveMembers
    @GetMapping(value = "/getActiveMembers")
    public Result<?> queryPageList(AppMember appMember,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AppMember> queryWrapper = QueryGenerator.initQueryWrapper(appMember, req.getParameterMap());
        queryWrapper.lambda().eq(AppMember::getStatus, 1);
        Page<AppMember> page = new Page<>(pageNo, pageSize);
        IPage<AppMember> pageList = appMemberService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 手工调账
     *
     * @param params 参数
     * @return 结果
     */
    @PostMapping(value = "/manualAdjust")
    public Result<?> manualAdjust(@RequestBody Map<String, String> params) {

        AppMember member = appMemberService.getById(params.get("memberId"));
        String type = params.get("type");
        if ("CASHOUT".equals(type)) {
            billService.cashoutWalletBalanceBill(member, new BigDecimal(params.get("amount")));
        } else {
            return Result.error("调账只支持提现");
        }

        return Result.OK();
    }

    @GetMapping(value = "/dashboard")
    public Result<?> dashboard() {

        // member
        Integer activeWorkers = 0;
        Integer inactiveWorkers = 0;
        Integer workers = 0;
        BigDecimal memberProfit = BigDecimal.ZERO;
        BigDecimal memberBalance = BigDecimal.ZERO;

        List<AppMemberWallet> wallets = walletService.list();
        for (AppMemberWallet wallet : wallets) {
            memberProfit = memberProfit.add(wallet.getTotalEarnings());
            memberBalance = memberBalance.add(wallet.getBalance());
        }

        // platform
        Double reportedHashrate = 0d;
        Double averageHashrate = 0d;
        BigDecimal totalEth = BigDecimal.ZERO;
        BigDecimal platformProfit;

        List<EtherMiner> miners = minerService.list();
        for (EtherMiner miner : miners) {
            reportedHashrate += miner.getReportedHashrate();
            averageHashrate += miner.getAverageHashrate();

            activeWorkers += miner.getActiveWorkers();
            workers += miner.getWorkers();
        }

        List<EtherPayout> payouts = payoutService.list();
        for (EtherPayout payout : payouts) {
            totalEth = totalEth.add(payout.getAmount());
        }
        // 特殊处理：总收益要加上星火结余，矿池支付表没有这部分数据
        totalEth = totalEth.add(new BigDecimal("191.6489"));
        platformProfit = totalEth.subtract(memberProfit);

        Map<String, Object> map = new HashMap<>();
        map.put("activeWorkers", activeWorkers);
        map.put("inactiveWorkers", inactiveWorkers);
        map.put("memberProfit", memberProfit);
        map.put("memberBalance", memberBalance);
        map.put("reportedHashrate", reportedHashrate / 1000); // M 折算成 G
        map.put("averageHashrate", averageHashrate / 1000);
        map.put("totalEth", totalEth);
        map.put("platformProfit", platformProfit.setScale(4, RoundingMode.DOWN));


        return Result.OK(map);
    }
}

