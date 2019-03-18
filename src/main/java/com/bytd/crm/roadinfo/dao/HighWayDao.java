package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.mapper.HighWayMapper;

public class HighWayDao {

    private static SqlConfiguration sqlConfiguration;

    public static String queryHighwayName(int highwayId) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        String highwayName =
                sqlConfiguration.getSqlSession().getMapper(HighWayMapper.class).queryHighwayName(highwayId);
        sqlConfiguration.getSqlSession().close();
        return highwayName;
    }

    public static int queryHighwayStandardCode(int highwayId) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        int standardCode =
                sqlConfiguration.getSqlSession().getMapper(HighWayMapper.class).queryHighwayStandardCode(highwayId);
        sqlConfiguration.getSqlSession().close();
        return standardCode;
    }

}
