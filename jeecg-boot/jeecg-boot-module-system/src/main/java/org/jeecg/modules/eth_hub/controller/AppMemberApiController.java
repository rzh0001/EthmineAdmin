package org.jeecg.modules.eth_hub.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.eth_hub.entity.AppMemberMiningData;
import org.jeecg.modules.eth_hub.entity.AppUser;
import org.jeecg.modules.eth_hub.service.AppMemberApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/app/member")
public class AppMemberApiController {

    @Autowired
    private AppMemberApiService api;

    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody @Valid AppUser member) {
        log.info("member register:{}", member);
        api.register(member);

        return Result.OK();
    }

    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody @Valid AppUser member) {
        log.info("member register:{}", member);
        String token = api.login(member);
        Map map = new HashMap();
        map.put("accessToken", token);
        return Result.OK(map);
    }

    @GetMapping(value = "/miningData/{username}")
    public Result<?> miningDate(@PathVariable String username) {
        AppMemberMiningData data = api.mingData(username);
        return Result.OK(data);

    }
}
