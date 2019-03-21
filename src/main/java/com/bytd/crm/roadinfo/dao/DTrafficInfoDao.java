package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.entities.DTrafficInfo;
import com.bytd.crm.roadinfo.mapper.DTrafficInfoMapper;

import java.util.List;

public class DTrafficInfoDao {

    private static SqlConfiguration sqlConfiguration;

    /**
     * 向路况事件信息表中插入数据
     * @param list 路况事件信息
     * @throws Exception
     */
    public static void insertTrafficInfo(List<DTrafficInfo> list) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).insertTrafficInfo(list);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static List<DTrafficInfo> queryInvalidTrafficInfo(List<DTrafficInfo> dTrafficInfos) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        List<DTrafficInfo> tempList =
                sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).queryInvalidTrafficInfo(dTrafficInfos);
        sqlConfiguration.getSqlSession().close();
        return tempList;
    }

    public static DTrafficInfo queryValidTrafficInfoById(int id) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        DTrafficInfo dTrafficInfo =
                sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).queryValidTrafficInfoById(id);
        sqlConfiguration.getSqlSession().close();
        return dTrafficInfo;
    }

    public static void updateTrafficInfoStatus(List<DTrafficInfo> dTrafficInfos) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).updateTrafficInfoStatus(dTrafficInfos);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }

    public static void insert(DTrafficInfo dTrafficInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).insert(dTrafficInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }


    public static void updateStatus(DTrafficInfo dTrafficInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        sqlConfiguration.getSqlSession().getMapper(DTrafficInfoMapper.class).updateStatus(dTrafficInfo);
        sqlConfiguration.getSqlSession().commit();
        sqlConfiguration.getSqlSession().close();
    }
}
