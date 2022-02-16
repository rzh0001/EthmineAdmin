package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorkerOnline;
import org.jeecg.modules.demo.eth_hub.mapper.EtherWorkerOnlineMapper;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerOnlineService;
import org.jeecg.modules.eth_hub.dao.EtherWorkerOnlineRepository;
import org.jeecg.modules.ruan.RuanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: ether_worker_online
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Service
public class EtherWorkerOnlineServiceImpl extends ServiceImpl<EtherWorkerOnlineMapper, EtherWorkerOnline> implements IEtherWorkerOnlineService {

    @Autowired
    private EtherWorkerOnlineRepository onlineDao;

    @Override
    public void saveWorkerOnline(EtherWorker worker) {

        String hourStr = RuanTool.dateToHourString(DateUtil.date());

        /**
         * 循环查询数据库，very bad
         */
//        EtherWorkerOnline data = onlineDao.findByWorkerIdAndOnlineTime(worker.getWorkerId(), hourStr);
//
//        // 一小时为单位，保存一条数据
//        if (BeanUtil.isNotEmpty(data)) {
//            return;
//        }

        EtherWorkerOnline data = new EtherWorkerOnline();
        String id = RuanTool.concat("{}-{}-{}", worker.getMinerName(),worker.getWorkerName(),hourStr);
        data.setId(id);
        data.setOnlineTime(hourStr);
        data.setWorkerId(worker.getWorkerId());
        data.setWorkerName(worker.getWorkerName());
        data.setMinerAddress(worker.getMinerAddress());
        data.setMinerId(worker.getMinerId());
        data.setMinerName(worker.getMinerName());

        saveOrUpdate(data);

    }
}
