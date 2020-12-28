package com.hfhj.akb.robot.service.impl;

import com.hfhj.akb.robot.dao.IRobotDao;
import com.hfhj.akb.robot.dao.IRobotLoginLogsDao;
import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotLoginLogs;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.robot.service.IRobotService;
import com.hfhj.akb.starter.db.Query;
import com.hfhj.akb.starter.domain.dto.PageList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RobotServiceImpl implements IRobotService {

    @Autowired
    private IRobotDao robotDao;

    @Autowired
    private IRobotLoginLogsDao robotLoginLogsDao;

    @Override
    public PageList<RobotWx> listRobot(Map<String, Object> queryParams, Map<String,Boolean> orderByParams, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        query.setPageNum(pageNo);
        query.setLimit(pageSize);
        for (String key: queryParams.keySet()) {
            if (StringUtils.isNotEmpty((String)queryParams.get(key)) )
            {
                switch (key)
                {
                    case "uid":
                        query.eq(key,queryParams.get(key));
                        break;
                    case "nick":
                        query.like(key,queryParams.get(key));
                        break;
                }
            }

        }

        for (String orderByKey:orderByParams.keySet()) {
//              String[] orderByVals = orderByKey.split(" ");
//              boolean bDesc = orderByVals[1].equalsIgnoreCase("desc");
//              bDesc ? query.desc(orderByVals[1])
                if (orderByParams.get(orderByKey))
                {
                    query.desc(orderByKey);
                }
                else{
                    query.asc(orderByKey);
                }
        }


        return robotDao.listRobot(query);
    }

    @Override
    public WechatRobotDto getRobotDetails(Integer uid) {
        return robotDao.getRobotDetails(uid);
    }

    @Override
    public WechatRobotLoginLogsDto getRobotLoginLogs(Integer uid) {
        Query query = new Query();
        query.eq("t0.uid",uid);
        PageList<RobotWx> robotWxPageList = robotDao.listRobot(query);
        WechatRobotLoginLogsDto wechatRobotLoginLogsDto = new WechatRobotLoginLogsDto();
        wechatRobotLoginLogsDto.setRobotWx(robotWxPageList.getData().get(0));

        PageList<RobotLoginLogs> robotLoginLogsPageList =  robotLoginLogsDao.getRobotLoginLogs(uid);
        wechatRobotLoginLogsDto.setRobotLoginLogsList(robotLoginLogsPageList.getData());
        return wechatRobotLoginLogsDto;
    }
}
