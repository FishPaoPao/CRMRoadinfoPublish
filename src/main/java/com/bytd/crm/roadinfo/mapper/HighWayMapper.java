package com.bytd.crm.roadinfo.mapper;

public interface HighWayMapper {
    /**
     * 根据高速公路id查询高速公路名称
     */

    String queryHighwayName(int id) throws Exception;
    int queryHighwayStandardCode(int highwayId) throws Exception;
}
