package com.joyou.patrol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ls
 * @since 2022-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BisUser对象", description = "用户表")
public class BisUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码（主键）")
    @TableId(value = "user_code", type = IdType.AUTO)
    private String userCode;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "姓")
    private String familyName;

    @ApiModelProperty(value = "名")
    private String givenName;

    @ApiModelProperty(value = "姓名拼音")
    private String userNamePy;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "入职时间")
    private String entryTime;

    @ApiModelProperty(value = "交接时间")
    private String turnoverTime;

    @ApiModelProperty(value = "手机号")
    private String mobileNo;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "教育程度")
    private String education;

    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;

    @ApiModelProperty(value = "办公电话")
    private String officeTelNo;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "公司Code")
    private String commpanyCode;

    @ApiModelProperty(value = "公司名称")
    private String commpanyName;

    @ApiModelProperty(value = "部门Id")
    private String departId;

    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "收费站名称")
    private String bisStationName;

    @ApiModelProperty(value = "收费站code")
    private String bisStationCode;

    @ApiModelProperty(value = "职称编码")
    private String title;

    @ApiModelProperty(value = "职称")
    private String titleName;

    @ApiModelProperty(value = "可用状态")
    private String status;

    private String authKey;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "账号")
    private String accountName;

    @ApiModelProperty(value = "是否由immp系统创建用户")
    private Integer isImmp;

    @ApiModelProperty(value = "密码（Md5加密）")
    private String password;

    @ApiModelProperty(value = "是否逻辑删除（0：否 1：是）")
    private Integer isDelete;

    @ApiModelProperty(value = "是否外协单位（0：否 1：是）")
    private Integer isAssisted;

    @ApiModelProperty(value = "外协单位的名称")
    private String assistedCompany;

    @ApiModelProperty(value = "冻结原因（100：离职: 200：人员变动: 300其他）")
    private Integer freezeReason;

    @ApiModelProperty(value = "解冻原因")
    private String unfreezeReason;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否重置密码（0：否 1：是）")
    private Integer isResetPassword;

    private String job;

    private String jobValue;

    private Integer orderSort;

    @ApiModelProperty(value = "修改人编码")
    private String updateUserCode;

    @ApiModelProperty(value = "外部系统标识（养护系统）")
    private String externalSystemIdentification;


}
