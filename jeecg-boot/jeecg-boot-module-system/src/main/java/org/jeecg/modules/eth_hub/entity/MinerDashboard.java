package org.jeecg.modules.eth_hub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MinerDashboard {
    private String status;
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private List<StatisticsDTO> statistics;
        private List<WorkersDTO> workers;
        private CurrentStatisticsDTO currentStatistics;
        private SettingsDTO settings;

        /**
         * 账户数据
         */
        @NoArgsConstructor
        @Data
        public static class CurrentStatisticsDTO {
            private Integer time;
            private Integer lastSeen;
            private Long reportedHashrate;
            private Double currentHashrate;
            private Integer validShares;
            private Integer invalidShares;
            private Integer staleShares;
            private Integer activeWorkers;
            private Long unpaid;
        }

        /**
         * 账户配置信息
         */
        @NoArgsConstructor
        @Data
        public static class SettingsDTO {
            private Integer monitor;
            private Long minPayout;
            private String email;
        }

        @NoArgsConstructor
        @Data
        public static class StatisticsDTO {
            private Integer time;
            private Integer reportedHashrate;
            private Double currentHashrate;
            private Integer validShares;
            private Integer invalidShares;
            private Integer staleShares;
            private Integer activeWorkers;
        }

        /**
         * 矿机数据
         */
        @NoArgsConstructor
        @Data
        public static class WorkersDTO {
            private String worker;
            private Integer time;
            private Integer lastSeen;
            private Integer reportedHashrate;
            private Double currentHashrate;
            private Integer validShares;
            private Integer invalidShares;
            private Integer staleShares;
        }
    }
}
