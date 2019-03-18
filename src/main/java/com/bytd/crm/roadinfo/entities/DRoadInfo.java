package com.bytd.crm.roadinfo.entities;

public class DRoadInfo extends DTrafficInfoBase {
    /**
     * Author_ID : 1
     * Block : 2
     * Created_Time : 2018/9/4 15:39:25
     * Direction : 1
     * Effect : null
     * EndStake : 3004
     * Fangwei : null
     * Highway_ID : 7
     * ID : 78335
     * Info_ID : 50976
     * Location : 测试
     * Notice : 请注意行车安全
     * Occur_Time :
     * Position : 2
     * Publish_Time : 2018/9/4 15:39:26
     * Reason : 测试
     * Show_Info : 测试，G1京哈高速进京方向测试车流量大，占用2，请注意行车安全。
     * Source : 0
     * Stake : 1002
     * Status : 0
     * StickTime :
     * T_id : 113298
     * Type_ID : 车流量大
     */

    private int Author_ID;
    private int Block;
    private String Created_Time;
    private int Direction;
    private String Effect;
    //private int EndStake;
    private int Fangwei;
    //private int Highway_ID;
    private int ID;
    private int Info_ID;
    private String Location;
    private String Notice;
    private String Occur_Time;
    private String Position;
    private String Publish_Time;
    private String Reason;
    private String Show_Info;
    private int Source;
    //private int Stake;
    //private int Status;
    private String StickTime;
    private int T_id;
    private String Type_ID;

    public int getAuthor_ID() {
        return Author_ID;
    }

    public void setAuthor_ID(int Author_ID) {
        this.Author_ID = Author_ID;
    }

    public int getBlock() {
        return Block;
    }

    public void setBlock(int Block) {
        this.Block = Block;
    }

    public String getCreated_Time() {
        return Created_Time;
    }

    public void setCreated_Time(String Created_Time) {
        this.Created_Time = Created_Time;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int Direction) {
        this.Direction = Direction;
    }

    public Object getEffect() {
        return Effect;
    }

    public void setEffect(String Effect) {
        this.Effect = Effect;
    }

//    public int getEndStake() {
//        return EndStake;
//    }
//
//    public void setEndStake(int EndStake) {
//        this.EndStake = EndStake;
//    }

    public int getFangwei() {
        return Fangwei;
    }

    public void setFangwei(int Fangwei) {
        this.Fangwei = Fangwei;
    }

//    public int getHighway_ID() {
//        return Highway_ID;
//    }
//
//    public void setHighway_ID(int Highway_ID) {
//        this.Highway_ID = Highway_ID;
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getInfo_ID() {
        return Info_ID;
    }

    public void setInfo_ID(int Info_ID) {
        this.Info_ID = Info_ID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String Notice) {
        this.Notice = Notice;
    }

    public String getOccur_Time() {
        return Occur_Time;
    }

    public void setOccur_Time(String Occur_Time) {
        this.Occur_Time = Occur_Time;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getPublish_Time() {
        return Publish_Time;
    }

    public void setPublish_Time(String Publish_Time) {
        this.Publish_Time = Publish_Time;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getShow_Info() {
        return Show_Info;
    }

    public void setShow_Info(String Show_Info) {
        this.Show_Info = Show_Info;
    }

    public int getSource() {
        return Source;
    }

    public void setSource(int Source) {
        this.Source = Source;
    }

//    public int getStake() {
//        return Stake;
//    }
//
//    public void setStake(int Stake) {
//        this.Stake = Stake;
//    }
//
//    public int getStatus() {
//        return Status;
//    }
//
//    public void setStatus(int Status) {
//        this.Status = Status;
//    }

    public String getStickTime() {
        return StickTime;
    }

    public void setStickTime(String StickTime) {
        this.StickTime = StickTime;
    }

    public int getT_id() {
        return T_id;
    }

    public void setT_id(int T_id) {
        this.T_id = T_id;
    }

    public String getType_ID() {
        return Type_ID;
    }

    public void setType_ID(String Type_ID) {
        this.Type_ID = Type_ID;
    }

}
