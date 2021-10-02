package org.jeecg.modules.demo.eth_hub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description: ether_payout
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("ether_payout")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ether_payout对象", description = "ether_payout")
public class EtherPayout implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
    /**
     * 矿工ID
     */
    @Excel(name = "矿工ID", width = 15)
    @ApiModelProperty(value = "矿工ID")
    private java.lang.String minerId;
    /**
     * 账户别名
     */
    @Excel(name = "账户别名", width = 15)
    @ApiModelProperty(value = "账户别名")
    private java.lang.String minerName;
    /**
     * ETH地址
     */
    @Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private java.lang.String minerAddress;
    /**
     * 金额
     */
    @Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
    private java.math.BigDecimal amount;
    /**
     * 转账时间
     */
    @Excel(name = "转账时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "转账时间")
    private java.util.Date paidOn;
    /**
     * 交易费用
     */
    @Excel(name = "交易费用", width = 15)
    @ApiModelProperty(value = "交易费用")
    private java.math.BigDecimal txCost;
    /**
     * 交易Hash
     */
    @Excel(name = "交易Hash", width = 15)
    @ApiModelProperty(value = "交易Hash")
    private java.lang.String txHash;
    /**
     * From Block
     */
    @Excel(name = "From Block", width = 15)
    @ApiModelProperty(value = "From Block")
    private java.lang.Integer start;
    /**
     * To Block
     */
    @Excel(name = "To Block", width = 15)
    @ApiModelProperty(value = "To Block")
    private java.lang.Integer end;
    /**
     * 结算状态
     */
    @Excel(name = "结算状态", width = 15, dicCode = "settle_status")
    @Dict(dicCode = "settle_status")
    @ApiModelProperty(value = "结算状态")
    private Integer settleStatus;
    /**
     * 结算时间
     */
    @Excel(name = "结算时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结算时间")
    private java.util.Date settleTime;
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
    /**
     * 唯一标志
     */
    @Excel(name = "唯一标志", width = 15)
    @ApiModelProperty(value = "唯一标志")
    private java.lang.String payoutNo;
}
