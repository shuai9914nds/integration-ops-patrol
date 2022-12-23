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
 * 系统日志表
 * </p>
 *
 * @author ls
 * @since 2022-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ImmpSystemLog对象", description = "系统日志表")
public class ImmpSystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统日志ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "状态")
    private String logStatic;

    @ApiModelProperty(value = "姓名")
    private String logName;

    @ApiModelProperty(value = "服务(类名)")
    private String logService;

    @ApiModelProperty(value = "行为(方法名)")
    private String logBehavior;

    @ApiModelProperty(value = "ip")
    private String logIp;

    private String userToken;

    @ApiModelProperty(value = "参数")
    private String logParameter;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间 ")
    private Date createTime;

    @ApiModelProperty(value = "客户端类型")
    private String clientType;


}
