package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DSubsSection;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface DSubsSectionMapper {
    int insert(DSubsSection record);

    int insertSelective(DSubsSection record) throws Exception;

    /**
     * 查询与路况信息相匹配的订阅路段
     * @param highwayStandardId
     * @param routeDirection
     * @param stake
     * @param endStake
     * @return
     */
    List<BigInteger> queryMatchSubsSection(@Param("highwayStandardId") int highwayStandardId,
                                           @Param("routeDirection") int routeDirection,
                                           @Param("stake") int stake,
                                           @Param("endStake") int endStake) throws Exception;
}