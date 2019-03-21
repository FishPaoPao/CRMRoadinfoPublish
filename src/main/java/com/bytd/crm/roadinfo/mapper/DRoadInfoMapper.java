package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DRoadInfo;

import java.util.List;

public interface DRoadInfoMapper {

    /**
     * 向d_roadinfo表中插入数据
     */

    void insertRoadInfo(List<DRoadInfo> dRoadInfos) throws Exception;

    /**
     * 查询d_roadinfo中失效的数据
     */
    List<DRoadInfo> queryInvalidRoadInfo(List<DRoadInfo> dRoadInfos) throws Exception;

    /**
     * 根据id查询d_roadinfo中的有效数据
     */
    DRoadInfo queryValidRoadInfoById(int id) throws Exception;

    /**
     * 跟新下架数据状态为1:失效
     */
    void updateRoadInfoStatus(List<DRoadInfo> dRoadInfos)throws Exception;

    void insert(DRoadInfo dRoadInfo) throws Exception;

    void updateStatus(DRoadInfo dRoadInfo) throws Exception;
}
