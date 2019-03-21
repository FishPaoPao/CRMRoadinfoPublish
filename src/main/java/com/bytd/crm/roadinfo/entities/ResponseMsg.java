package com.bytd.crm.roadinfo.entities;

import com.google.gson.Gson;

/**
 * @ClassName ResponseMsg
 * @Description TODO
 * @Author yuheng
 * @Date
 * @Version 1.0
 **/
public class ResponseMsg {

    /**
     * error : {"errorCode":"0","errorInfo":"success"}
     */

    private Error error;

    public ResponseMsg(Error error) {
        this.setError(error);
    }

    public static ResponseMsg objectFromData(String str) {

        return new Gson().fromJson(str, ResponseMsg.class);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static class Error {
        /**
         * errorCode : 0
         * errorInfo : success
         */

        private String errorCode;
        private String errorInfo;

        public Error(String errorCode, String errorInfo) {
            this.errorCode = errorCode;
            this.errorInfo = errorInfo;
        }

        public static Error objectFromData(String str) {

            return new Gson().fromJson(str, Error.class);
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorInfo() {
            return errorInfo;
        }

        public void setErrorInfo(String errorInfo) {
            this.errorInfo = errorInfo;
        }
    }
}
