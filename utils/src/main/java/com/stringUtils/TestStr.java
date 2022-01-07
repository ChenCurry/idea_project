package com.stringUtils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestStr {

    public static void main(String[] args) throws ParseException {
//        System.out.println(strReplace("1:1油泥模型制作及其风洞试验测试  "));
        System.out.println(lastPeroidStr("202108"));
    }

    public static String strReplace(String str){
        return str.trim().replaceAll(":","").replaceAll(";","").replaceAll("!","").replaceAll("_","");
    }

    public static String nextPeroidStr(String startDate) throws ParseException {
        Date t = null;
        String nmonth=new String();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        t=sdf.parse(startDate);
        Date nextmonth=new Date((t.getYear()+(t.getMonth()+1)/12),(t.getMonth()+1)%12,t.getDate());
        nmonth = sdf.format(nextmonth.getTime());
        return nmonth;
    }

    public static String lastPeroidStr(String startDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Calendar c=Calendar.getInstance();
        String mon = "";
        try {
            c.setTime(format.parse(startDate));
            c.add(Calendar.MONTH,-1);
            Date m=c.getTime();
            mon=format.format(m);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mon;
    }
}
