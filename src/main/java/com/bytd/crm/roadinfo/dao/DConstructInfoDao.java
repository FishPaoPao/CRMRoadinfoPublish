package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.entities.DConstructInfo;
import com.bytd.crm.roadinfo.mapper.DConstructInfoMapper;

import java.util.List;

/**
 * @author bytd
 */
public class DConstructInfoDao {

    private static SqlConfiguration sqlConfiguration;

    public static void insertConstructInfo(List<DConstructInfo> dConstructInfos) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).insertConstructInfo(dConstructInfos);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static List<DConstructInfo> queryInvalidConstructInfo(List<DConstructInfo> dConstructInfos) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        List<DConstructInfo> tempList =
                sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).queryInvalidConstructInfo(dConstructInfos);
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }

    public static DConstructInfo queryValidConstructInfoById(int id) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        DConstructInfo dConstructInfo =
                sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).queryValidConstructInfoById(id);
        sqlConfiguration.getSqlSession().close();
        return dConstructInfo;
    }

    public static void updateConstructInfoStatus(List<DConstructInfo> dConstructInfos) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).updateConstructInfoStatus(dConstructInfos);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static void insert(DConstructInfo dConstructInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).insert(dConstructInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static void updateStatusById(DConstructInfo dConstructInfo) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DConstructInfoMapper.class).updateStatusById(dConstructInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }
}
