package org.jeecg.modules.eth_hub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Payout {
    private String status;
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private List<RoundsDTO> rounds;
        private List<?> payouts;
        private String miningStart;
        private Object pendingPayout;
        private EstimatesDTO estimates;

        @NoArgsConstructor
        @Data
        public static class EstimatesDTO {
            private Double averageHashrate;
            private Double coinsPerMin;
            private Double usdPerMin;
            private Double btcPerMin;
        }

        @NoArgsConstructor
        @Data
        public static class RoundsDTO {
            private Integer block;
            private Long amount;
        }
    }
}
