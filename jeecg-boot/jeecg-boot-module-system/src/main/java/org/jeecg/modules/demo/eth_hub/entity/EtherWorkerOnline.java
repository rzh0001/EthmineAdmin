package org.jeecg.modules.demo.eth_hub.entity;

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
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description: ether_worker_online
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("ether_worker_online")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ether_worker_online对象", description="ether_worker_online")
public class EtherWorkerOnline implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**矿机ID*/
	@Excel(name = "矿机ID", width = 15)
    @ApiModelProperty(value = "矿机ID")
    private java.lang.String workerId;
	/**矿机名称*/
	@Excel(name = "矿机名称", width = 15)
    @ApiModelProperty(value = "矿机名称")
    private java.lang.String workerName;
	/**矿工地址*/
	@Excel(name = "矿工地址", width = 15)
    @ApiModelProperty(value = "矿工地址")
    private java.lang.String minerId;
	/**账户别名*/
	@Excel(name = "账户别名", width = 15)
    @ApiModelProperty(value = "账户别名")
    private java.lang.String minerName;
	/**ETH地址*/
	@Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private java.lang.String minerAddress;
	/**在线时间(小时)*/
	@Excel(name = "在线时间(小时)", width = 15)
    @ApiModelProperty(value = "在线时间(小时)")
    private java.lang.String onlineTime;
	/**结算状态*/
	@Excel(name = "结算状态", width = 15, dicCode = "settle_status")
	@Dict(dicCode = "settle_status")
    @ApiModelProperty(value = "结算状态")
    private java.lang.Integer settleStatus;
	/**结算时间*/
	@Excel(name = "结算时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结算时间")
    private java.util.Date settleTime;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
	/**delFlag*/
	@Excel(name = "delFlag", width = 15)
    @ApiModelProperty(value = "delFlag")
    private java.lang.Integer delFlag;
}
