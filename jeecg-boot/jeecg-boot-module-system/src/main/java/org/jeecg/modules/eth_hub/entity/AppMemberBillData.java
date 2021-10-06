package org.jeecg.modules.eth_hub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AppMemberBillData {

    private java.lang.String id;

    /**
     * 币种
     */
    @ApiModelProperty(value = "币种")
    private java.lang.String currency;
    /**
     * 账单类型
     */
    @Dict(dicCode = "bill_type")
    @ApiModelProperty(value = "账单类型")
    private java.lang.String type;
    /**
     * 账单金额
     */
    @ApiModelProperty(value = "账单金额")
    private java.math.BigDecimal amount;
    /**
     * 钱包余额
     */
    @ApiModelProperty(value = "钱包余额")
    private java.math.BigDecimal balance;
//    /**手续费*/
//    @ApiModelProperty(value = "手续费")
//    private java.math.BigDecimal charge;
    /**
     * 账单详情
     */

    @ApiModelProperty(value = "账单详情")
    private java.lang.String detail;
    /**
     * 详情关联
     */

    @ApiModelProperty(value = "详情关联")
    private java.lang.String detailId;
    /**
     * 结算日
     */

    @ApiModelProperty(value = "结算日")
    private java.lang.String settleDate;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
}
