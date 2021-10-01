package org.jeecg.modules.demo.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.springframework.data.repository.CrudRepository;

public interface AppMemberWalletRepository extends CrudRepository<AppMemberWallet, String> {

    AppMemberWallet findByMemberUsernameAndCurrency(String memberUsername, String currency);
}
