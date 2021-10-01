package org.jeecg.modules.demo.eth_hub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;

/**
 * @Description: ether_miner
 * @Author: jeecg-boot
 * @Date: 2021-09-30
 * @Version: V1.0
 */
public interface IEtherMinerService extends IService<EtherMiner> {

    boolean updateAppMemberInfo(EtherMiner miner);

}
