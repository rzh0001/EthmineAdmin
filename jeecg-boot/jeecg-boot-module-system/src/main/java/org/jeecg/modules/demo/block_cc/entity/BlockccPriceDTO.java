package org.jeecg.modules.demo.block_cc.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class BlockccPriceDTO {
    @Alias("s")
    private String slug;
    @Alias("S")
    private String symbol;
    @Alias("t")
    private Long time;
    @Alias("u")
    private BigDecimal usd;
    @Alias("b")
    private BigDecimal btc;
    @Alias("a")
    private BigDecimal volume; //交易量(单位为当前币种)
    @Alias("v")
    private BigDecimal volumeUsd; //交易量(USD)
    @Alias("ra")
    private BigDecimal reportedVolume;
    @Alias("rv")
    private BigDecimal reportedVolumeUsd;
    @Alias("m")
    private BigDecimal marketCapUsd; //市值(USD)

    private BigDecimal c; //24小时涨跌幅

    private BigDecimal h; //24小时最高价

    private BigDecimal l; //24小时最低价

    private BigDecimal cw; //1周涨跌幅

    private BigDecimal hw; //1周最高价

    private BigDecimal lw; //1周最低价

    private BigDecimal cm; //1月涨跌幅

    private BigDecimal hm; //1月最高价

    private BigDecimal lm; //1月最低价

    private BigDecimal ha; //历史最高价

    private BigDecimal la; //历史最低价
}
