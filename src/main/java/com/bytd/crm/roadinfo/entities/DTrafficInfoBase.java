package com.bytd.crm.roadinfo.entities;

public class DTrafficInfoBase {

    String Stake;
    String EndStake;
    int Status;

    /**
     *
     * @return 高速公路ID
     */
    public int getHighway_ID() {
        return Highway_ID;
    }

    public void setHighway_ID(int highway_ID) {
        Highway_ID = highway_ID;
    }

    int Highway_ID;

    /**
     *
     * @return 结束桩号
     */
    public String getEndStake() {
        return EndStake;
    }

    public void setEndStake(String EndStake) {
        this.EndStake = EndStake;
    }

    /**
     *
     * @return 起始桩号
     */
    public String getStake() {
        return Stake;
    }

    public void setStake(String Stake) {
        this.Stake = Stake;
    }

    /**
     *
     * @return 路况消息状态，0：上架，1：下架
     */
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

}
