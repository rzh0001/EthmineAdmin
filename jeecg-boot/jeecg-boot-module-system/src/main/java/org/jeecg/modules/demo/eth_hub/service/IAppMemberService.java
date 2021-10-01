package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.AppMember;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IAppMemberService extends IService<AppMember> {

    /**
     * 激活会员账户
     *
     * @param member
     */
    void activeMemberWallet(AppMember member);
}
