package com.bytd.crm.roadinfo.mapper;

import com.bytd.crm.roadinfo.entities.DConstructInfo;
import com.bytd.crm.roadinfo.entities.DRoadInfo;
import com.bytd.crm.roadinfo.entities.DTrafficInfo;
import com.bytd.crm.roadinfo.entities.MileagePoint;

public interface MileagePointMapper {
    /**
     * 查询GIS坐标
     */
    MileagePoint queryStartGIS(DRoadInfo dRoadInfo) throws Exception;
    MileagePoint queryEndGIS(DRoadInfo dRoadInfo) throws Exception;
    MileagePoint queryStartGIS2(DConstructInfo dConstructInfo) throws Exception;
    MileagePoint queryEndGIS2(DConstructInfo dconstructInfo) throws Exception;
    MileagePoint queryStartGIS3(DTrafficInfo dTrafficInfo) throws Exception;
    MileagePoint queryEndGIS3(DTrafficInfo dTrafficInfo) throws Exception;
}
