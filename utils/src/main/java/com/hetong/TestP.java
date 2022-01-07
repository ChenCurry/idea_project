package com.hetong;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestP {

    /**
     * 计算已认领，未认领金额（根据含税金额及excel中的到款金额）
     */
    @Test
    public void test2(){
        //含税金额 即 合同金额
        List listHanshui = new ArrayList<BigDecimal>();
        listHanshui.add(new BigDecimal("1500000"));
        listHanshui.add(new BigDecimal("2380000"));
        listHanshui.add(new BigDecimal("200000"));
        listHanshui.add(new BigDecimal("702000"));
        listHanshui.add(new BigDecimal("100000"));
        listHanshui.add(new BigDecimal("150000"));
        listHanshui.add(new BigDecimal("760000"));
        listHanshui.add(new BigDecimal("30000"));
        listHanshui.add(new BigDecimal("1200000"));
        listHanshui.add(new BigDecimal("1050000"));
        listHanshui.add(new BigDecimal("660000"));
        listHanshui.add(new BigDecimal("490000"));
        listHanshui.add(new BigDecimal("702000"));
        listHanshui.add(new BigDecimal("400000"));
        listHanshui.add(new BigDecimal("155870"));
        listHanshui.add(new BigDecimal("2680000"));
        listHanshui.add(new BigDecimal("588000"));
        listHanshui.add(new BigDecimal("200000"));
        listHanshui.add(new BigDecimal("299000"));
        listHanshui.add(new BigDecimal("298500"));
        listHanshui.add(new BigDecimal("199000"));
        listHanshui.add(new BigDecimal("100000"));
        listHanshui.add(new BigDecimal("520000"));
        listHanshui.add(new BigDecimal("260000"));
        listHanshui.add(new BigDecimal("950000"));
        listHanshui.add(new BigDecimal("2400000"));
        listHanshui.add(new BigDecimal("850000"));
        listHanshui.add(new BigDecimal("900000"));
        listHanshui.add(new BigDecimal("600000"));
        listHanshui.add(new BigDecimal("450000"));

        //到款金额 即 已认领金额 BigDecimal.ZERO
        List listDaokuan = new ArrayList<BigDecimal>();
        listDaokuan.add(new BigDecimal("450000"));
        listDaokuan.add(new BigDecimal("1428000"));
        listDaokuan.add(new BigDecimal("200000"));
        listDaokuan.add(new BigDecimal("421200"));
        listDaokuan.add(new BigDecimal("95000"));
        listDaokuan.add(new BigDecimal("150000"));
        listDaokuan.add(new BigDecimal("532000"));
        listDaokuan.add(new BigDecimal("20000"));
        listDaokuan.add(new BigDecimal("1080000"));
        listDaokuan.add(new BigDecimal("945000"));
        listDaokuan.add(new BigDecimal("660000"));
        listDaokuan.add(new BigDecimal("392000"));
        listDaokuan.add(new BigDecimal("351000"));
        listDaokuan.add(new BigDecimal("200000"));
        listDaokuan.add(new BigDecimal("77935"));
        listDaokuan.add(new BigDecimal("804000"));
        listDaokuan.add(new BigDecimal("176400"));
        listDaokuan.add(new BigDecimal("200000"));
        listDaokuan.add(BigDecimal.ZERO);
        listDaokuan.add(new BigDecimal("149250"));
        listDaokuan.add(new BigDecimal("159200"));
        listDaokuan.add(new BigDecimal("50000"));
        listDaokuan.add(new BigDecimal("470000"));
        listDaokuan.add(new BigDecimal("104000"));
        listDaokuan.add(new BigDecimal("855000"));
        listDaokuan.add(new BigDecimal("480000"));
        listDaokuan.add(new BigDecimal("850000"));
        listDaokuan.add(BigDecimal.ZERO);
        listDaokuan.add(new BigDecimal("400000"));
        listDaokuan.add(new BigDecimal("350000"));

        //已认领金额
//        for (int i = 0; i < listDaokuan.size(); i++) {
//            System.out.println(listDaokuan.get(i));
//        }


        //未认领金额
        for (int i = 0; i < listHanshui.size(); i++) {
            //含税金额
            BigDecimal hanshuijine = (BigDecimal) listHanshui.get(i);
            //到款金额 即为 已认领金额
            BigDecimal daokuan = (BigDecimal) listDaokuan.get(i);
            //未认领金额
            BigDecimal weirenling = hanshuijine.subtract(daokuan);
            weirenling = (weirenling.compareTo(BigDecimal.ZERO)<0)?BigDecimal.ZERO:weirenling;
            System.out.println(weirenling);
        }
    }

    /**
     * 根据含税金额和税率计算不含税金额
     */
    @Test
    public void test1(){
        //含税金额
        List listHanshui = new ArrayList<BigDecimal>();
        listHanshui.add(new BigDecimal("45168.72"));
        listHanshui.add(new BigDecimal("240000"));
        listHanshui.add(new BigDecimal("6750"));
        listHanshui.add(new BigDecimal("30250"));
        listHanshui.add(new BigDecimal("15500"));






        //税率
        List listShuilv = new ArrayList<BigDecimal>();
        listShuilv.add(new BigDecimal("6"));
        listShuilv.add(new BigDecimal("6"));
        listShuilv.add(new BigDecimal("6"));
        listShuilv.add(new BigDecimal("6"));
        listShuilv.add(new BigDecimal("6"));



        for (int i = 0; i < listHanshui.size(); i++) {
            //含税金额
            BigDecimal hanshuijine = (BigDecimal) listHanshui.get(i);
            //税率
            BigDecimal shuilv = (BigDecimal) listShuilv.get(i);
            BigDecimal shuilv2 = shuilv.divide(new BigDecimal(100));
            BigDecimal add = shuilv2.add(BigDecimal.ONE);
            BigDecimal divide = hanshuijine.divide(add, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(divide);
        }

    }
    @Test
    public void test346(){
        System.out.println(test("202112"));
    }

    /**
     * 传入期间
     * 传出起始时间，截至时间
     */

    public static HashMap<String, String> test(String periodStr){//202112
        HashMap<String, String> hashMap = new HashMap<String, String>();

        String yearStr = periodStr.substring(0, 4);
        int spYear2 = Integer.parseInt(yearStr);
        String monthStr = periodStr.substring(4, 6);
        int spNum2 = Integer.parseInt(monthStr);

        Boolean flag = null;
        if ((spYear2%4==0&&spYear2%100!=0)||spYear2%400==0) {
            System.out.println(spYear2+"是闰年");
            flag = true;
        }else {
            System.out.println(spYear2+"不是闰年");
            flag = false;
        }

        String endT = "" + spYear2+"-";
        if(spNum2==1||spNum2==3||spNum2==5||spNum2==7||spNum2==8||spNum2==10||spNum2==12){
            if(spNum2<10){
                endT += "0"+spNum2+"-31 23:59:59";
            }else{
                endT += spNum2+"-31 23:59:59";
            }

        }else if(spNum2==4||spNum2==6||spNum2==9||spNum2==11){
            if(spNum2<10){
                endT += "0"+spNum2+"-30 23:59:59";
            }else{
                endT += spNum2+"-30 23:59:59";
            }
        }else if(spNum2==2){
            if(flag){
                endT += "0"+spNum2+"-29 23:59:59";
            }else{
                endT += "0"+spNum2+"-28 23:59:59";
            }
        }

        hashMap.put("startDay", yearStr+"-"+monthStr+"-01 00:00:00");
        hashMap.put("endDay", endT);
        return hashMap;
    }
}
