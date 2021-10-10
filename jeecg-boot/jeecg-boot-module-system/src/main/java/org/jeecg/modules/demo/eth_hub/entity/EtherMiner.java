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
 * @Description: ether_miner
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("ether_miner")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ether_miner对象", description="ether_miner")
public class EtherMiner implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**矿工昵称*/
	@Excel(name = "矿工昵称", width = 15)
    @ApiModelProperty(value = "矿工昵称")
    private java.lang.String minerName;
	/**ETH地址*/
	@Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private java.lang.String minerAddress;
	/**会员ID*/
	@Excel(name = "会员ID", width = 15)
    @ApiModelProperty(value = "会员ID")
    private java.lang.String memberId;
	/**会员账户*/
	@Excel(name = "会员账户", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "会员账户")
    private java.lang.String memberUsername;
	/**会员昵称*/
	@Excel(name = "会员昵称", width = 15)
    @ApiModelProperty(value = "会员昵称")
    private java.lang.String memberNickname;
	/**待转账*/
	@Excel(name = "待转账", width = 15)
    @ApiModelProperty(value = "待转账")
    private java.math.BigDecimal unpaid;
	/**矿机总数*/
	@Excel(name = "矿机总数", width = 15)
    @ApiModelProperty(value = "矿机总数")
    private java.lang.Integer workers;
	/**活跃矿机*/
	@Excel(name = "活跃矿机", width = 15)
    @ApiModelProperty(value = "活跃矿机")
    private java.lang.Integer activeWorkers;
	/**报告算力*/
	@Excel(name = "报告算力", width = 15)
    @ApiModelProperty(value = "报告算力")
    private java.lang.Double reportedHashrate;
	/**当前算力*/
	@Excel(name = "当前算力", width = 15)
    @ApiModelProperty(value = "当前算力")
    private java.lang.Double currentHashrate;
	/**平均算力*/
	@Excel(name = "平均算力", width = 15)
    @ApiModelProperty(value = "平均算力")
    private java.lang.Double averageHashrate;
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
	/**monitor*/
	@Excel(name = "monitor", width = 15)
    @ApiModelProperty(value = "monitor")
    private java.lang.Integer monitor;
	/**email*/
	@Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private java.lang.String email;
	/**minPayout*/
	@Excel(name = "minPayout", width = 15)
    @ApiModelProperty(value = "minPayout")
    private java.lang.Integer minPayout;
	/**最后更新*/
	@Excel(name = "最后更新", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新")
    private java.util.Date lastSeen;
	/**报告时间*/
	@Excel(name = "报告时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报告时间")
    private java.util.Date time;
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
	/**预计日收益(ETH)*/
	@Excel(name = "预计日收益(ETH)", width = 15)
    @ApiModelProperty(value = "预计日收益(ETH)")
    private java.lang.Double coinsPerDay;
	/**预计日收益(USD)*/
	@Excel(name = "预计日收益(USD)", width = 15)
    @ApiModelProperty(value = "预计日收益(USD)")
    private java.lang.Double usdPerDay;
	/**预计日收益(BTC)*/
	@Excel(name = "预计日收益(BTC)", width = 15)
    @ApiModelProperty(value = "预计日收益(BTC)")
    private java.lang.Double btcPerDay;
}
