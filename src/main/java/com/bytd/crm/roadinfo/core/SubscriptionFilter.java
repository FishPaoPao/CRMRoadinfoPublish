package com.bytd.crm.roadinfo.core;

import com.bytd.crm.roadinfo.entities.*;
import com.bytd.crm.roadinfo.dao.*;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubscriptionFilter {
    static{
        PropertiesLoader.load();
    }

    private static Logger logger = Logger.getLogger(RoadInfoPublishJob.class);

    /**
     * 进行订阅信息匹配并推送
     * @param traffic 被广播出去的路况信息
     * @throws Exception
     */
    static void matchSubscriptionAndPush(DTrafficInfoBase traffic) throws Exception {

        //此highwayId为广播路况消息中的highwayId即国标highwayId
        int highwayId = traffic.getHighway_ID();
        //根据国标highwayId查询速通标准highwayId
        int highwayStandardId = HighWayDao.queryHighwayStandardCode(highwayId);
        //进行方向转换，路况信息中(1:进京 2：出京 3：内环 4：外环),全程监控路由数据中(1:内环 2:外环 3:进京 4:出京)
        int trafficInfoDirection;//路况信息中的方向
        String stake = traffic.getStake();
        String endStake = traffic.getEndStake();
        if(traffic instanceof DRoadInfo){
            trafficInfoDirection = ((DRoadInfo) traffic).getDirection();
        } else if (traffic instanceof  DTrafficInfo) {
            trafficInfoDirection = ((DTrafficInfo)traffic).getDirection();
        } else {
            trafficInfoDirection = ((DConstructInfo)traffic).getDirection();
            //解析施工路况信息的桩号字段，并为起始桩号、结束桩号赋值
            String[] stakes = ((DConstructInfo)traffic).getStake().replace(",","").split("-");
            if(stakes != null && stakes.length > 0){
                stake = stakes[0];
                if(stakes.length > 1){
                    endStake = stakes[1];
                } else {
                    endStake = stakes[0];
                }
            }
        }
        int routeDirection;
        switch (trafficInfoDirection) {
            case 1:
                routeDirection = 3;
                break;
            case 2:
                routeDirection = 4;
                break;
            case 3:
                routeDirection = 1;
                break;
            case 4:
                routeDirection = 2;
                break;
            default:
                routeDirection = trafficInfoDirection;
        }

        //1.根据路况信息traffic及订阅信息路段表d_subs_section表数据筛选将要推送的路况信息
        //返回结果为订阅id d_customer_subs表中的id字段
        List<BigInteger> subsIds = DSubsSectionDao.queryMatchSubsSection(highwayStandardId, routeDirection, Integer.parseInt(stake), Integer.parseInt(endStake));
        //2.根据subsId查询d_customer_subs表获取匹配订阅消息
        if (subsIds != null && subsIds.size() > 0) {
            logger.info("订阅消息转发");
            List<DCustomerSubs> matchedList = DCustomerSubsDao.queryCustomerSubsById(subsIds);
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("seqNo", System.currentTimeMillis());
            String subscriptionJson = buildPushingJson(traffic, matchedList);

            // 发送到指定地址
            WebDataProxy webDataProxy = new WebDataProxy();
            webDataProxy.sendJson(PropertiesLoader.LESUTONG_SUBS_URL, paramsMap, subscriptionJson);

            // 保存订阅发送历史到数据库
            DCustomerSubsDao.insertSubsPushInfo(PropertiesLoader.LESUTONG_SUBS_URL, paramsMap, subscriptionJson);
        }

    }

    /**
     * 进行订阅信息匹配并推送
     * @param traffic 基础路况信息（高速公路ID，起始桩号，结束桩号，发布状态）
     * @param subsList 用户订阅信息列表
     * @throws Exception
     */
    static void matchSubscriptionAndPush(DTrafficInfoBase traffic, List<DCustomerSubs> subsList) throws Exception{
        /*
        String startStake = traffic.getStake();
        String endStake = traffic.getEndStake();
        String[] stakes = traffic.getStake().split("-");
        if (stakes.length > 1){
            startStake = stakes[0];
            endStake = stakes[1];
            if(endStake.equals(" ") || endStake.equals(",")){
                endStake = startStake;
            }
        }*/

        final int cyclicRoad6 = 2; //六环线的StandardId 为 2
        //基础路况信息中的Highway_id（国标HighwayId）
        int highwayId = traffic.getHighway_ID();
        //根据国标HighwayID查询速通标准HighwayCode
        int highwayStandardId = HighWayDao.queryHighwayStandardCode(highwayId);
        List<DCustomerSubs> matchedList = new ArrayList<>();

        //以下代码逻辑为：根据订阅消息对路况信息进行筛选
        for(DCustomerSubs subsritpion: subsList){
            //订阅消息中的速通标准HighwayStandardId
            int inHighWayStandardId = subsritpion.getEntrance_HighWayStandardId();
            int outHighWayStandardId = subsritpion.getExitrance_HighwayStandardId();
            if(inHighWayStandardId == highwayStandardId//订阅消息中的HighwayID与路况消息中的highwayid匹配（入口）
                    || outHighWayStandardId == highwayStandardId//订阅消息中的HighwayID与路况消息中的HighwayID匹配（出口）
                    || ((inHighWayStandardId != outHighWayStandardId) && (highwayStandardId == cyclicRoad6)))//订阅消息中的出入口不在同一条路上，且路况发生在六环上
            {
                //将有路况消息发生的对应的订阅消息添加入匹配信息列表中
                matchedList.add(subsritpion);
            }
        }

        //存在可推送的路况路况信息
        if(matchedList.size() > 0){
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("seqNo", System.currentTimeMillis());
            String subscriptionJson = buildPushingJson(traffic, matchedList);

            // 发送到指定地址
            WebDataProxy webDataProxy = new WebDataProxy();
            webDataProxy.sendJson(PropertiesLoader.LESUTONG_SUBS_URL, paramsMap, subscriptionJson);

            // 保存订阅发送历史到数据库
            DCustomerSubsDao.insertSubsPushInfo(PropertiesLoader.LESUTONG_SUBS_URL, paramsMap, subscriptionJson);
        }
    }

    static String buildPushingJson(DTrafficInfoBase traffic, List<DCustomerSubs> matchedList){
        LSTRoadInfoInterface lstRoadInfo;
        if(traffic instanceof DConstructInfo){
            lstRoadInfo = LSTRoadInfoInterface.makeParams((DConstructInfo) traffic);
        }else if (traffic instanceof DRoadInfo){
            lstRoadInfo = LSTRoadInfoInterface.makeParams((DRoadInfo) traffic);
        }else{//else if (traffic instanceof DTrafficInfo){
            lstRoadInfo = LSTRoadInfoInterface.makeParams((DTrafficInfo) traffic);
        }
        String baseJson = JsonHelper.objectToJson(lstRoadInfo);
        List<LSTCustomerInterface> customers = getLstCustomerList(matchedList);
        String customersJson = JsonHelper.objectToJson(customers);
        return baseJson.substring(0, baseJson.length()-1) + ",\"userList\":"+customersJson+"}";
    }

    static List<LSTCustomerInterface> getLstCustomerList(List<DCustomerSubs> matchedList){
        List<LSTCustomerInterface> lstCustomers = new ArrayList<>();
        for (DCustomerSubs c: matchedList){
            try{
                String customerId = c.getCustomer_Id();
                List<String> cards = DCustomerSubsDao.queryCards(customerId);
                LSTCustomerInterface lstCustomer = new LSTCustomerInterface();
                lstCustomer.setUserId(c.getCustomer_Id());
                lstCustomer.setSubscribeId(c.getSub_Request_Id());
                lstCustomer.getCardNoList().addAll(0, cards);
                lstCustomers.add(lstCustomer);
            }
            catch (Exception e){
                logger.error(e);
                logger.error(ExceptionLog.errorTrackSpace(e));
            }
        }
        return lstCustomers;
    }
}
