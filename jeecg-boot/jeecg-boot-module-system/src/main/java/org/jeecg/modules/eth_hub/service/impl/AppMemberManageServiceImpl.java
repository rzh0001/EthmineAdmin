package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.jeecg.modules.eth_hub.entity.AppUser;
import org.jeecg.modules.eth_hub.service.AppMemberManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMemberManageServiceImpl implements AppMemberManageService {
    @Autowired
    private IAppMemberService memberService;

    @Autowired
    private AppMemberRepository dao;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void register(AppUser register) {

        // check email
        AppMember emailUser = dao.findByEmail(register.getEmail());
        Assert.isNull(emailUser);

        //check invite code
        AppMember inviteUser = dao.findByInviteCode(register.getInviteBy());
        if (BeanUtil.isEmpty(inviteUser)) {
            throw new JeecgBootException("wrong invite code");
        }
        // che

        AppMember newMember = new AppMember();
        BeanUtil.copyProperties(register, newMember);
        newMember.setUsername(register.getEmail());
        newMember.setStatus(0); // 未激活

        memberService.save(newMember);


    }

    @Override
    public String login(AppUser user) {
        // find user
        AppMember member = dao.findByEmail(user.getEmail());
        if (BeanUtil.isEmpty(member)) {
            throw new JeecgBootException("account or password is wrong");
        }
        // check password
        if (StrUtil.compare(user.getPassword(), member.getPassword(), false) != 0) {
            throw new JeecgBootException("account or password is wrong");
        }

        // gen token
        String token = JwtUtil.sign(user.getEmail(), user.getPassword());
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        return token;
    }

    @Override
    public void logout(AppUser user) {

    }


}
