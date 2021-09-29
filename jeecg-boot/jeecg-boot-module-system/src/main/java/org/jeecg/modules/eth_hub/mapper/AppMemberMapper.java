package org.jeecg.modules.eth_hub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.eth_hub.entity.AppMember;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
public interface AppMemberMapper extends BaseMapper<AppMember> {

    AppMember selectByEmail(String email);

    AppMember selectByInviteCode(String inviteCode);
}
