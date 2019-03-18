package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.entities.DCustomerSubs;
import com.bytd.crm.roadinfo.entities.DSubsPushInfo;
import com.bytd.crm.roadinfo.mapper.DCustomerSubsMapper;
import com.bytd.crm.roadinfo.mapper.DSubsPushHistoryMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class DCustomerSubsDao {

    private static SqlConfiguration sqlConfiguration;

    public static List<DCustomerSubs> queryCustomerSubs() throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        List<DCustomerSubs> tempList =
                sqlConfiguration.getSqlSession().getMapper(DCustomerSubsMapper.class).queryCustomerSubs();
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }

    public static List<String> queryCards(String userId) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        List<String> tempList = sqlConfiguration.getSqlSession().getMapper(DCustomerSubsMapper.class).queryCards(userId);
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }

    public static List<DCustomerSubs> queryCustomerSubsById(List<BigInteger> ids) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        List<DCustomerSubs> tempList =
                sqlConfiguration.getSqlSession().getMapper(DCustomerSubsMapper.class).queryCustomerSubsById(ids);
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }
    public static void insertSubsPushInfo(String url, Map<String, Object> paramsMap, String subsJson) throws Exception {
        Gson gson = new GsonBuilder().create();
        DSubsPushInfo pushInfo = new DSubsPushInfo();
        pushInfo.setUrl(url);
        pushInfo.setParams_Map(gson.toJson(paramsMap));
        pushInfo.setJson(subsJson);
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DSubsPushHistoryMapper.class).insertSubsPushInfo(pushInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

}
