package org.jeecg.modules.eth_hub.service;

import org.jeecg.modules.eth_hub.entity.AppMemberMiningData;
import org.jeecg.modules.eth_hub.entity.AppUser;

public interface AppMemberApiService {

    void register(AppUser register);

    String login(AppUser user);

    void logout(AppUser user);

    AppMemberMiningData mingData(String username);
}
