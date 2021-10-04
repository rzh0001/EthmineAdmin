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
 * @Description: app_member_bill
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("app_member_bill")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="app_member_bill对象", description="app_member_bill")
public class AppMemberBill implements Serializable {
    private static final long serialVersionUID = 1L;

	/**账单ID*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "账单ID")
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
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
    private java.lang.String currency;
	/**账单类型*/
	@Excel(name = "账单类型", width = 15, dicCode = "bill_type")
	@Dict(dicCode = "bill_type")
    @ApiModelProperty(value = "账单类型")
    private java.lang.String type;
	/**账单金额*/
	@Excel(name = "账单金额", width = 15)
    @ApiModelProperty(value = "账单金额")
    private java.math.BigDecimal amount;
	/**钱包余额*/
	@Excel(name = "钱包余额", width = 15)
    @ApiModelProperty(value = "钱包余额")
    private java.math.BigDecimal balance;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
    private java.math.BigDecimal charge;
	/**账单详情*/
	@Excel(name = "账单详情", width = 15)
    @ApiModelProperty(value = "账单详情")
    private java.lang.String detail;
	/**详情关联*/
	@Excel(name = "详情关联", width = 15)
    @ApiModelProperty(value = "详情关联")
    private java.lang.String detailId;
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
