package com.hfhj.akb.robot.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class WechatRobotDto implements Serializable{
    /**
     * 机器码
     */
    private String identity;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 微信号
     */
    private String wxId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nick;


    /**
     * 选择发送的群组
     */
    private String targetGroup;

    /**
     * 0：下线；1：上线
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 群号
     */
    private String qqGroup;

    /**
     * 推荐人QQ号
     */
    private String userQq;

    /**
     * 机器人QQ号
     */
    private String robotQq;

    /**
     * 备注
     */
    private String qq_remark;



}
