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
 * @Description: eth_mining_machine
 * @Author: jeecg-boot
 * @Version: V1.0
 */
@Entity
@Data
@TableName("eth_mining_machine")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="eth_mining_machine对象", description="eth_mining_machine")
public class EthMiningMachine implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@Id
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private java.lang.String id;
	/**矿机名称*/
	@Excel(name = "矿机名称", width = 15)
    @ApiModelProperty(value = "矿机名称")
    private java.lang.String machineName;
	/**显卡型号*/
	@Excel(name = "显卡型号", width = 15)
    @ApiModelProperty(value = "显卡型号")
    private java.lang.String gpu;
	/**参考算力(M)*/
	@Excel(name = "参考算力(M)", width = 15)
    @ApiModelProperty(value = "参考算力(M)")
    private java.lang.Integer hashrate;
	/**参考功耗*/
	@Excel(name = "参考功耗", width = 15)
    @ApiModelProperty(value = "参考功耗")
    private java.lang.Integer powerWaste;
	/**每小时耗电量(度)*/
	@Excel(name = "每小时耗电量(度)", width = 15)
    @ApiModelProperty(value = "每小时耗电量(度)")
    private java.lang.Double powerWasteHour;
	/**详细参数*/
	@Excel(name = "详细参数", width = 15)
    @ApiModelProperty(value = "详细参数")
    private java.lang.String detail;
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
