package org.jeecg.modules.eth_hub.service;

import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;

public interface EtherminePersistService {

    void persistMiner(EtherMiner miner);

    void persistWorker(EtherMiner miner);
}
