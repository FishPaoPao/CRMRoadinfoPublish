package com.bytd.crm.roadinfo.entities;

public class Highway {

    private int id;
    private int highwayID;
    private String highwayName;
    private String HighwayCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHighwayID() {
        return highwayID;
    }

    public void setHighwayID(int highwayID) {
        this.highwayID = highwayID;
    }

    public String getHighwayName() {
        return highwayName;
    }

    public void setHighwayName(String highwayName) {
        this.highwayName = highwayName;
    }

    public String getHighwayCode() {
        return HighwayCode;
    }

    public void setHighwayCode(String highwayCode) {
        HighwayCode = highwayCode;
    }
}
