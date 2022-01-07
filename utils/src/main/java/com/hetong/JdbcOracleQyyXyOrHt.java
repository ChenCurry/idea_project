package com.hetong;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcOracleQyyXyOrHt {
    public static void main(String[] args) throws Exception{
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        //10.130.5.156
        //10.130.5.186
        String url= "jdbc:oracle:thin:@10.130.5.186:1521:easdb";//协议：自协议//[ip]:[port]/database
        String user = "qyyeasdb";
        String password = "kingdee";
        try {
            Class.forName("oracle.jdbc.OracleDriver");//oracle.jdbc.driver.OracleDriver
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            List<String> list = new ArrayList<String>();
            rs = statement.executeQuery("select x.bfid,x.afid\n" +
                    "from (\n" +
                    "select b.fid as bfid,count(a.fid) as afid from CT_YCY_XyOrHtEntry a,CT_YCY_XyOrHt b where a.fparentid=b.fid group by b.fid\n" +
                    ") x \n" +
                    "where x.afid>1");
            while (rs.next()){
                list.add(rs.getString("bfid"));
            }
            System.out.println(list.toString());


            for (int i = 0; i < list.size(); i++) {
                ArrayList<HashMap<String, BigDecimal>> maps = new ArrayList<>();
                HashMap<String, BigDecimal> map = new HashMap<>();
                String fid = list.get(i);
                rs = statement.executeQuery("select a.cftaxamt,a.cfuntaxamt,a.cftax from CT_YCY_XyOrHtEntry a,CT_YCY_XyOrHt b where a.fparentid=b.fid and b.fid= '"+fid+"'");
                while (rs.next()){
                    map = new HashMap<>();
                    map.put("cftaxamt",((rs.getString("cftaxamt"))==null)?BigDecimal.ZERO:new BigDecimal(rs.getString("cftaxamt")));
                    map.put("cfuntaxamt",((rs.getString("cfuntaxamt"))==null)?BigDecimal.ZERO:new BigDecimal(rs.getString("cfuntaxamt")));
                    map.put("cftax",((rs.getString("cftax"))==null)?BigDecimal.ZERO:new BigDecimal(rs.getString("cftax")));
                    maps.add(map);
                }

                BigDecimal hanshui = null;
                BigDecimal buhanshui = null;
                rs = statement.executeQuery("select cftaxamt,cfuntaxamt from CT_YCY_XyOrHt where fid ='"+fid+"'");
                while (rs.next()){
                    hanshui = ((rs.getString("cftaxamt"))==null)?BigDecimal.ZERO:new BigDecimal(rs.getString("cftaxamt"));
                    buhanshui = ((rs.getString("cfuntaxamt"))==null)?BigDecimal.ZERO:new BigDecimal(rs.getString("cfuntaxamt"));
                }

                BigDecimal hanshui2 = BigDecimal.ZERO;
                BigDecimal buhanshui2 = BigDecimal.ZERO;
                for (int i1 = 0; i1 < maps.size(); i1++) {
                    HashMap<String, BigDecimal> eachMap = maps.get(i1);
                    hanshui2 = hanshui2.add(eachMap.get("cftaxamt"));
                    buhanshui2 = buhanshui2.add(eachMap.get("cfuntaxamt"));
                }

                String hth = "";
                rs = statement.executeQuery("select fnumber from ct_ycy_xyorht where fid ='"+fid+"'");
                while (rs.next()){
                    hth = rs.getString("fnumber");
                }

                if(hanshui2.compareTo(hanshui)!=0){//含税金额需要改
                    System.out.println(hth+"--对应的合同含税金额需要由>"+hanshui+"<改为>"+hanshui2);
                }

                if(buhanshui2.compareTo(buhanshui)!=0){//不含税金额需要改
                    System.out.println(hth+"--对应的合同不含税金额需要由>"+buhanshui+"<改为>"+buhanshui2);
                }



            }



        } finally {
            try {
                if (rs != null){//严谨一些
                    rs.close();
                }
            } finally {//彻底删除statement，conn
                try {
                    if (statement != null){
                        statement.close();
                    }
                } finally {//彻底删除conn
                    if (conn != null){
                        conn.close();
                    }
                }
            }
        }
    }
}