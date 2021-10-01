package org.jeecg.modules.eth_hub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/eth_hub/appMember")
public class AppMemberAdminController {

    @Autowired
    private IAppMemberService appMemberService;

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
}

