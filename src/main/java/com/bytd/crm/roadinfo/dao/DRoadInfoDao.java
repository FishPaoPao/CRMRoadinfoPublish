package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.entities.DRoadInfo;
import com.bytd.crm.roadinfo.mapper.DRoadInfoMapper;

import java.util.List;

public class DRoadInfoDao{

    private static SqlConfiguration sqlConfiguration;

    public static void insertRoadInfo(List<DRoadInfo> dRoadInfos) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).insertRoadInfo(dRoadInfos);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static List<DRoadInfo> queryInvalidRoadInfo(List<DRoadInfo> dRoadInfos) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        List<DRoadInfo> tempList =
                sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).queryInvalidRoadInfo(dRoadInfos);
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }

    public static DRoadInfo queryValidRoadInfoById(int id) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        DRoadInfo dRoadInfo =
                sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).queryValidRoadInfoById(id);
        sqlConfiguration.getSqlSession().close();
        return dRoadInfo;
    }

    public static void updateRoadInfoStatus(List<DRoadInfo> dRoadInfos) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).updateRoadInfoStatus(dRoadInfos);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }
    public static void insert(DRoadInfo dRoadInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).insert(dRoadInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static void updateStatus(DRoadInfo dRoadInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DRoadInfoMapper.class).updateStatus(dRoadInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }
}
