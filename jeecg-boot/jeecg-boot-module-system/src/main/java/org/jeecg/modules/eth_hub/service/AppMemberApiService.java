package org.jeecg.modules.eth_hub.service;

import org.jeecg.modules.eth_hub.entity.AppMemberBillData;
import org.jeecg.modules.eth_hub.entity.AppMemberMiningData;
import org.jeecg.modules.eth_hub.entity.AppUser;

import java.util.List;

public interface AppMemberApiService {

    void register(AppUser register);

    String login(AppUser user);

    void logout(AppUser user);

    AppMemberMiningData miningData(String username);

    List<AppMemberBillData> bill(String username);
}
