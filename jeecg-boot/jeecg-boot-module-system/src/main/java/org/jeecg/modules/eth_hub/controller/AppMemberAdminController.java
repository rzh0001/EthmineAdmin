package org.jeecg.modules.eth_hub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberBillService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
        Page<AppMember> page = new Page<AppMember>(pageNo, pageSize);
        IPage<AppMember> pageList = appMemberService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 手工调账
     *
     * @param params 参数
     * @return
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
}

