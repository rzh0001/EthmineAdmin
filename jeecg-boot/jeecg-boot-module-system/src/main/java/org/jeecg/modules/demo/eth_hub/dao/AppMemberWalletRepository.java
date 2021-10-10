package org.jeecg.modules.demo.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.AppMemberWallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppMemberWalletRepository extends CrudRepository<AppMemberWallet, String> {

    Optional<AppMemberWallet> findByMemberUsernameAndCurrency(String memberUsername, String currency);
}
