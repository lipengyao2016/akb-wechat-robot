package com.hfhj.akb.robot.dao.impl;

import com.hfhj.akb.robot.dao.IRobotDao;
import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.starter.db.Query;
import com.hfhj.akb.starter.domain.dto.PageList;
import com.hfhj.akb.starter.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RobotDaoImpl extends  BaseDaoImpl implements IRobotDao {

    @Override
    public PageList<RobotWx> listRobot(Query query) {
        StringBuffer sb = new StringBuffer();
        sb.append("select t0.id,t0.identity,t0.uid,t0.wx_id,t0.avatar,t0.nick,t0.is_publish_group,t0.is_publish_moments from robot_wx t0");

        query.base(sb.toString()).exeSql();
        String querySql =  query.querySql();
        PageList result = this.page(query);
        List<RobotWx> robotWxList = EntityUtils.caseEntity(result.getData(),RobotWx.class,false);
        result.setData(robotWxList);
        return result;
    }

    @Override
    public WechatRobotDto getRobotDetails(Integer uid) {
        StringBuffer sb = new StringBuffer();
        sb.append("select t0.identity,t0.uid,t0.wx_id,t0.avatar,t0.nick,t0.target_group,t0.status,t0.remark,t1.qq_group,t1.user_qq,t1.robot_qq from robot_wx t0,robot_qq_group t1 where t0.uid = t1.uid ");
        Query query = new Query();
        query.eq("t0.uid",uid);
        query.base(sb.toString()).exeSql();
        String querySql =  query.querySql();
        WechatRobotDto wechatRobotDto = this.get(query,WechatRobotDto.class);
        return wechatRobotDto;
    }


}
