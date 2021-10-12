package org.jeecg.modules.demo.crypto_compare;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EthPrice {
    @Alias("USD")
    private BigDecimal usd;
    @Alias("CNY")
    private BigDecimal cny;
}
