package com.hfhj.akb.robot.service;

import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.starter.domain.dto.PageList;

import java.util.Map;

public interface IRobotService {
    public PageList<RobotWx> listRobot(Map<String,Object> queryParams, Map<String,Boolean> orderByParams,
                                       Integer pageNo, Integer pageSize);

    public WechatRobotDto getRobotDetails(Integer uid);

    public WechatRobotLoginLogsDto getRobotLoginLogs(Integer uid);
}
