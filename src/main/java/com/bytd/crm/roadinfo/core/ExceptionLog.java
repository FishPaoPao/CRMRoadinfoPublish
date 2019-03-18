package com.bytd.crm.roadinfo.core;

public class ExceptionLog {
    /**
     * 向日志中输出异常信息
     * @param e 异常
     * @return 异常描述信息
     */
    public static String errorTrackSpace(Exception e) {
        StringBuffer sb = new StringBuffer();
        if (e != null) {
            for (StackTraceElement element : e.getStackTrace()) {
                sb.append("\r\n\t").append(element);
            }
        }
        return sb.length() == 0 ? null : sb.toString();
    }
}
