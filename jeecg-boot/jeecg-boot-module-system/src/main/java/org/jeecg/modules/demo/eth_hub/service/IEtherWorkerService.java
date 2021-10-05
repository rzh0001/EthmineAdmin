package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;

/**
 * @Description: ether_worker
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IEtherWorkerService extends IService<EtherWorker> {

    /**
     * 保存矿机数据集
     *
     * @param miner
     * @param data
     */
    void saveWorker(EtherMiner miner, MinerDashboard.DataDTO.WorkersDTO data);

    /**
     * 更新离线矿机数据
     *
     * @param miner
     */
    void updateInactiveWorkers(EtherMiner miner);
}
