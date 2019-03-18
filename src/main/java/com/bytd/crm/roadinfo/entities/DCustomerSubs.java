package com.bytd.crm.roadinfo.entities;

public class DCustomerSubs {
    private int Id;
    private String Sub_Request_Id;
    private String Customer_Id;
    private String Entrance;
    private int Entrance_HighWayStandardId;
    private String Entrance_Stake;
    private double Entrance_SmX;
    private double Entrance_SmY;
    private String Exitrance;
    private int Exitrance_HighwayStandardId;
    private String Exitrance_Stake;
    private double Exitrance_SmX;
    private double Exitrance_SmY;
    private String Broadcast_Time;
    private String Update_Time;

    private String Path;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomer_Id() {
        return Customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        Customer_Id = customer_Id;
    }

    public String getSub_Request_Id() {
        return Sub_Request_Id;
    }

    public void setSub_Request_Id(String sub_Request_Id) {
        Sub_Request_Id = sub_Request_Id;
    }

    public String getEntrance() {
        return Entrance;
    }

    public void setEntrance(String entrance) {
        Entrance = entrance;
    }

    public String getExitrance() {
        return Exitrance;
    }

    public void setExitrance(String exitrance) {
        Exitrance = exitrance;
    }

    public String getBroadcast_Time() {
        return Broadcast_Time;
    }

    public void setBroadcast_Time(String broadcast_Time) {
        Broadcast_Time = broadcast_Time;
    }

    public String getUpdate_Time() {
        return Update_Time;
    }

    public void setUpdate_Time(String update_Time) {
        Update_Time = update_Time;
    }

    public int getEntrance_HighWayStandardId() {
        return Entrance_HighWayStandardId;
    }

    public void setEntrance_HighWayStandardId(int entrance_HighWayStandardId) {
        Entrance_HighWayStandardId = entrance_HighWayStandardId;
    }

    public String getEntrance_Stake() {
        return Entrance_Stake;
    }

    public void setEntrance_Stake(String entrance_Stake) {
        Entrance_Stake = entrance_Stake;
    }

    public double getEntrance_SmX() {
        return Entrance_SmX;
    }

    public void setEntrance_SmX(double entrance_SmX) {
        Entrance_SmX = entrance_SmX;
    }

    public double getEntrance_SmY() {
        return Entrance_SmY;
    }

    public void setEntrance_SmY(double entrance_SmY) {
        Entrance_SmY = entrance_SmY;
    }

    public int getExitrance_HighwayStandardId() {
        return Exitrance_HighwayStandardId;
    }

    public void setExitrance_HighwayStandardId(int exitrance_HighwayStatndardId) {
        Exitrance_HighwayStandardId = exitrance_HighwayStatndardId;
    }

    public String getExitrance_Stake() {
        return Exitrance_Stake;
    }

    public void setExitrance_Stake(String exitrance_Stake) {
        Exitrance_Stake = exitrance_Stake;
    }

    public double getExitrance_SmX() {
        return Exitrance_SmX;
    }

    public void setExitrance_SmX(double exitrance_SmX) {
        Exitrance_SmX = exitrance_SmX;
    }

    public double getExitrance_SmY() {
        return Exitrance_SmY;
    }

    public void setExitrance_SmY(double exitrance_SmY) {
        Exitrance_SmY = exitrance_SmY;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public String toString() {
        return "DCustomerSubs{" +
                "Id=" + Id +
                ", Sub_Request_Id='" + Sub_Request_Id + '\'' +
                ", Customer_Id='" + Customer_Id + '\'' +
                ", Entrance='" + Entrance + '\'' +
                ", Entrance_HighWayStandardId=" + Entrance_HighWayStandardId +
                ", Entrance_Stake='" + Entrance_Stake + '\'' +
                ", Entrance_SmX=" + Entrance_SmX +
                ", Entrance_SmY=" + Entrance_SmY +
                ", Exitrance='" + Exitrance + '\'' +
                ", Exitrance_HighwayStandardId=" + Exitrance_HighwayStandardId +
                ", Exitrance_Stake='" + Exitrance_Stake + '\'' +
                ", Exitrance_SmX=" + Exitrance_SmX +
                ", Exitrance_SmY=" + Exitrance_SmY +
                ", Broadcast_Time='" + Broadcast_Time + '\'' +
                ", Update_Time='" + Update_Time + '\'' +
                ", Path='" + Path + '\'' +
                '}';
    }
}
