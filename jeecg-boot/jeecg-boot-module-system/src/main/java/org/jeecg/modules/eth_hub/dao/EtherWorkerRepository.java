package org.jeecg.modules.eth_hub.dao;

import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.springframework.data.repository.CrudRepository;

public interface EtherWorkerRepository extends CrudRepository<EtherWorker, String> {

    EtherWorker findByWorkerId(String workerId);

    Integer countAllByMinerId(String minerId);

    Integer countByMinerIdAndOnlineStatus(String minerId, Integer status);

}
