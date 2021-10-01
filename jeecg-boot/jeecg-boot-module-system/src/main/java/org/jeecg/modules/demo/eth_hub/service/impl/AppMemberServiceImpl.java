package org.jeecg.modules.demo.eth_hub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;
import org.jeecg.modules.demo.eth_hub.mapper.AppMemberMapper;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberService;
import org.jeecg.modules.demo.eth_hub.service.IAppMemberWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Service
public class AppMemberServiceImpl extends ServiceImpl<AppMemberMapper, AppMember> implements IAppMemberService {

    @Autowired
    private IAppMemberWalletService walletService;

    @Override
    public void activeMemberWallet(AppMember member) {
        walletService.newWallet(member, "ETH");
    }
}
