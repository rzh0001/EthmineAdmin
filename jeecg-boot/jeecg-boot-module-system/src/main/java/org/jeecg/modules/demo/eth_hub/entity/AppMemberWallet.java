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
 * @Description: app_member_wallet
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("app_member_wallet")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="app_member_wallet对象", description="app_member_wallet")
public class AppMemberWallet implements Serializable {
    private static final long serialVersionUID = 1L;

	/**钱包ID*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "钱包ID")
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
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
    private java.math.BigDecimal balance;
	/**总收益*/
	@Excel(name = "总收益", width = 15)
    @ApiModelProperty(value = "总收益")
    private java.math.BigDecimal totalEarnings;
	/**待入账*/
	@Excel(name = "待入账", width = 15)
    @ApiModelProperty(value = "待入账")
    private java.math.BigDecimal unpaid;
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
