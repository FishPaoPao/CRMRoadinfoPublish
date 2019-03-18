package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DConstructInfo;

import java.util.List;

public interface DConstructInfoMapper {

    /**
     * 向d_construct中新增数据
     */
    void insertConstructInfo(List<DConstructInfo> dConstructInfos) throws Exception;

    /**
     * 查询d_construct中失效的数据
     */
    List<DConstructInfo> queryInvalidConstructInfo(List<DConstructInfo> dConstructInfos) throws Exception;

    /**
     * 根据id查询d_construct中的有效数据
     */
    DConstructInfo queryValidConstructInfoById(int id) throws Exception;

    /**
     * 更新下架数据状态为1:失效
     */
    void updateConstructInfoStatus(List<DConstructInfo> dConstructInfos)throws Exception;
}
