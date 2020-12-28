package com.hfhj.akb.robot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2020-12-28 19:59:41 
 */

@Data
@Entity
@Table ( name ="robot_login_logs" , schema = "")
public class RobotLoginLogs {

	@Id
   	@Column(name = "id" )
	private Long id;

	/**
	 * 会员ID.
	 */
   	@Column(name = "uid" )
	private Long uid;

	/**
	 * 登录时间。
	 */
   	@Column(name = "login_time" )
	private Date loginTime;

	/**
	 * 登出时间。
	 */
   	@Column(name = "logout_time" )
	private Date logoutTime;



	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"uid='" + uid + '\'' +
					"loginTime='" + loginTime + '\'' +
					"logoutTime='" + logoutTime + '\'' +
				'}';
	}

}
