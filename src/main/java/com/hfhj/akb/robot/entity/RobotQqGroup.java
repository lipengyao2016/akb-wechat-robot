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
 * @Date 2020-12-28 13:53:14 
 */

@Data
@Entity
@Table ( name ="robot_qq_group" , schema = "")
public class RobotQqGroup  {

	/**
	 * id
	 */
	@Id
   	@Column(name = "id" )
	private Long id;

	/**
	 * 群号
	 */
   	@Column(name = "qq_group" )
	private String qqGroup;

	/**
	 * 推荐人QQ号
	 */
   	@Column(name = "user_qq" )
	private String userQq;

	/**
	 * 机器人QQ号
	 */
   	@Column(name = "robot_qq" )
	private String robotQq;

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
	 * 更新时间
	 */
   	@Column(name = "modified" )
	private Long modified;


	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"qqGroup='" + qqGroup + '\'' +
					"userQq='" + userQq + '\'' +
					"robotQq='" + robotQq + '\'' +
					"remark='" + remark + '\'' +
					"created='" + created + '\'' +
					"modified='" + modified + '\'' +
				'}';
	}

}
