package org.jeecg.modules.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.EtherWorkerOnline;
import org.springframework.data.repository.CrudRepository;

public interface EtherWorkerOnlineRepository extends CrudRepository<EtherWorkerOnline, String> {

    EtherWorkerOnline findByWorkerIdAndOnlineTime(String workerId, String onlineTime);
}
