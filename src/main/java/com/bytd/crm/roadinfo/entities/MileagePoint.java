package com.bytd.crm.roadinfo.entities;

/**
 * 数据表Mileage_Point的实体类
 */
public class MileagePoint {

    private int ID;
    private int smID;
    private String smX;
    private String smY;
    private int smLibTileId;
    private int smUserID;
    private String Name;
    private int value;
    private int roadID;
    private int direction;
    private int highwayID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSmID() {
        return smID;
    }

    public void setSmID(int smID) {
        this.smID = smID;
    }

    public String getSmX() {
        return smX;
    }

    public void setSmX(String smX) {
        this.smX = smX;
    }

    public String getSmY() {
        return smY;
    }

    public void setSmY(String smY) {
        this.smY = smY;
    }

    public int getSmLibTileId() {
        return smLibTileId;
    }

    public void setSmLibTileId(int smLibTileId) {
        this.smLibTileId = smLibTileId;
    }

    public int getSmUserID() {
        return smUserID;
    }

    public void setSmUserID(int smUserID) {
        this.smUserID = smUserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRoadID() {
        return roadID;
    }

    public void setRoadID(int roadID) {
        this.roadID = roadID;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHighwayID() {
        return highwayID;
    }

    public void setHighwayID(int highwayID) {
        this.highwayID = highwayID;
    }
}
