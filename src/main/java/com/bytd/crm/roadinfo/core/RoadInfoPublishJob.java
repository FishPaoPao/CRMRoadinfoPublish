package com.bytd.crm.roadinfo.core;

import com.bytd.crm.roadinfo.dao.DConstructInfoDao;
import com.bytd.crm.roadinfo.dao.DRoadInfoDao;
import com.bytd.crm.roadinfo.dao.DTrafficInfoDao;
import com.bytd.crm.roadinfo.entities.*;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bytd
 */
//@DisallowConcurrentExecution禁止Quartz作业的并发执行逻辑
@DisallowConcurrentExecution
public class RoadInfoPublishJob implements Job {

    private Logger logger = Logger.getLogger(RoadInfoPublishJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            List<DRoadInfo> traffics = transmitRoadInfoData();
            //以下for循环逻辑：将广播路况信息与订阅消息进行匹配
            for (DRoadInfo t : traffics) {
                SubscriptionFilter.matchSubscriptionAndPush(t);
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }

        try {
            List<DConstructInfo> constructInfos = transmitConstructInfoData();
            for (DConstructInfo t : constructInfos) {
                SubscriptionFilter.matchSubscriptionAndPush(t);
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }

        try {
            List<DTrafficInfo> events = transmitTrafficInfo();
            for (DTrafficInfo t : events) {
                SubscriptionFilter.matchSubscriptionAndPush(t);
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }
    }

    /**
     * 获取和转发路况信息数据
     *
     * @throws Exception
     */
    private List<DRoadInfo> transmitRoadInfoData() throws Exception {
        WebDataProxy webDataProxy = new WebDataProxy();
        Map<String, Object> paramsMap = new HashMap<>();
        //从信息中心获取数据
        String resultString = webDataProxy.getData(PropertiesLoader.ROAD_URL, null, "utf-8");
        List<DRoadInfo> roadInfoList = new ArrayList<>();
        if (resultString != null && resultString.length() > 0) {
            List<DRoadInfo> jsonToRoadInfoList = JsonHelper.jsonToObject(resultString, DRoadInfo.class);
            //进行数据过滤，并返回将要推送的数据集合(包含上架数据集合，下架数据集合)
            roadInfoList = roadInfoDataProcess(jsonToRoadInfoList);
            //发起post请求，将路况信息数据转发至乐速通
            logger.info("待转发路况信息: " + "count=" + roadInfoList.size());
            if (roadInfoList.size() > 0) {
                for (DRoadInfo dRoadInfo : roadInfoList) {
                    LSTRoadInfoInterface lstRoadInfoInterface = LSTRoadInfoInterface.makeParams(dRoadInfo);
                    paramsMap.put("seqNo", System.currentTimeMillis());
                    ResponseMsg responseMsg =
                            webDataProxy.sendObject(PropertiesLoader.LESUTONG_BROAD_URL, paramsMap, lstRoadInfoInterface);
                    if("success".equals(responseMsg.getError().getErrorInfo().toLowerCase())) {
                        if(dRoadInfo.getStatus() == 0) {
                            //将dRoadInfo插入数据库表d_roadinfo
                            DRoadInfoDao.insert(dRoadInfo);
                        } else {
                            //更新d_roadinfo表中的数据状态
                            DRoadInfoDao.updateStatus(dRoadInfo);
                        }
                    }
                }
            }
        }
        return roadInfoList;
    }

    /**
     * 获取和转发施工消息
     *
     * @throws Exception
     */
    private List<DConstructInfo> transmitConstructInfoData() throws Exception {
        WebDataProxy webDataProxy = new WebDataProxy();
        Map<String, Object> paramsMap = new HashMap<>();
        //从信息中心获取数据
        String resultString = webDataProxy.getData(PropertiesLoader.CONST_URL, null, "utf-8");
//        resultString = "[{\"Author_ID\":1,\"Begin_Date\":\"2018\\/8\\/14 0:00:00\",\"Begin_Time\":\"\",\"Created_Time\":\"2018/8/14 2:04:43\",\"Direction\":1,\"End_Date\":\"2018\\/8\\/15 0:00:00\",\"End_Time\":\"\",\"Fangwei\":0,\"Highway_ID\":5,\"ID\":19558,\"Info_ID\":13363,\"Location\":\"羊山隧道至新农村桥\",\"Publish_Time\":\"2018/8/14 16:30:06\",\"Reason\":\"占道施工\",\"Recover_Date\":\"\",\"Show_Info\":\"10时至16时，京承高速进京方向K74羊山隧道至K68+400新农村桥占道施工。\",\"Source\":0,\"Stake\":\"74000-68400,\",\"Status\":0,\"StickTime\":\"\",\"Time\":\"10时至16时\"}]";
        List<DConstructInfo> dConstructInfos = new ArrayList<>();
        if (resultString != null && resultString.length() > 0) {
            List<DConstructInfo> jsonToConstructInfoList = JsonHelper.jsonToObject(resultString, DConstructInfo.class);
            //进行数据过滤，并返回将要推送的数据集合
            dConstructInfos = constructInfoDataProcess(jsonToConstructInfoList);
            //发起post请求，将路况信息数据转发至乐速通
            logger.info("待转发施工信息: " + "count=" + dConstructInfos.size());
            ArrayList<Integer> tempList = new ArrayList();
            for(DConstructInfo dConstructInfo : dConstructInfos) {
                tempList.add(dConstructInfo.getID());
            }
            logger.info("待转发消息id:" + tempList);
            if (dConstructInfos.size() > 0) {
                for (DConstructInfo dConstructInfo : dConstructInfos) {
                    LSTRoadInfoInterface lstRoadInfoInterface = LSTRoadInfoInterface.makeParams(dConstructInfo);
                    paramsMap.put("seqNo", System.currentTimeMillis());
                    ResponseMsg responseMsg =
                            webDataProxy.sendObject(PropertiesLoader.LESUTONG_BROAD_URL, paramsMap, lstRoadInfoInterface);
                    if("success".equals(responseMsg.getError().getErrorInfo().toLowerCase())) {
                        if (dConstructInfo.getStatus() == 0) {
                            DConstructInfoDao.insert(dConstructInfo);
                        } else {
                            DConstructInfoDao.updateStatusById(dConstructInfo);
                        }
                    }
                }
            }
        }
        return dConstructInfos;
    }

    /**
     * 获取和转发交通事件信息
     *
     * @throws Exception
     */
    private List<DTrafficInfo> transmitTrafficInfo() throws Exception {
        WebDataProxy webDataProxy = new WebDataProxy();
        Map<String, Object> paramsMap = new HashMap<>();
        //从信息中心获取路况事件信息
        String resultString = webDataProxy.getData(PropertiesLoader.TRAFFIC_URL, null, "utf-8");
        List<DTrafficInfo> dTrafficInfos = new ArrayList<>();
        if (resultString != null && resultString.length() > 0) {
            List<DTrafficInfo> jsonToTrafficInfoList = JsonHelper.jsonToObject(resultString, DTrafficInfo.class);
            //进行数据过滤，并返回将要推送的数据集合
            dTrafficInfos = trafficInfoDataProcess(jsonToTrafficInfoList);
            logger.info("待转发事件信息: " + "count=" + dTrafficInfos.size());
            if (dTrafficInfos.size() > 0) {
                for (DTrafficInfo dTrafficInfo : dTrafficInfos) {
                    LSTRoadInfoInterface lstRoadInfoInterface = LSTRoadInfoInterface.makeParams(dTrafficInfo);
                    paramsMap.put("seqNo", System.currentTimeMillis());
                    ResponseMsg responseMsg =
                            webDataProxy.sendObject(PropertiesLoader.LESUTONG_BROAD_URL, paramsMap, lstRoadInfoInterface);
                    if("success".equals(responseMsg.getError().getErrorInfo().toLowerCase())) {
                        if(dTrafficInfo.getStatus() == 0) {
                            //将dTrafficInfo插入数据库
                            DTrafficInfoDao.insert(dTrafficInfo);
                        } else {
                            //更新本地数据库d_trafficinfo表中的数据状态为“1”：失效
                            DTrafficInfoDao.updateStatus(dTrafficInfo);
                        }
                    }
                }
            }
        }
        return dTrafficInfos;
    }

    /**
     * DRoadInfo相关数据处理,将需要上架及下架的数据进行整合
     *
     * @param infoCenterDataList 信息中心返回数据
     * @return 整合后的数据集合，包含上架数据和下架数据
     * @throws Exception
     */
    private List<DRoadInfo> roadInfoDataProcess(List<DRoadInfo> infoCenterDataList) throws Exception {
        List<DRoadInfo> invalidRoadInfos;
        List<DRoadInfo> validRoadInfos = new ArrayList<>();
        List<DRoadInfo> commonList = new ArrayList<>();
        List<DRoadInfo> removeList = new ArrayList<>();
        //修正一下数据错误
        for (DRoadInfo i : infoCenterDataList) {
            if (i.getOccur_Time().equals("")) {
                i.setOccur_Time(i.getPublish_Time());
            }
            if (i.getStickTime().equals("")) {
                i.setStickTime(i.getPublish_Time());
            }
        }
        //生成下架数据集合
        invalidRoadInfos = DRoadInfoDao.queryInvalidRoadInfo(infoCenterDataList);
        //更新下架数据集合中 DRoadInfo.status 字段为1，表示数据失效
        if (invalidRoadInfos != null && invalidRoadInfos.size() > 0) {
            for (DRoadInfo dRoadInfo : invalidRoadInfos) {
                dRoadInfo.setStatus(1);
            }
            //根据下架数据集合更新数据库表d_roadinfo,将相应数据status字段更新为1，表示数据失效
//            DRoadInfoDao.updateRoadInfoStatus(invalidRoadInfos);
            //将invalidRoadinfos集合中Block为0或1的数据移除，拥堵和缓行的数据从d_trafficinfo表中取
            for (DRoadInfo dRoadInfo : invalidRoadInfos) {
                if (dRoadInfo.getBlock() == 0 || dRoadInfo.getBlock() == 1) {
                    removeList.add(dRoadInfo);
                }
            }
            for (DRoadInfo dRoadInfo : removeList) {
                invalidRoadInfos.remove(dRoadInfo);
            }
            commonList.addAll(invalidRoadInfos);
        }

        //生成持久化及上架数据集合
        for (DRoadInfo dRoadInfo : infoCenterDataList) {
            if (DRoadInfoDao.queryValidRoadInfoById(dRoadInfo.getID()) == null) {
                validRoadInfos.add(dRoadInfo);
            }
        }
        removeList.clear();
        if (validRoadInfos.size() > 0) {
//            DRoadInfoDao.insertRoadInfo(validRoadInfos);
            //将validRoadInfos集合中Bolck为0或1的数据移除
            for (DRoadInfo dRoadInfo : validRoadInfos) {
                if (dRoadInfo.getBlock() == 0 || dRoadInfo.getBlock() == 1) {
                    removeList.add(dRoadInfo);
                }
            }
            for (DRoadInfo dRoadInfo : removeList) {
                validRoadInfos.remove(dRoadInfo);
            }
            commonList.addAll(validRoadInfos);
        }
        return commonList;
    }

    /**
     * DConstructInfo相关数据处理,将需要上架及下架的数据进行整合
     *
     * @param infoCenterDataList 信息中心返回数据
     * @return 整合后的数据集合，包含上架数据和下架数据
     * @throws Exception
     */
    private List<DConstructInfo> constructInfoDataProcess(List<DConstructInfo> infoCenterDataList) throws Exception {
        List<DConstructInfo> invalidConstructInfos;
        List<DConstructInfo> validConstructInfos = new ArrayList<>();
        List<DConstructInfo> commonList = new ArrayList<>();
        //修正一下数据错误
        for (DConstructInfo i : infoCenterDataList) {
            if (i.getRecover_Date().equals("")) {
                i.setRecover_Date(i.getPublish_Time());
            }
            if (i.getStickTime().equals("")) {
                i.setStickTime(i.getPublish_Time());
            }
        }

        //生成下架数据集合
        invalidConstructInfos = DConstructInfoDao.queryInvalidConstructInfo(infoCenterDataList);
        //更新下架数据集合中 DConstructInfo.status 字段为1，表示数据失效
        if (invalidConstructInfos != null && invalidConstructInfos.size() > 0) {
            for (DConstructInfo dConstructInfo : invalidConstructInfos) {
                dConstructInfo.setStatus(1);
            }
            commonList.addAll(invalidConstructInfos);
            //根据下架数据集合更新数据库表d_ConstructInfo,将相应数据status字段更新为1，表示数据失效
//            DConstructInfoDao.updateConstructInfoStatus(invalidConstructInfos);
        }
        //生成持久化及上架数据集合
        for (DConstructInfo dConstructInfo : infoCenterDataList) {
            if (DConstructInfoDao.queryValidConstructInfoById(dConstructInfo.getID()) == null) {
                validConstructInfos.add(dConstructInfo);
            }
        }
        if (validConstructInfos.size() > 0) {
//            DConstructInfoDao.insertConstructInfo(validConstructInfos);
            commonList.addAll(validConstructInfos);
        }
        return commonList;
    }

    /**
     * DTrafficinfo,将需要上架及下架的数据进行整合
     *
     * @param infoCenterDataList 信息中心返回数据
     * @return 整合后的数据集合，包含上架数据和下架数据
     * @throws Exception
     */
    private List<DTrafficInfo> trafficInfoDataProcess(List<DTrafficInfo> infoCenterDataList) throws Exception {
        List<DTrafficInfo> invalidTrafficInfos;
        List<DTrafficInfo> validTrafficInfos = new ArrayList<>();
        List<DTrafficInfo> commonList = new ArrayList<>();
        //修正一下数据错误
        for (DTrafficInfo i : infoCenterDataList) {
            if (i.getOccur_Time().equals("")) {
                i.setOccur_Time(i.getPublish_Time());
            }
            if (i.getStickTime().equals("")) {
                i.setStickTime(i.getPublish_Time());
            }
        }
        //生成下架数据集合
        invalidTrafficInfos = DTrafficInfoDao.queryInvalidTrafficInfo(infoCenterDataList);
        //更新下架数据集合中 DTrafficInfo.status 字段为1，表示数据失效
        if (invalidTrafficInfos != null && invalidTrafficInfos.size() > 0) {
            for (DTrafficInfo dTrafficInfo : invalidTrafficInfos) {
                dTrafficInfo.setStatus(1);
            }
            commonList.addAll(invalidTrafficInfos);
            //更新本地数据库中事件信息状态为失效
//            DTrafficInfoDao.updateTrafficInfoStatus(invalidTrafficInfos);
        }
        //生成持久化上架数据集合
        for (DTrafficInfo dTrafficInfo : infoCenterDataList) {
            if (DTrafficInfoDao.queryValidTrafficInfoById(dTrafficInfo.getID()) == null) {
                validTrafficInfos.add(dTrafficInfo);
            }
        }

        if (validTrafficInfos.size() > 0) {
//            DTrafficInfoDao.insertTrafficInfo(validTrafficInfos);
            commonList.addAll(validTrafficInfos);
        }
        return commonList;
    }
}
