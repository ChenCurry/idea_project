package com.company;

import cn.cad.mobile.MobileCodeWS;
import cn.cad.mobile.MobileCodeWSSoap;
import org.junit.Test;

import java.sql.Timestamp;

public class Main {


    /**
     * webservice测试
     */
    @Test
    public void testWebservice(){
        //创建服务视图
        MobileCodeWS mobileCodeWS=new MobileCodeWS();
        //获取服务实现类
        MobileCodeWSSoap mobileCodeWSSoap= mobileCodeWS.getPort(MobileCodeWSSoap.class);
        //调用查询方法
        String message=mobileCodeWSSoap.getMobileCodeInfo("13788888888", null);//
        System.out.println(message);
    }

    @Test
    public void testHttpCall(){
        Double dd = Double.parseDouble("2.3");
        int i = dd.intValue();
        System.out.println(i);

    }


    @Test
    public void testTime(){
        //当前时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //指定时间
        Timestamp fixTime = Timestamp.valueOf("2018-05-18 09:32:32");
        if(timestamp.after(fixTime)){
            System.out.println("只保存cd");
        }
    }

}
