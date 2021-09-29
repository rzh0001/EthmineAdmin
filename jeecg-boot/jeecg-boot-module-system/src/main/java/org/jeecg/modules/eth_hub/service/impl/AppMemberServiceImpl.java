package org.jeecg.modules.eth_hub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.eth_hub.entity.AppMember;
import org.jeecg.modules.eth_hub.mapper.AppMemberMapper;
import org.jeecg.modules.eth_hub.service.IAppMemberService;
import org.springframework.stereotype.Service;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
@Service
public class AppMemberServiceImpl extends ServiceImpl<AppMemberMapper, AppMember> implements IAppMemberService {

    @Override
    public AppMember findByEmail(String email) {
        return baseMapper.selectByEmail(email);
    }

    @Override
    public AppMember findByInviteCode(String inviteCode) {
        return baseMapper.selectByInviteCode(inviteCode);
    }
}
