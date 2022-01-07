package com.jdbc;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TestJdbc {
    @Test
    public void testUitl(){
        ArrayList<HashMap<String, Object>> hashMaps = DBUtilCy.executeQuery("select * from T_SCM_DeliveryType");
        for (int i = 0; i < hashMaps.size(); i++) {
            HashMap<String, Object> stringObjectHashMap = hashMaps.get(i);
            System.out.println(stringObjectHashMap.toString());
        }
    }

    @Test
    public void testTime(){
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -5);// 5分钟之前的时间
        Date beforeD = beforeTime.getTime();
        String before5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);  // 前五分钟时间
        System.out.println(before5);
    }

    @Test
    public void testTime2(){
        Timestamp timestamp = strToSqlDate("2021-11-17 03:09:22", "yyyy-MM-dd HH:mm:ss");
        System.out.println(timestamp);
    }

    public static java.sql.Timestamp strToSqlDate(String strDate, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        java.util.Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        return dateSQL;
    }
}
