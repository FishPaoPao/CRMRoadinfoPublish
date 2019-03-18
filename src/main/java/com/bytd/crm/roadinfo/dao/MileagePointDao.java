package com.bytd.crm.roadinfo.dao;

import com.bytd.crm.roadinfo.entities.DConstructInfo;
import com.bytd.crm.roadinfo.entities.DRoadInfo;
import com.bytd.crm.roadinfo.entities.DTrafficInfo;
import com.bytd.crm.roadinfo.entities.MileagePoint;
import com.bytd.crm.roadinfo.mapper.MileagePointMapper;

/**
 * @author bytd
 */
public class MileagePointDao {

    private static SqlConfiguration sqlConfiguration;

    public static MileagePoint queryStartGIS(DRoadInfo dRoadInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryStartGIS(dRoadInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }

    public static MileagePoint queryEndGIS(DRoadInfo dRoadInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryEndGIS(dRoadInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }

    public static MileagePoint queryStartGIS2(DConstructInfo dConstructInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryStartGIS2(dConstructInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }

    public static MileagePoint queryEndGIS2(DConstructInfo dConstructInfo) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryEndGIS2(dConstructInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }

    public static MileagePoint queryStartGIS3(DTrafficInfo dTrafficInfo) throws Exception {
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryStartGIS3(dTrafficInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }

    public static MileagePoint queryEndGIS3(DTrafficInfo dTrafficInfo) throws Exception{
        sqlConfiguration = SqlConfiguration.getInstance();
        MileagePointMapper mapper
                = sqlConfiguration.getSqlSession().getMapper(MileagePointMapper.class);
        MileagePoint mileagePoint = mapper.queryEndGIS3(dTrafficInfo);
        sqlConfiguration.getSqlSession().close();
        return mileagePoint;
    }
}
