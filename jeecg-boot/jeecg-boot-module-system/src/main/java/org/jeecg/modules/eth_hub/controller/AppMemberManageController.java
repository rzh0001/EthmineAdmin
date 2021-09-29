package org.jeecg.modules.eth_hub.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.eth_hub.entity.AppMemberRegister;
import org.jeecg.modules.eth_hub.service.AppMemberManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/app/member")
public class AppMemberManageController {

    @Autowired
    private AppMemberManageService memberManageService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody @Valid AppMemberRegister member) {
        log.info("member register:{}", member);
        memberManageService.register(member);

        return Result.OK();
    }
}
