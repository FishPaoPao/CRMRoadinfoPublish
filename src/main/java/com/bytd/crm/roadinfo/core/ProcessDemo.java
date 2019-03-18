package com.bytd.crm.roadinfo.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class ProcessDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("Traffic Infomation Publish JobScheduler start...");
        if(processCheck(RoadinfoPublishJobScheduler.class.getSimpleName())){
            System.err.println(new Date() + " WARN:" + " A roadinfo publish process already exists");
        } else {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("java -cp /crm/service/CRMRoadinfoPublish.jar com.bytd.crm.roadinfo.core.RoadinfoPublishJobScheduler");
        }
        System.out.println("Traffic Infomation Publish JobScheduler started");
    }

    private static boolean processCheck(String className){
        InputStream in = null;
        try {
            in = Runtime.getRuntime().exec("jps").getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=br.readLine()) != null){
                if(line.contains(className)){
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
