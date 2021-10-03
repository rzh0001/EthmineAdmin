package org.jeecg.modules.eth_hub.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * App 会员挖矿数据
 */
@Data
public class AppMemberMiningData {

    /**
     * 活跃矿机
     */
    @ApiModelProperty(value = "活跃矿机")
    private java.lang.Integer activeWorkers;

    @ApiModelProperty(value = "活跃矿机")
    private java.lang.Integer inactiveWorkers;

    @ApiModelProperty(value = "总矿机数")
    private Integer workers;
    /**
     * 报告算力
     */
    @ApiModelProperty(value = "报告算力")
    private java.lang.Double reportedHashrate;
    /**
     * 当前算力
     */
    @ApiModelProperty(value = "当前算力")
    private java.lang.Double currentHashrate;
    /**
     * 有效份额
     */
    @ApiModelProperty(value = "有效份额")
    private java.lang.Integer validShares;
    /**
     * 无效份额
     */
    @ApiModelProperty(value = "无效份额")
    private java.lang.Integer invalidShares;

    @ApiModelProperty(value = "延迟份额")
    private Integer staleShares;

    @ApiModelProperty(value = "钱包余额")
    private java.math.BigDecimal balance;
    /**
     * 总收益
     */
    @ApiModelProperty(value = "总收益")
    private java.math.BigDecimal totalEarnings;
    /**
     * 待入账
     */
    @ApiModelProperty(value = "待入账")
    private java.math.BigDecimal unpaid;


}
