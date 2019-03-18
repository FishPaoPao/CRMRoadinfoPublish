package com.bytd.crm.roadinfo.core;

/**
 * @author bytd
 */
public class ConstManager {

    public enum StatusConts{
        /**
         * 0:发布
         */
        PUBLISH(0,"PUBLISH"),
        /**
         * 1:撤销
         */
        REVOCATION(1,"REVOCATION");

        private int code;
        private String codePhrase;

        StatusConts(int code, String codePhrase) {
            this.code = code;
            this.codePhrase = codePhrase;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getCodePhrase() {
            return codePhrase;
        }

        public void setCodePhrase(String codePhrase) {
            this.codePhrase = codePhrase;
        }
        public static String getPhraseByCode(int code){
            switch(code){
                case 0:
                    return StatusConts.PUBLISH.getCodePhrase();
                case 1:
                    return StatusConts.REVOCATION.getCodePhrase();
                default:
                    return null;
            }
        }
    }

    public enum DirectionConts{
        /**
         * 1:进京 (GOINTOCAPTIAL)
         */
        GOINTOCAPITAL(1,"GOINTOCAPITAL"),
        /**
         * 2:出京（LEAVECAPTIAL)
         */
        LEAVECAPITAL(2,"LEAVECAPITAL"),
        /**
         * 3:内环 (INNERRING)
         */
        INNERRING(3,"INNERRING"),
        /**
         * 4:外环 (OUTERRING)
         */
        OUTERRING (4,"OUTERRING"),
        /**
         * 5:双向(TWOWAY)
         */
        TWOWAY(5,"TWOWAY");

        private int code;
        private String codePhrase;

        DirectionConts(int code, String codePhrase){
            this.code = code;
            this.codePhrase = codePhrase;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getCodePhrase() {
            return codePhrase;
        }

        public void setCodePhrase(String codePhrase) {
            this.codePhrase = codePhrase;
        }

        public static String getPhraseByCode(int code){
            switch(code){
                case 1:
                    return DirectionConts.GOINTOCAPITAL.getCodePhrase();
                case 2:
                    return DirectionConts.LEAVECAPITAL.getCodePhrase();
                case 3:
                    return DirectionConts.INNERRING.getCodePhrase();
                case 4:
                    return DirectionConts.OUTERRING.getCodePhrase();
                case 5:
                    return DirectionConts.TWOWAY.getCodePhrase();
                default:
                    return null;
            }
        }
    }

    public enum EventOfCategory {
        /**
         * 车流量大：HEAVYTRAFFIC
         */
        HEAVYTRAFFIC("车流量大", "HEAVYTRAFFIC"),
        /**
         * 拥堵：TRAFFICCONGESTION
         */
        TRAFFICCONGESTION("拥堵", "TRAFFICCONGESTION"),
        /**
         * 交通事故：TRAFFICACCIDENT
         */
        TRAFFICACCIDENT("交通事故", "TRAFFICACCIDENT"),
        /**
         * 故障车：TROUBLETRUCK
         */
        TROUBLETRUCK("故障车", "TROUBLETRUCK"),
        /**
         * 交通管制：TRAFFICCONTROL
         */
        TRAFFICCONTROL("临时交通管制", "TRAFFICCONTROL"),
        /**
         * 封闭：ROADCLOSURE
         */
        ROADCLOSURE("封闭", "ROADCLOSURE"),
        /**
         * 占道施工：ROADCONSTRUCTION
         */
        ROADCONSTRUCTION("占道施工","ROADCONSTRUCTION");

        private String eventOfCategoryCode;
        private String eventOfCategoryDesc;

        public String getEventOfCategoryCode() {
            return eventOfCategoryCode;
        }

        public void setEventOfCategoryCode(String eventOfCategoryCode) {
            this.eventOfCategoryCode = eventOfCategoryCode;
        }

        public String getEventOfCategoryDesc() {
            return eventOfCategoryDesc;
        }

        public void setEventOfCategoryDesc(String eventOfCategoryDesc) {
            this.eventOfCategoryDesc = eventOfCategoryDesc;
        }

        EventOfCategory(String eventOfCategoryCode, String eventOfCategoryDesc) {
            this.eventOfCategoryCode = eventOfCategoryCode;
            this.eventOfCategoryDesc = eventOfCategoryDesc;
        }
        public static String getPhraseByCode(String code){
            switch(code){
                case "车流量大" :
                    return EventOfCategory.HEAVYTRAFFIC.getEventOfCategoryDesc();
                case "拥堵":
                    return EventOfCategory.TRAFFICCONGESTION.getEventOfCategoryDesc();
                case "交通事故":
                    return EventOfCategory.TRAFFICACCIDENT.getEventOfCategoryDesc();
                case "故障车":
                    return EventOfCategory.TROUBLETRUCK.getEventOfCategoryDesc();
                case "临时交通管制":
                    return EventOfCategory.TRAFFICCONTROL.getEventOfCategoryDesc();
                case "封闭":
                    return EventOfCategory.ROADCLOSURE.getEventOfCategoryDesc();
                case "占道施工":
                    return EventOfCategory.ROADCONSTRUCTION.getEventOfCategoryDesc();
                default:
                    return null;
            }
        }
    }
}
