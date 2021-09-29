package org.jeecg.modules.eth_hub.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    private String id;
    /**
     * 矿工昵称
     */
    @Excel(name = "矿工昵称", width = 15)
    @ApiModelProperty(value = "矿工昵称")
    private String minerName;
    /**
     * ETH地址
     */
    @Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private String minerAddress;
    /**
     * 报告时间
     */
    @Excel(name = "报告时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "报告时间")
    private Date time;
    /**
     * 最后更新
     */
    @Excel(name = "最后更新", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最后更新")
    private Date lastSeen;
    /**
     * 报告算力
     */
    @Excel(name = "报告算力", width = 15)
    @ApiModelProperty(value = "报告算力")
    private Integer reportedHashrate;
    /**
     * 当前算力
     */
    @Excel(name = "当前算力", width = 15)
    @ApiModelProperty(value = "当前算力")
    private Double currentHashrate;
    /**
     * 有效份额
     */
    @Excel(name = "有效份额", width = 15)
    @ApiModelProperty(value = "有效份额")
    private Integer validShares;
    /**
     * 无效份额
     */
    @Excel(name = "无效份额", width = 15)
    @ApiModelProperty(value = "无效份额")
    private Integer invalidShares;
    /**
     * 延迟份额
     */
    @Excel(name = "延迟份额", width = 15)
    @ApiModelProperty(value = "延迟份额")
    private Integer staleShares;
    /**
     * 活跃矿机
     */
    @Excel(name = "活跃矿机", width = 15)
    @ApiModelProperty(value = "活跃矿机")
    private Integer activeWorkers;
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
    private Integer monitor;
    /**
     * email
     */
    @Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private String email;
    /**
     * minPayout
     */
    @Excel(name = "minPayout", width = 15)
    @ApiModelProperty(value = "minPayout")
    private Integer minPayout;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * delFlag
     */
    @Excel(name = "delFlag", width = 15)
    @ApiModelProperty(value = "delFlag")
    private Integer delFlag;
}
