package org.jeecg.modules.eth_hub.service;

import org.jeecg.modules.eth_hub.entity.AppUser;

public interface AppMemberManageService {

    void register(AppUser register);

    String login(AppUser user);

    void logout(AppUser user);
}
