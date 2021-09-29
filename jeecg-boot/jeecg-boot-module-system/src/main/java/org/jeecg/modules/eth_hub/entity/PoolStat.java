package org.jeecg.modules.eth_hub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PoolStat {

    private String status;
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private List<?> topMiners;
        private List<MinedBlocksDTO> minedBlocks;
        private PoolStatsDTO poolStats;
        private PriceDTO price;
        private EstimatesDTO estimates;

        @NoArgsConstructor
        @Data
        public static class PoolStatsDTO {
            private Double hashRate;
            private Integer miners;
            private Integer workers;
            private Double blocksPerHour;
        }

        @NoArgsConstructor
        @Data
        public static class PriceDTO {
            private String time;
            private Double usd;
            private Double btc;
            private Double eur;
            private Double cny;
            private Integer rub;
        }

        @NoArgsConstructor
        @Data
        public static class EstimatesDTO {
            private String time;
            private Double blockReward;
            private Long hashrate;
            private Double blockTime;
            private Double gasPrice;
        }

        @NoArgsConstructor
        @Data
        public static class MinedBlocksDTO {
            private Integer number;
            private String miner;
            private Integer time;
        }
    }
}
