package com.bytd.crm.roadinfo.entities;

import com.bytd.crm.roadinfo.core.ConstManager;
import com.bytd.crm.roadinfo.core.ExceptionLog;
import com.bytd.crm.roadinfo.dao.HighWayDao;
import com.bytd.crm.roadinfo.dao.MileagePointDao;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LSTRoadInfoInterface {
    /**
     * 消息ID
     */
    private String id;
    /**
     * 区域：区域ID
     */
    private Area area;

    public static class Area{
        /**
         * 默认为北京地区区域id 101010100
         */
        private String areaid = "101010100";

        public Area() { }

        public Area(String areaid) {
            this.areaid = areaid;
        }

        public String getAreaId() {
            return areaid;
        }

        public void setAreaId(String areaid) {
            this.areaid = areaid;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "areaid='" + areaid + '\'' +
                    '}';
        }
    }

    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 严重程度, 默认为ORDINARY
     */
    private String orderOfSeverity = "ORDINARY";
    /**
     * 消息状态
     */
    private String status;
    //消息类型
    /**
     * 消息类型
     */
    private String eventOfCategory;
    /**
     * 有效时间，默认为最长时长
     */
    private Long validity = dateToTimeStamp("2099/12/31 23:59:59");
    /**
     * 路况来源
     */
    private String source = "BOYOTOD";
    /**
     * 高速公路名称
     */
    private String highWayName;
    /**
     * 开始时间,消息类型为占道施工时填写
     */
    private Long startTime;
    /**
     * 结束时间,消息类型为占道施工时填写
     */
    private Long endTime;
    /**
     * 方向
     */
    private String direction;
    /**
     * 坐标列表
     */
    private List<Coordinate> coordinateList;
    public static class Coordinate{
        private String x;
        private String y;
        /**
         * 坐标系类型
         */
        private String coordinateSystem = "WGS84";

        public Coordinate(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public void setCoordinateSystem(String coordinateSystem) {
            this.coordinateSystem = coordinateSystem;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderOfSeverity() {
        return orderOfSeverity;
    }

    public void setOrderOfSeverity(String oderOfSeverity) {
        this.orderOfSeverity = oderOfSeverity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEventOfCategory() {
        return eventOfCategory;
    }

    public void setEventOfCategory(String eventOfCategory) {
        this.eventOfCategory = eventOfCategory;
    }

    public Long getValidity() {
        return validity;
    }

    public void setValidity(Long validity) {
        this.validity = validity;
    }

    public String getSrouce() {
        return source;
    }

    public void setSrouce(String source) {
        this.source = source;
    }

    public String getHighwayName() {
        return highWayName;
    }

    public void setHighwayName(String highwayName) {
        this.highWayName = highwayName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    /**
     * 将输入的日期格式数据转换为时间戳
     * @param str 日期格式 yyyy/MM/dd HH:mm:ss
     * @return 时间戳
     */
    public Long dateToTimeStamp(String str){
        Date date = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                date = df.parse(str);
            }
            catch (Exception e){
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = df.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

    /**
     * 采用正则表达式解析占道施工信息d_constructinfo表中的TIME字段
     * Time字段格式 开始时间+至+结束时间 0时至24时
     * @param time 影响时间
     * @return 起止时间 list[0]:开始时间,list[1]:终止时间，null,输入参数格式有误
     */
    public List<String> parseTime(String time) {
        if (!"".equals(time) && time != null) {
            String[] temp = time.split("至");
            List<String> timeList = new ArrayList<>();
            for (String ele : temp) {
                ele.replace("次日", "");
                String pattern = "(\\d+)\\D";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(ele);
                int count = 0;
                String timeStr = "";
                while (m.find()) {
                    count++;
                    timeStr += m.group(1) + ":";
                }
                switch (count) {
                    case 1:
                        timeStr += "00:00";
                        break;
                    case 2:
                        timeStr += "00";
                        break;
                    case 3:
                        timeStr = timeStr.substring(0, timeStr.length() - 1);
                    default:
                }
                timeList.add(timeStr);
            }
            return timeList;
        }
        return null;
    }
//    public List<String> parseTime(String time){
//        String pattern = "^(\\d+)\\D+(\\d+)\\D+";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(time);
//        if(m.find()){
//            List<String> timeList = new ArrayList<>();
//            timeList.add(m.group(1) + ":00:00");
//            timeList.add(m.group(2) + ":00:00");
//            return timeList;
//        }
//        return null;
//    }

    public static LSTRoadInfoInterface makeParams(DRoadInfo dRoadInfo){
        LSTRoadInfoInterface lstRoadInfoInterface = new LSTRoadInfoInterface();
        Logger logger = Logger.getLogger(LSTRoadInfoInterface.class);
        //消息ID
        lstRoadInfoInterface.setId(String.valueOf(dRoadInfo.getInfo_ID()));
        //区域,区域ID为默认值“101010100”
        lstRoadInfoInterface.setArea(new LSTRoadInfoInterface.Area());
        //消息标题：地理位置 + 消息类型
        String title = dRoadInfo.getLocation() + dRoadInfo.getType_ID();
        lstRoadInfoInterface.setTitle(title);
        //消息内容
        lstRoadInfoInterface.setContent(dRoadInfo.getShow_Info());
        //消息状态
        lstRoadInfoInterface.setStatus(ConstManager.StatusConts.getPhraseByCode(dRoadInfo.getStatus()));
        //消息类型
        lstRoadInfoInterface.setEventOfCategory(
                ConstManager.EventOfCategory.getPhraseByCode(dRoadInfo.getType_ID()));
        //高速公路名称
        //获取高速公路名称
        try {
            String highwayName = HighWayDao.queryHighwayName(dRoadInfo.getHighway_ID());
            lstRoadInfoInterface.setHighwayName(highwayName);
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }
        //方向
        lstRoadInfoInterface.setDirection(
                ConstManager.DirectionConts.getPhraseByCode(dRoadInfo.getDirection()));
        //获取起始桩号对应的GIS坐标
        List<Coordinate> coordinates = new ArrayList<>();
        try {
            MileagePoint mileagePoint = MileagePointDao.queryStartGIS(dRoadInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("起始桩号数据异常 " +
                         "Highway_ID: " + dRoadInfo.getHighway_ID() +
                         ",Direction: " + dRoadInfo.getDirection() +
                         ",Stake: " + dRoadInfo.getStake());
            logger.warn(e);
        }
        //获取结束桩号对应的GIS坐标
        try {
            MileagePoint mileagePoint = MileagePointDao.queryEndGIS(dRoadInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("结束桩号数据异常 " +
                         "Highway_ID: " + dRoadInfo.getHighway_ID() +
                         ",Direction: " + dRoadInfo.getDirection() +
                         ",EndStake: " + dRoadInfo.getEndStake());
            logger.warn(e);
        }
        lstRoadInfoInterface.setCoordinateList(coordinates);
        return lstRoadInfoInterface;
    }

    public static LSTRoadInfoInterface makeParams(DConstructInfo dConstructInfo){
        LSTRoadInfoInterface lstRoadInfoInterface = new LSTRoadInfoInterface();
        Logger logger = Logger.getLogger(LSTRoadInfoInterface.class);
        lstRoadInfoInterface.setId(String.valueOf(dConstructInfo.getInfo_ID()));
        lstRoadInfoInterface.setArea(new LSTRoadInfoInterface.Area());
        lstRoadInfoInterface.setTitle(dConstructInfo.getLocation() +
                ConstManager.EventOfCategory.ROADCONSTRUCTION.getEventOfCategoryCode());
        lstRoadInfoInterface.setContent(dConstructInfo.getShow_Info());
        lstRoadInfoInterface.setStatus(ConstManager.StatusConts.getPhraseByCode(dConstructInfo.getStatus()));
        lstRoadInfoInterface.setEventOfCategory(
                ConstManager.EventOfCategory.ROADCONSTRUCTION.getEventOfCategoryDesc());
        //高速公路名称
        try {
            String highwayName = HighWayDao.queryHighwayName(dConstructInfo.getHighway_ID());
            lstRoadInfoInterface.setHighwayName(highwayName);
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }
        //dCoustructInfo.getTime()返回d_constructinfo表中Time字段:开始时间+“至”+结束时间
        //开始时间
        List<String> timeList = lstRoadInfoInterface.parseTime(dConstructInfo.getTime());
        String startDateTime = dConstructInfo.getBegin_Date().split(" ")[0] + " " +timeList.get(0);
        lstRoadInfoInterface.setStartTime(lstRoadInfoInterface.dateToTimeStamp(startDateTime));
        //结束时间
        String endDateTime = dConstructInfo.getEnd_Date().split(" ")[0] + " " + timeList.get(1);
        lstRoadInfoInterface.setEndTime(lstRoadInfoInterface.dateToTimeStamp(endDateTime));
        //方向
        lstRoadInfoInterface.setDirection(
                ConstManager.DirectionConts.getPhraseByCode(dConstructInfo.getDirection()));
        //桩号列表
        //获取起始桩号对应的GIS坐标
        List<Coordinate> coordinates = new ArrayList<>();
        try {
            MileagePoint mileagePoint = MileagePointDao.queryStartGIS2(dConstructInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("起始桩号数据异常 " +
                         "Highway_ID: " + dConstructInfo.getHighway_ID() +
                         ",Direction: " + dConstructInfo.getDirection() +
                         ",Stake: " + dConstructInfo.getStake().split("-")[0]);
            logger.warn(e);
        }
        //获取结束桩号对应的GIS坐标
        try {
            MileagePoint mileagePoint= MileagePointDao.queryEndGIS2(dConstructInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("结束桩号数据异常 " +
                         "Highway_ID: " + dConstructInfo.getHighway_ID() +
                         ",Direction: " + dConstructInfo.getDirection() +
                         ",Stake: " + dConstructInfo.getStake().split("-")[1]);
            logger.warn(e);
        }
        lstRoadInfoInterface.setCoordinateList(coordinates);
        return lstRoadInfoInterface;
    }

    public static LSTRoadInfoInterface makeParams(DTrafficInfo dTrafficInfo){
        LSTRoadInfoInterface lstRoadInfoInterface = new LSTRoadInfoInterface();
        Logger logger = Logger.getLogger(LSTRoadInfoInterface.class);
        lstRoadInfoInterface.setId(String.valueOf(dTrafficInfo.getInfo_ID()));
        lstRoadInfoInterface.setArea(new LSTRoadInfoInterface.Area());
        lstRoadInfoInterface.setTitle(dTrafficInfo.getLocation() + dTrafficInfo.getType_ID());
        lstRoadInfoInterface.setContent(dTrafficInfo.getShow_Info());
        lstRoadInfoInterface.setStatus(ConstManager.StatusConts.getPhraseByCode(dTrafficInfo.getStatus()));
        lstRoadInfoInterface.setEventOfCategory(
                ConstManager.EventOfCategory.getPhraseByCode(dTrafficInfo.getType_ID()));
        //高速公路名称
        try {
            String highwayName = HighWayDao.queryHighwayName(dTrafficInfo.getHighway_ID());
            lstRoadInfoInterface.setHighwayName(highwayName);
        } catch (Exception e) {
            logger.error(e);
            logger.error(ExceptionLog.errorTrackSpace(e));
        }
        //方向
        lstRoadInfoInterface.setDirection(
                ConstManager.DirectionConts.getPhraseByCode(dTrafficInfo.getDirection()));
        //桩号列表
        //获取起始桩号对应的GIS坐标
        List<Coordinate> coordinates = new ArrayList<>();
        try {
            MileagePoint mileagePoint = MileagePointDao.queryStartGIS3(dTrafficInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("起始桩号数据异常 " +
                    "Highway_ID: " + dTrafficInfo.getHighway_ID() +
                    ",Direction: " + dTrafficInfo.getDirection() +
                    ",Stake: " + dTrafficInfo.getStake());
            logger.warn(e);
        }
        //获取结束桩号对应的GIS坐标
        try {
            MileagePoint mileagePoint= MileagePointDao.queryEndGIS3(dTrafficInfo);
            Coordinate coordinate = new Coordinate(mileagePoint.getSmX(), mileagePoint.getSmY());
            coordinates.add(coordinate);
        } catch (Exception e) {
            logger.warn("结束桩号数据异常 " +
                    "Highway_ID: " + dTrafficInfo.getHighway_ID() +
                    ",Direction: " + dTrafficInfo.getDirection() +
                    ",Stake: " + dTrafficInfo.getEndStake());
            logger.warn(e);
        }
        lstRoadInfoInterface.setCoordinateList(coordinates);
        return lstRoadInfoInterface;
    }
}
