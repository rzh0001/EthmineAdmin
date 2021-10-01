package org.jeecg.modules.eth_hub.service.impl;

import cn.hutool.json.JSONUtil;
import org.jeecg.modules.eth_hub.entity.MinerDashboard;
import org.jeecg.modules.eth_hub.entity.Payout;
import org.jeecg.modules.eth_hub.entity.PoolStat;
import org.jeecg.modules.eth_hub.service.EthermineApi;
import org.jeecg.modules.ruan.RuanTool;
import org.springframework.stereotype.Service;

@Service
public class EthermineApiImpl implements EthermineApi {
    private final static String poolUrl = "https://api.ethermine.org";
    private final String miner = "0x0c4762728ad72e2e976219dfb2253c6725dfe229";

    @Override
    public PoolStat basicPoolStats() {
        String res = RuanTool.httpGet(poolUrl + basicPoolStats);
        return JSONUtil.toBean(res, PoolStat.class);
    }

    @Override
    public MinerDashboard minerDashboard(String ethAddress) {
        String res = RuanTool.httpGet(poolUrl + RuanTool.concat(minerDashboard, ethAddress));
        return JSONUtil.toBean(res, MinerDashboard.class);
    }

    @Override
    public Payout minerPayouts(String ethAddress) {
        String res = RuanTool.httpGet(poolUrl + RuanTool.concat(minerPayouts, ethAddress));
        return JSONUtil.toBean(res, Payout.class);
    }
}
