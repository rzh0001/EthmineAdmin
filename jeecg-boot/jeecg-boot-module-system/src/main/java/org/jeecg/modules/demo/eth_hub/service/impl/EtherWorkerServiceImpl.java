package org.jeecg.modules.demo.eth_hub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.eth_hub.entity.EtherMiner;
import org.jeecg.modules.demo.eth_hub.entity.EtherWorker;
import org.jeecg.modules.demo.eth_hub.mapper.EtherWorkerMapper;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerOnlineService;
import org.jeecg.modules.demo.eth_hub.service.IEtherWorkerService;
import org.jeecg.modules.eth_hub.dao.EtherWorkerRepository;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;
import org.jeecg.modules.ruan.RuanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: ether_worker
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Service
public class EtherWorkerServiceImpl extends ServiceImpl<EtherWorkerMapper, EtherWorker> implements IEtherWorkerService {
    @Autowired
    private EtherWorkerRepository workerDao;

    @Autowired
    private IEtherWorkerOnlineService onlineService;

    @Override
    public void saveWorker(EtherMiner miner, MinerDashboard.DataDTO.WorkersDTO data) {

        // check worker and save
        String workerId = miner.getMinerAddress() + ":" + data.getWorker();

        EtherWorker worker = workerDao.findByWorkerId(workerId);

        if (BeanUtil.isEmpty(worker)) {

            worker = new EtherWorker();

            worker.setWorkerId(workerId);
            worker.setMinerId(miner.getId());
            worker.setMinerAddress(miner.getMinerAddress());
            worker.setMinerName(miner.getMinerName());
        }

        worker.setTime(RuanTool.convertTime(data.getTime()));
        worker.setLastSeen(RuanTool.convertTime(data.getLastSeen()));
        worker.setWorkerName(data.getWorker());
        worker.setCurrentHashrate(data.getCurrentHashrate());
        worker.setReportedHashrate(data.getReportedHashrate());
        worker.setInvalidShares(data.getInvalidShares());
        worker.setStaleShares(data.getStaleShares());
        worker.setInvalidShares(data.getInvalidShares());


        // lastSeen 超过30分钟 或 ReportedHashrate = 0 判断矿机掉线
        if (RuanTool.betweenDateToMinute(worker.getLastSeen(), DateUtil.date()) > 30 || data.getReportedHashrate() == 0) {
            worker.setOnlineStatus(0);
        } else {
            worker.setOnlineStatus(1);
        }

        saveOrUpdate(worker);

        // 更新ether_worker_online表，以小时为单位，用于电费计算
        if (worker.getOnlineStatus() == 1) {
            onlineService.saveWorkerOnline(worker);
        }

    }

    @Override
    public void updateInactiveWorkers(EtherMiner miner) {
        UpdateWrapper<EtherWorker> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().isNull(EtherWorker::getOnlineStatus).eq(EtherWorker::getMinerId, miner.getId());

        EtherWorker worker = new EtherWorker();
        worker.setOnlineStatus(3);
        baseMapper.update(worker, updateWrapper);
    }

}
