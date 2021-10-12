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
 * @Description: digital_currency
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("digital_currency")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="digital_currency对象", description="digital_currency")
public class DigitalCurrency implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private java.lang.String id;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
    private java.lang.String currency;
	/**美元价格*/
	@Excel(name = "美元价格", width = 15)
    @ApiModelProperty(value = "美元价格")
    private java.math.BigDecimal usd;
	/**人民币价格*/
	@Excel(name = "人民币价格", width = 15)
    @ApiModelProperty(value = "人民币价格")
    private java.math.BigDecimal cny;
	/**BTC价格*/
	@Excel(name = "BTC价格", width = 15)
    @ApiModelProperty(value = "BTC价格")
    private java.math.BigDecimal btc;
	/**创建者*/
    @ApiModelProperty(value = "创建者")
    private java.lang.String createBy;
	/**更新者*/
    @ApiModelProperty(value = "更新者")
    private java.lang.String updateBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
}
