package org.jeecg.modules.eth_hub.service;


import org.jeecg.modules.eth_hub.entity.MinerDashboard;
import org.jeecg.modules.eth_hub.entity.Payout;
import org.jeecg.modules.eth_hub.entity.PoolStat;

/**
 * ethermine pool api
 * https://www.ethermine.org/api/pool
 */
public interface EthermineApi {

    /// Pool Api
    final String basicPoolStats = "/poolStats";
    final String minedBlocksStats = "/blocks/history";
    final String networkStats = "/networkStats";
    final String serverHashrateStats = "/servers/history";

    /// Miner Api
    final String minerDashboard = "/miner/{}/dashboard";
    final String minerHistory = "/miner/{}/history";
    final String minerPayouts = "/miner/{}/payouts";
    final String minerRounds = "/miner/{}/rounds";
    final String minerSettings = "/miner/{}/settings";
    final String minerCurrentStats = "/miner/{}/currentStats";

    /// Worker Api
    final String allWorkerStatistics = "/miner/{}/workers";
    final String individualHistoricalWorkerStatistics = "/miner/{}/worker/:worker/history";
    final String individualWorkerStatistics = "/miner/{}/worker/:worker/currentStats";
    final String workerMonitoring = "/miner/{}/worker/:worker/monitor";


    PoolStat basicPoolStats();

    MinerDashboard minerDashboard(String ethAddress);

    Payout minerPayouts(String ethAddress);


}
