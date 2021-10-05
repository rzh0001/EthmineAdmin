package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorkerOnline;

/**
 * @Description: ether_worker_online
 * @Author: jeecg-boot
 * @Version: V1.0
 */
public interface IEtherWorkerOnlineService extends IService<EtherWorkerOnline> {

    void saveWorkerOnline(EtherWorker worker);
}
