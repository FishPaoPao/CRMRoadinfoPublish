package com.bytd.crm.roadinfo.entities;

public class DTrafficInfo extends DTrafficInfoBase {
    /**
     * Author_ID : 1
     * Created_Time : 2018/9/4 15:36:56
     * Detail : null
     * DetourImg : null
     * Direction : 1
     * Effect : null
     * EndStake : null
     * Fangwei : 0
     * Highway_ID : 7
     * ID : 113297
     * Info_ID : 60663
     * IsBlock : true
     * Location : 测试
     * Notice : 请注意行车安全
     * Occur_Time :
     * Position : null
     * Publish_Time : 2018/9/4 15:39:26
     * Reason : 测试2
     * Show_Info : 测试2，G1京哈高速进京方向K1+002测试发生交通事故，请注意行车安全。
     * Source : 0
     * Stake : 1002
     * Status : 0
     * StickTime :
     * Type_ID : 交通事故
     */

    private int Author_ID;
    private String Created_Time;
    private  String Detail;
    private String DetourImg;
    private int Direction;
    private  String Effect;
    //private  int EndStake;
    private int Fangwei;
    //private int Highway_ID;
    private int ID;
    private int Info_ID;
    private boolean IsBlock;
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
    private String Type_ID;

    public int getAuthor_ID() {
        return Author_ID;
    }

    public void setAuthor_ID(int Author_ID) {
        this.Author_ID = Author_ID;
    }

    public String getCreated_Time() {
        return Created_Time;
    }

    public void setCreated_Time(String Created_Time) {
        this.Created_Time = Created_Time;
    }

    public Object getDetail() {
        return Detail;
    }

    public void setDetail(String Detail) {
        this.Detail = Detail;
    }

    public Object getDetourImg() {
        return DetourImg;
    }

    public void setDetourImg(String DetourImg) {
        this.DetourImg = DetourImg;
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

//    public Object getEndStake() {
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

    public boolean isIsBlock() {
        return IsBlock;
    }

    public void setIsBlock(boolean IsBlock) {
        this.IsBlock = IsBlock;
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

    public Object getPosition() {
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

    public String getType_ID() {
        return Type_ID;
    }

    public void setType_ID(String Type_ID) {
        this.Type_ID = Type_ID;
    }

}
