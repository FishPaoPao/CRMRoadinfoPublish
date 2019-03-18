package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.mapper.DSubsSectionMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName DSubsSectionDao
 * @Description TODO
 * @Author yuheng
 * @Date 20181225
 * @Version 1.0
 **/
public class DSubsSectionDao {

    private static SqlConfiguration sqlConfiguration;

    public static List<BigInteger> queryMatchSubsSection(int highwayStandardId, int routeDirection,int stake, int endStake ) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        List<BigInteger> subsIds = sqlConfiguration.getSqlSession()
                .getMapper(DSubsSectionMapper.class)
                .queryMatchSubsSection(highwayStandardId, routeDirection, stake, endStake);
        sqlConfiguration.getSqlSession().close();
        return subsIds;
    }
}
