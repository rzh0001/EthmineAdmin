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
 * @Description: app_member_power
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("app_member_power")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="app_member_power对象", description="app_member_power")
public class AppMemberPower implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private java.lang.String id;
	/**会员ID*/
	@Excel(name = "会员ID", width = 15)
    @ApiModelProperty(value = "会员ID")
    private java.lang.String memberId;
	/**会员账户*/
	@Excel(name = "会员账户", width = 15)
    @ApiModelProperty(value = "会员账户")
    private java.lang.String memberUsername;
	/**会员昵称*/
	@Excel(name = "会员昵称", width = 15)
    @ApiModelProperty(value = "会员昵称")
    private java.lang.String memberNickname;
	/**矿工ID*/
	@Excel(name = "矿工ID", width = 15)
    @ApiModelProperty(value = "矿工ID")
    private java.lang.String minerId;
	/**矿工昵称*/
	@Excel(name = "矿工昵称", width = 15)
    @ApiModelProperty(value = "矿工昵称")
    private java.lang.String minerName;
	/**ETH地址*/
	@Excel(name = "ETH地址", width = 15)
    @ApiModelProperty(value = "ETH地址")
    private java.lang.String minerAddress;
	/**工作日*/
	@Excel(name = "工作日", width = 15)
    @ApiModelProperty(value = "工作日")
    private java.lang.String workDate;
	/**在线数量(台)*/
	@Excel(name = "在线数量(台)", width = 15)
    @ApiModelProperty(value = "在线数量(台)")
    private java.lang.Integer activeWorkers;
	/**在线时长(小时)*/
	@Excel(name = "在线时长(小时)", width = 15)
    @ApiModelProperty(value = "在线时长(小时)")
    private java.lang.Integer onlineHours;
	/**耗电量(度)*/
	@Excel(name = "耗电量(度)", width = 15)
    @ApiModelProperty(value = "耗电量(度)")
    private java.lang.Double powerQuantity;
	/**电价(元)*/
	@Excel(name = "电价(元)", width = 15)
    @ApiModelProperty(value = "电价(元)")
    private java.math.BigDecimal powerPrice;
	/**电费(元)*/
	@Excel(name = "电费(元)", width = 15)
    @ApiModelProperty(value = "电费(元)")
    private java.math.BigDecimal powerCharge;
	/**结算状态*/
	@Excel(name = "结算状态", width = 15)
    @ApiModelProperty(value = "结算状态")
    private java.lang.Integer settleStatus;
	/**结算日*/
	@Excel(name = "结算日", width = 15)
    @ApiModelProperty(value = "结算日")
    private java.lang.String settleDate;
	/**创建者*/
    @ApiModelProperty(value = "创建者")
    private java.lang.String createBy;
	/**更新者*/
    @ApiModelProperty(value = "更新者")
    private java.lang.String updateBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
}
