package org.jeecg.modules.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.eth_hub.entity.AppMember;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
public interface IAppMemberService extends IService<AppMember> {

    AppMember findByEmail(String email);

    AppMember findByInviteCode(String inviteCode);

}
