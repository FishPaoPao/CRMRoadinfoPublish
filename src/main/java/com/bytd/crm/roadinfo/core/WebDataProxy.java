package com.bytd.crm.roadinfo.core;

import com.bytd.crm.roadinfo.entities.ResponseMsg;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.bytd.crm.roadinfo.core.ExceptionLog.errorTrackSpace;

/**
 * @author bytd
 */
public class WebDataProxy {

    private Logger logger = Logger.getLogger(WebDataProxy.class);
    public String getData(String urlParam, Map<String, Object> params, String charset){
        StringBuilder resultString = new StringBuilder();
        //构建请求参数
        StringBuilder sbParams = makeRequestParams(params);
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            URL url = null;
            if(sbParams != null && sbParams.length() > 0){
                url = new URL(urlParam + "?" + sbParams.substring(0,sbParams.length() - 1));
            } else {
                url = new URL(urlParam);
            }
            conn = (HttpURLConnection) url.openConnection();
            //设置请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "text/html; charset=" + charset);
            conn.setRequestMethod("GET");
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            logger.info("请求数据: " + conn.getRequestMethod() + " " + conn.getURL());
            conn.connect();
            //发起http请求
            if(conn.getResponseCode() == 200){
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                String line = null;
                while((line = br.readLine()) != null){
                    resultString.append(line);
                }
                logger.info("返回数据: " + resultString);
            } else {
                logger.error(String.valueOf(conn.getResponseCode()));
                logger.error(conn.getResponseMessage());
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error(errorTrackSpace(e));
        }
        return resultString.toString();
    }

    public <T> ResponseMsg sendObject(String urlParam, Map<String, Object> params, T entity) {
        String jsonData = JsonHelper.objectToJson(entity);
        return sendJson(urlParam, params, jsonData, "utf-8");
    }

    public ResponseMsg sendJson(String urlParam, Map<String, Object> params, String jsonText) {
        return sendJson(urlParam, params, jsonText, "utf-8");
    }

    public ResponseMsg sendJson(String urlParam, Map<String, Object> params, String jsonText, String charset) {
        StringBuilder resultString = new StringBuilder();
        //构建请求参数
        StringBuilder sbParams = makeRequestParams(params);
        //创建http请求
        HttpURLConnection conn = null;
        OutputStreamWriter os = null;
        BufferedReader br = null;
        try {
            URL url = null;
            if(sbParams != null && sbParams.length() > 0){
                url = new URL(urlParam + "?" + sbParams.substring(0,sbParams.length() - 1));
            } else {
                url = new URL(urlParam);
            }
            conn = (HttpURLConnection) url.openConnection();
            //设置Http请求参数
            conn.setRequestProperty("Content-Type", "application/json; charset=" + charset);
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("Charset", charset);
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setRequestMethod("POST");
            logger.info("转发数据: " + conn.getRequestMethod() + " " + conn.getURL());
            conn.connect();
            //发送json数据
            os = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            logger.info("转发内容: " + jsonText);
            os.write(jsonText);
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                String line = null;
                while ((line = br.readLine()) != null) {
                    resultString.append(line);
                }
                logger.info("返回结果: " + resultString);
                return ResponseMsg.objectFromData(resultString.toString());
            } else {
                logger.error("返回结果, code=" + conn.getResponseCode() + "desc=" + conn.getResponseMessage());
                return new ResponseMsg(new ResponseMsg.Error(String.valueOf(conn.getResponseCode()), conn.getResponseMessage()));
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error(errorTrackSpace(e));
            return new ResponseMsg(new ResponseMsg.Error("9999", e.getMessage()));
        }
    }

    private StringBuilder makeRequestParams(Map<String,Object> params){
        StringBuilder sbParams = new StringBuilder();
        if(params != null && params.size() > 0){
            for(Entry<String, Object> entry : params.entrySet()){
                sbParams.append(entry.getKey());
                sbParams.append("=");
                sbParams.append(entry.getValue());
                sbParams.append("&");
            }
            return sbParams;
        }
        return null;
    }
}
