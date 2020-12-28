package com.hfhj.akb.robot.dto;

import com.hfhj.akb.robot.entity.RobotLoginLogs;
import com.hfhj.akb.robot.entity.RobotWx;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WechatRobotLoginLogsDto implements Serializable{

    private RobotWx robotWx;

    private List<RobotLoginLogs> robotLoginLogsList;


}
