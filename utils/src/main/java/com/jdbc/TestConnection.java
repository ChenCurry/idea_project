package com.jdbc;

import java.util.ArrayList;
import java.util.HashMap;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("开始");
        try{
//            ArrayList<HashMap<String, Object>> list = DBUtilCy.executeQuery("select EasId from tb_kingdee_bankflow");
            ArrayList<HashMap<String, Object>> list = DBUtilCy.executeQuery("select fid from T_BE_BankPayingBill");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}
