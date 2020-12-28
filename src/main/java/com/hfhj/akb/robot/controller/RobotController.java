package com.hfhj.akb.robot.controller;


import com.hfhj.akb.robot.dto.WechatRobotDto;
import com.hfhj.akb.robot.dto.WechatRobotLoginLogsDto;
import com.hfhj.akb.robot.entity.RobotWx;
import com.hfhj.akb.robot.service.IRobotService;
import com.hfhj.akb.starter.domain.BaseResult;
import com.hfhj.akb.starter.domain.dto.PageList;
import com.hfhj.akb.starter.util.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hfhj.akb.starter.aspect.WebLogAspect;


/**
 * @Author: hwl
 * @DateTime: 2020-09-23 15:00
 * @Description: 机器人控制器
 */
@Api(tags = "机器人")
@Slf4j
@RestController
@RequestMapping("/app/v1/robot")
public class RobotController {

    @Autowired
    private IRobotService robotService;

    /**
     * 机器人列表。
     *
     * @return BaseResult
     * @author hwl
     * @date 2020/10/02 15：00
     */
    @RequestMapping("/list")
    public BaseResult list(
            @RequestParam(defaultValue = "", required = false) String uid,// 是否仅优惠券
            @RequestParam(defaultValue = "", required = false) String nick,// 搜索关键字

            @RequestParam(defaultValue = "asc", required = false) String sort,
            @RequestParam(defaultValue = "created", required = false) String sortField,

            @RequestParam(defaultValue = "1", required = false) Integer pageNo,// 当前页
            @RequestParam(defaultValue = "10", required = false) Integer pageSize //每页条数
    ) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("uid",uid);
        queryParams.put("nick",nick);

        Map<String,Boolean> orderByParams = new HashMap<String,Boolean>();
        if (StringUtils.isNotEmpty(sortField))
        {
            orderByParams.put(sortField,sort.equalsIgnoreCase("desc"));
        }


        PageList<RobotWx> resultList = robotService.listRobot(queryParams,orderByParams,pageNo,pageSize);
        return BaseResult.ok(resultList);
    }


    /**
     * 机器人列表。
     *
     * @return BaseResult
     * @author hwl
     * @date 2020/10/02 15：00
     */
    @RequestMapping("/getRobotDetail")
    public BaseResult getRobotDetail(
            @RequestParam(defaultValue = "", required = false) String uid // 是否仅优惠券
    ) {

        WechatRobotDto wechatRobotDto = robotService.getRobotDetails(Integer.valueOf(uid));
        return BaseResult.ok(wechatRobotDto);
    }

    @RequestMapping("/getRobotLoginLogs")
    public BaseResult getRobotLoginLogs(
            @RequestParam(defaultValue = "", required = false) String uid // 是否仅优惠券
    ) {

        WechatRobotLoginLogsDto wechatRobotLoginLogsDto = robotService.getRobotLoginLogs(Integer.valueOf(uid));
        return BaseResult.ok(wechatRobotLoginLogsDto);
    }


    public static void main(String[] args) {
//        DateUtils.DateDiffTimeBean todayTime = DateUtils.getTodayTime();
//
//        System.out.println(todayTime.getStart());
//        System.out.println(todayTime.getEnd());
//
//        System.out.println(DateUtils.dateToSStr(new Date()));
//        System.out.println(DateUtils.toSecondTime(DateUtils.strToSDate(DateUtils.dateToSStr(new Date())).getTime()));

        String format = String.format("List_akb_product_goodsSearchV1_hasCoupon:{%s}+keyword:{%s}+source:{%d}+sort:{%s}+sortName1:{%s}+pageNo:{%d}+pageSize:{%d}+priceLow:{%d}+priceHigh:{%d}+isOnlyJdSelf:{%s}", "false", "", 1, "desc", "month_sales", 1, 10, 0, 100, "false");
        System.out.println(format);
    }

}
