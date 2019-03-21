package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DTrafficInfo;

import java.util.List;

public interface DTrafficInfoMapper {
    /**
     * 向D_TrafficInfo表中插入路况事件信息数据
     */
    void insertTrafficInfo(List<DTrafficInfo> list) throws Exception;

    /**
     * 查询d_roadinfo中失效的数据
     */
    List<DTrafficInfo> queryInvalidTrafficInfo(List<DTrafficInfo> dTrafficInfos) throws Exception;

    /**
     * 根据id查询d_roadinfo中的有效数据
     */
    DTrafficInfo queryValidTrafficInfoById(int id) throws Exception;

    /**
     * 跟新下架数据状态为1:失效
     */
    void updateTrafficInfoStatus(List<DTrafficInfo> dTrafficInfos) throws Exception;

    void insert(DTrafficInfo dTrafficInfo) throws Exception;

    void updateStatus(DTrafficInfo dTrafficInfo) throws Exception;
}
