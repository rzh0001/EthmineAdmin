package org.jeecg.modules.eth_hub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: ether_miner
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
@Data
@TableName("ether_miner")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ether_miner对象", description = "ether_miner")
public class EtherMiner implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
    /**
     * 矿工昵称
     */
    @Excel(name = "矿工昵称", width = 15)
    @ApiModelProperty(value = "矿工昵称")
    private java.lang.String minerName;
    /**
     * ETH地址
     */
    @Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private java.lang.String minerAddress;
    /**
     * 报告时间
     */
    @Excel(name = "报告时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat()
    @ApiModelProperty(value = "报告时间")
    private java.util.Date time;
    /**
     * 最后更新
     */
    @Excel(name = "最后更新", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat()
    @ApiModelProperty(value = "最后更新")
    private java.util.Date lastSeen;
    /**
     * 报告算力
     */
    @Excel(name = "报告算力", width = 15)
    @ApiModelProperty(value = "报告算力")
    private java.lang.Integer reportedHashrate;
    /**
     * 当前算力
     */
    @Excel(name = "当前算力", width = 15)
    @ApiModelProperty(value = "当前算力")
    private java.lang.Double currentHashrate;
    /**
     * 有效份额
     */
    @Excel(name = "有效份额", width = 15)
    @ApiModelProperty(value = "有效份额")
    private java.lang.Integer validShares;
    /**
     * 无效份额
     */
    @Excel(name = "无效份额", width = 15)
    @ApiModelProperty(value = "无效份额")
    private java.lang.Integer invalidShares;
    /**
     * 延迟份额
     */
    @Excel(name = "延迟份额", width = 15)
    @ApiModelProperty(value = "延迟份额")
    private java.lang.Integer staleShares;
    /**
     * 活跃矿机
     */
    @Excel(name = "活跃矿机", width = 15)
    @ApiModelProperty(value = "活跃矿机")
    private java.lang.Integer activeWorkers;
    /**
     * 待转账
     */
    @Excel(name = "待转账", width = 15)
    @ApiModelProperty(value = "待转账")
    private BigDecimal unpaid;
    /**
     * monitor
     */
    @Excel(name = "monitor", width = 15)
    @ApiModelProperty(value = "monitor")
    private java.lang.Integer monitor;
    /**
     * email
     */
    @Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private java.lang.String email;
    /**
     * minPayout
     */
    @Excel(name = "minPayout", width = 15)
    @ApiModelProperty(value = "minPayout")
    private java.lang.Integer minPayout;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
    /**
     * delFlag
     */
    @Excel(name = "delFlag", width = 15)
    @ApiModelProperty(value = "delFlag")
    private java.lang.Integer delFlag;
}
