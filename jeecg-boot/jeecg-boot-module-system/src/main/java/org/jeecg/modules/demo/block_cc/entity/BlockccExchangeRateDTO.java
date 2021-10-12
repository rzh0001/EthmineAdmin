package org.jeecg.modules.demo.block_cc.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class BlockccExchangeRateDTO {
    @Alias("c")
    private String currency;
    @Alias("r")
    private BigDecimal rate;
}