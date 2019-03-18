package com.bytd.crm.roadinfo.entities;

import java.util.ArrayList;
import java.util.List;

public class LSTCustomerInterface {
    private String gid;
    private String subscribeId;
    private List<String> etcCardNos= new ArrayList<>();

    public String getUserId() {
        return gid;
    }

    public void setUserId(String userId) {
        this.gid = userId;
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public List<String> getCardNoList() {
        return etcCardNos;
    }
}
