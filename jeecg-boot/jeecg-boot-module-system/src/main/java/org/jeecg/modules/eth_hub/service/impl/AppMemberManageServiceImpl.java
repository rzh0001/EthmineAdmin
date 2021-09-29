package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import org.jeecg.modules.eth_hub.dao.AppMemberRepository;
import org.jeecg.modules.eth_hub.entity.AppMember;
import org.jeecg.modules.eth_hub.entity.AppMemberRegister;
import org.jeecg.modules.eth_hub.service.AppMemberManageService;
import org.jeecg.modules.eth_hub.service.IAppMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMemberManageServiceImpl implements AppMemberManageService {
    @Autowired
    private IAppMemberService memberService;

    @Autowired
    private AppMemberRepository dao;


    @Override
    public void register(AppMemberRegister register) {

        // check email
        AppMember emailUser = dao.findByEmail(register.getEmail());
        Assert.isNull(emailUser);

        //check invite code
        AppMember inviteUser = dao.findByInviteCode(register.getInviteBy());
        Assert.notNull(inviteUser);

        AppMember newMember = new AppMember();
        BeanUtil.copyProperties(register, newMember);
        newMember.setUsername(register.getEmail());

        memberService.save(newMember);


    }


}
