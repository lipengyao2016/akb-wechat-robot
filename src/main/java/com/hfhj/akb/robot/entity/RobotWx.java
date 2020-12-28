package com.hfhj.akb.robot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2020-12-25 09:24:09 
 */


@Data
@Entity
@Table ( name ="robot_wx" , schema = "")
public class RobotWx  {

	/**
	 * ids
	 */
	@Id
   	@Column(name = "id" )
	private Long id;

	/**
	 * 机器码
	 */
   	@Column(name = "identity" )
	private String identity;

	/**
	 * 用户ID
	 */
   	@Column(name = "uid" )
	private Long uid;

	/**
	 * 微信号
	 */
   	@Column(name = "wx_id" )
	private String wxId;

	/**
	 * 头像
	 */
   	@Column(name = "avatar" )
	private String avatar;

	/**
	 * 昵称
	 */
   	@Column(name = "nick" )
	private String nick;

	/**
	 * 0：否；1：是
	 */
   	@Column(name = "is_publish_group" )
	private Boolean isPublishGroup;

	/**
	 * 0：否；1：是
	 */
   	@Column(name = "is_publish_moments" )
	private Boolean isPublishMoments;

	/**
	 * 0：否；1：是
	 */
   	@Column(name = "is_wx_robot" )
	private Boolean isWxRobot;

	/**
	 * 选择发送的群组
	 */
   	@Column(name = "target_group" )
	private String targetGroup;

	/**
	 * 0：关闭；1：开启
	 */
   	@Column(name = "sw" )
	private Boolean sw;

	/**
	 * 微信机器人有效时间
	 */
   	@Column(name = "deadline" )
	private Long deadline;

	/**
	 * 自购订单数
	 */
   	@Column(name = "order_total" )
	private Long orderTotal;

	/**
	 * 0：否；1：是
	 */
   	@Column(name = "is_auto_renew" )
	private Integer isAutoRenew;

	/**
	 * 0：下线；1：上线
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 备注
	 */
   	@Column(name = "remark" )
	private String remark;

	/**
	 * 创建时间
	 */
   	@Column(name = "created" )
	private Long created;

	/**
	 * 修改时间
	 */
   	@Column(name = "modified" )
	private Long modified;

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"identity='" + identity + '\'' +
					"uid='" + uid + '\'' +
					"wxId='" + wxId + '\'' +
					"avatar='" + avatar + '\'' +
					"nick='" + nick + '\'' +
					"isPublishGroup='" + isPublishGroup + '\'' +
					"isPublishMoments='" + isPublishMoments + '\'' +
					"isWxRobot='" + isWxRobot + '\'' +
					"targetGroup='" + targetGroup + '\'' +
					"sw='" + sw + '\'' +
					"deadline='" + deadline + '\'' +
					"orderTotal='" + orderTotal + '\'' +
					"isAutoRenew='" + isAutoRenew + '\'' +
					"status='" + status + '\'' +
					"remark='" + remark + '\'' +
					"created='" + created + '\'' +
					"modified='" + modified + '\'' +
				'}';
	}

}
