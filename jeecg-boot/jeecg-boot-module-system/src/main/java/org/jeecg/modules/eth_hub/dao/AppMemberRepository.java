package org.jeecg.modules.eth_hub.dao;

import org.jeecg.modules.eth_hub.entity.AppMember;
import org.springframework.data.repository.CrudRepository;

public interface AppMemberRepository extends CrudRepository<AppMember, String> {

    AppMember findByEmail(String email);

    AppMember findByInviteCode(String inviteBy);
}
