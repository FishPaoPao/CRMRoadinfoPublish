package com.bytd.crm.roadinfo.entities;

public class DSubsPushInfo extends DTrafficInfoBase {
    private int Id;
    private String Url;
    private String Params_Map;
    private String Json;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getParams_Map() {
        return Params_Map;
    }

    public void setParams_Map(String params_Map) {
        Params_Map = params_Map;
    }

    public String getJson() {
        return Json;
    }

    public void setJson(String json) {
        Json = json;
    }
}
