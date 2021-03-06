package com.utils;


import sun.net.www.http.HttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpConnectUtil {

    private static String DUOSHUO_SHORTNAME = "yoodb";//多说短域名 ****.yoodb.****
    private static String DUOSHUO_SECRET = "xxxxxxxxxxxxxxxxx";//多说秘钥

    /** *  * @Title:getToken * @Description:(get方式调用http接口获取token) * @param url * @return * @throws Exception * @author zzq */
//    public static String getToken(String url) throws Exception {
//        String responseMsg = "";
//        String access_token = "";
//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = new GetMethod(url);
//        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                new DefaultHttpMethodRetryHandler());
//        try {
//            httpClient.executeMethod(getMethod);
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            InputStream in = getMethod.getResponseBodyAsStream();
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while ((len = in.read(buf)) != -1) {
//                out.write(buf, 0, len);
//            }
//            responseMsg = out.toString("UTF-8");
//            JSONObject obj = JSONObject.fromObject(responseMsg);
//            System.out.print(obj);
//            access_token = obj.getString("access_token");
//        } catch (HttpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            getMethod.releaseConnection();// 释放连接
//        }
//        return access_token;
//    }access_token

    /**
     * post方式
     * @param url
     * @param code
     * @param type
     * @author www.yoodb.com
     * @return
     */
//    public static String postHttp(String url,String code,String type) {
//        String responseMsg = "";
//        HttpClient httpClient = new HttpClient();
//        httpClient.getParams().setContentCharset("GBK");
//        PostMethod postMethod = new PostMethod(url);
//        postMethod.addParameter(type, code);
//        postMethod.addParameter("client_id", DUOSHUO_SHORTNAME);
//        postMethod.addParameter("client_secret", DUOSHUO_SECRET);
//        try {
//            httpClient.executeMethod(postMethod);
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            InputStream in = postMethod.getResponseBodyAsStream();
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while((len=in.read(buf))!=-1){
//                out.write(buf, 0, len);
//            }
//            responseMsg = out.toString("UTF-8");
//        } catch (HttpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            postMethod.releaseConnection();
//        }
//        return responseMsg;
//    }
}
