package com.hfhj.akb.robot.dao.impl;

import com.hfhj.akb.robot.dao.IRobotDao;
import com.hfhj.akb.robot.dao.IRobotLoginLogsDao;
import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotLoginLogs;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.starter.db.Query;
import com.hfhj.akb.starter.domain.dto.PageList;
import com.hfhj.akb.starter.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RobotLoingLogsDaoImpl extends  BaseDaoImpl implements IRobotLoginLogsDao {


    @Override
    public PageList<RobotLoginLogs> getRobotLoginLogs(Integer uid) {
        Query query = new Query();
        query.eq("uid",uid);
        return this.list(query,"select t0.id,t0.uid,t0.login_time,t0.logout_time from robot_login_logs t0",RobotLoginLogs.class);
    }
}
