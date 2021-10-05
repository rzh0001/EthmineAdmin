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
 * @Description: ether_worker
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("ether_worker")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ether_worker对象", description="ether_worker")
public class EtherWorker implements Serializable {
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
	/**报告时间*/
	@Excel(name = "报告时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "报告时间")
    private java.util.Date time;
	/**最后更新*/
	@Excel(name = "最后更新", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后更新")
    private java.util.Date lastSeen;
	/**报告算力*/
	@Excel(name = "报告算力", width = 15)
    @ApiModelProperty(value = "报告算力")
    private java.lang.Integer reportedHashrate;
	/**当前算力*/
	@Excel(name = "当前算力", width = 15)
    @ApiModelProperty(value = "当前算力")
    private java.lang.Double currentHashrate;
	/**有效份额*/
	@Excel(name = "有效份额", width = 15)
    @ApiModelProperty(value = "有效份额")
    private java.lang.Integer validShares;
	/**无效份额*/
	@Excel(name = "无效份额", width = 15)
    @ApiModelProperty(value = "无效份额")
    private java.lang.Integer invalidShares;
	/**延迟份额*/
	@Excel(name = "延迟份额", width = 15)
    @ApiModelProperty(value = "延迟份额")
    private java.lang.Integer staleShares;
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
	/**在线状态*/
	@Excel(name = "在线状态", width = 15, dicCode = "online_status")
	@Dict(dicCode = "online_status")
    @ApiModelProperty(value = "在线状态")
    private java.lang.Integer onlineStatus;
}
