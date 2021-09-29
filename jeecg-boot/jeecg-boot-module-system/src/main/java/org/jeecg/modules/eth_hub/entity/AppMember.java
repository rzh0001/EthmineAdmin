package org.jeecg.modules.eth_hub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: app_member
 * @Author: jeecg-boot
 * @Date: 2021-09-29
 * @Version: V1.0
 */
@Data
@Entity
@TableName("app_member")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "app_member对象", description = "app_member")
public class AppMember implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 头像
     */
    @Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 用户名
     */
    @Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机
     */
    @Excel(name = "手机", width = 15)
    @ApiModelProperty(value = "手机")
    private String mobile;
    /**
     * 昵称
     */
    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 密码
     */
    @Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 平台
     */
    @Excel(name = "平台", width = 15)
    @ApiModelProperty(value = "平台")
    private Integer platform;
    /**
     * 邀请码
     */
    @Excel(name = "邀请码", width = 15)
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
    /**
     * 邀请人
     */
    @Excel(name = "邀请人", width = 15)
    @ApiModelProperty(value = "邀请人")
    private String inviteBy;
    /**
     * 账户状态
     */
    @Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
    private Integer status;
    /**
     * VIP类型
     */
    @Excel(name = "VIP类型", width = 15)
    @ApiModelProperty(value = "VIP类型")
    private Integer vipType;
    /**
     * VIP状态
     */
    @Excel(name = "VIP状态", width = 15)
    @ApiModelProperty(value = "VIP状态")
    private Integer vipStatus;
    /**
     * VIP开始时间
     */
    @Excel(name = "VIP开始时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "VIP开始时间")
    private Date vipStartTime;
    /**
     * VIP过期时间
     */
    @Excel(name = "VIP过期时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "VIP过期时间")
    private Date vipEndTime;
    /**
     * permissions
     */
    @Excel(name = "permissions", width = 15)
    @ApiModelProperty(value = "permissions")
    private String permissions;
    /**
     * 性别
     */
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 生日
     */
    @Excel(name = "生日", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private Date birth;
    /**
     * 地址
     */
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String address;
    /**
     * grade
     */
    @Excel(name = "grade", width = 15)
    @ApiModelProperty(value = "grade")
    private Integer grade;
    /**
     * 位置
     */
    @Excel(name = "位置", width = 15)
    @ApiModelProperty(value = "位置")
    private String position;
    /**
     * description
     */
    @Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
    private String description;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * delFlag
     */
    @Excel(name = "delFlag", width = 15)
    @ApiModelProperty(value = "delFlag")
    private Integer delFlag;
}
