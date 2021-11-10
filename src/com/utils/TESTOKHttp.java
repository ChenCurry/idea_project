package com.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TESTOKHttp {
    /**
     * 调用对方接口方法
     */
    public static void interfaceUtil(String path,String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");//GET和POST必须全大写
            /**GET方法请求*****start*/
            /**
             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
             * */
            conn.connect();

            /**GET方法请求*****end*/

            /***POST方法请求****start*/

            /*out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流

            out.print(data);//发送请求参数即数据

            out.flush();//缓冲数据
            */
            /***POST方法请求****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                str=new String(str.getBytes(),"UTF-8");//解决中文乱码问题
                System.out.println(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用对方接口方法-post
     */
    public static void interfacePostUtil(String path,String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("POST");//GET和POST必须全大写

            /***POST方法请求****start*/
            out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流
            out.print(data);//发送请求参数即数据
            out.flush();//缓冲数据
            /***POST方法请求****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                str=new String(str.getBytes(),"UTF-8");//解决中文乱码问题
                System.out.println(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //post
    public static void sendPost_body(String arl, String s) {
        // 创建url资源
        OutputStreamWriter out = null;
        URL url;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(arl);

// 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
// 设置不用缓存
            conn.setUseCaches(false);
// 设置传递方式
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("POST"); // 设置请求方式
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");//设置消息头，解决508错误
// 开始连接请求
            conn.connect();
            out = new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8"); // utf-8编码
// 写入请求的字符串
            out.append(s);
            out.flush();
            out.close();
// System.out.println(conn.getResponseCode());
            if(conn.getResponseCode()==200){
// System.out.println("success");
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }

        } catch (Exception e) {
// System.out.println("发送 POST 请求出现异常！" + e);
            result = new StringBuilder("{\"resCode\":\"1\",\"errCode\":\"1001\",\"resData\":\"\"}");
            e.printStackTrace();
// log.error("远程服务未开启", e);
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        //get请求
//    	interfaceUtil("http://10.130.5.157:8080/easportal/openapi/login?authPattern=BaseDB&dcName=qyy01&language=l2&password=&user=user", "");

        //post请求
        String dateStr = "{\"api\":\"addOrUpdateAgreeOrContract\",\"data\":[{\"ifChange\":\"update\",\"agreeOrContractBills\":[{\"billType\":\"02\",\"namee\":\"s3sfd\",\"number\":\"00\",\"fromSys\":\"00\",\"xyNbr\":\"0143\",\"contractType\":\"00\",\"custNbr\":\"01.01.0027\",\"deptNbr\":\"0143\",\"taxAmt\":\"85.3\",\"unTaxAmt\":\"53.9\",\"version\":\"2.69\",\"bizState\":\"00\",\"currency\":\"RMB\",\"entrys\":[{\"contractLineNbr\":\"sdf33\",\"contractLineNm\":\"nnnm\",\"taxAmt\":\"96.2\",\"unTaxAmt\":\"63.2\",\"tax\":\"2.69\"},{\"contractLineNbr\":\"sdf33\",\"contractLineNm\":\"nnnm\",\"taxAmt\":\"96.2\",\"unTaxAmt\":\"63.2\",\"tax\":\"2.69\"}]},{\"billType\":\"02\",\"namee\":\"sdfs3\",\"number\":\"sdfs44g\",\"fromSys\":\"00\",\"xyNbr\":\"0143\",\"contractType\":\"00\",\"custNbr\":\"01.01.0027\",\"deptNbr\":\"0143\",\"taxAmt\":\"85.3\",\"unTaxAmt\":\"53.9\",\"version\":\"2.69\",\"bizState\":\"02\",\"currency\":\"RMB\",\"entrys\":[{\"contractLineNbr\":\"sdf33\",\"contractLineNm\":\"nnnm\",\"taxAmt\":\"96.2\",\"unTaxAmt\":\"63.2\",\"tax\":\"2.69\"},{\"contractLineNbr\":\"sdf33\",\"contractLineNm\":\"nnnm\",\"taxAmt\":\"96.2\",\"unTaxAmt\":\"63.2\",\"tax\":\"2.69\"}]}]}]}";
//        interfacePostUtil("http://10.130.5.157:8080/easportal/openapi/api?token=2j/MNfZzyOm8HjAuDZ/EciqzBgsEzLcVF76kDgvWsAlF9EbZzi7DJFnjzh/raw6F", dateStr);

        //post2
        sendPost_body("http://10.130.5.157:8080/easportal/openapi/api?token=2j/MNfZzyOm8HjAuDZ/EciqzBgsEzLcVF76kDgvWsAlF9EbZzi7DJFnjzh/raw6F",dateStr);

    }
}
