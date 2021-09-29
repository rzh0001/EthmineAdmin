package org.jeecg.modules.eth_hub.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.eth_hub.entity.AppUser;
import org.jeecg.modules.eth_hub.service.AppMemberManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/app/member")
public class AppMemberManageController {

    @Autowired
    private AppMemberManageService memberManageService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody @Valid AppUser member) {
        log.info("member register:{}", member);
        memberManageService.register(member);

        return Result.OK();
    }

    @PostMapping(value = "login")
    public Result login(@RequestBody @Valid AppUser member) {
        log.info("member register:{}", member);
        String token = memberManageService.login(member);
        Map map = new HashMap();
        map.put("access_token", token);
        return Result.OK(map);
    }
}
