package org.jeecg.modules.demo.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.springframework.data.repository.CrudRepository;

public interface EtherMinerRepository extends CrudRepository<EtherMiner, String> {

    EtherMiner findByMemberUsername(String memberUsername);
}
