package com.bytd.crm.roadinfo.core;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author bytd
 */
public class PropertiesLoader {

    private static final String PROPERTIES_NAME = "RoadinfoPublish.xml";
    /**
     * 信息中心数据获取路况信息接口
     */
    public static String ROAD_URL;
    /**
     * 信息中心数据获取施工信息
     */
    public static String CONST_URL;
    /**
     * 信息中心事件数据接口
     */
    public static String TRAFFIC_URL;
    /**
     * 路况广播转发目标接口
     */
    public static String LESUTONG_BROAD_URL;

    /**
     * 订阅路况推送目标接口
     */
    public static String LESUTONG_SUBS_URL;
    /**
     * 定时任务延时启动时长，单位为SECONDS
     */
    public static int INITDELAY;
    /**
     * 定时任务执行周期,单位为SECONDS
     */
    public static int PERIOD;
    /**
     * 定时任务执行次数
     */
    public static int REPEATCOUNT;

    /**
     * 加载配置文件信息
     */
    public static void load(){
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream =
                    PropertiesLoader.class.getClassLoader().getResourceAsStream("config/" + PROPERTIES_NAME);
            properties.loadFromXML(resourceAsStream);
            ROAD_URL = properties.getProperty("Road");
            CONST_URL = properties.getProperty("Const");
            TRAFFIC_URL = properties.getProperty("Traffic");
            LESUTONG_BROAD_URL = properties.getProperty("LesutongBroad");
            LESUTONG_SUBS_URL = properties.getProperty("LesutongSubs");
            INITDELAY = Integer.parseInt(properties.getProperty("Initdelay"));
            PERIOD = Integer.parseInt(properties.getProperty("Period"));
            REPEATCOUNT = Integer.parseInt(properties.getProperty("Repeatcount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
