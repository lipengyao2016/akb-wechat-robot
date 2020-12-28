package com.hfhj.akb.robot.dao;

import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotLoginLogs;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.starter.db.Query;
import com.hfhj.akb.starter.domain.dto.PageList;

public interface IRobotLoginLogsDao {

    public PageList<RobotLoginLogs> getRobotLoginLogs(Integer uid);
}
