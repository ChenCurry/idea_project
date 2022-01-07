package com.hetong;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 批量处理：看合同有没有做过业务，没有则置为失效
 */
public class JdbcOracleQyyXyOrHt2 {
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
            List<String> list = new ArrayList<String>();//所有合同add进来

            list.add("HH19128A");
            list.add("HZ19017A");
            list.add("HZ20113A");
            list.add("HE21006A");
            list.add("HZ20147A");
            list.add("HH20036A");
            list.add("HH20321A");
            list.add("HE20111A");
            list.add("HE20112A");
            list.add("HH20239A");
            list.add("HE20114A");
            list.add("HZ20096A");
            list.add("HH20275A");
            list.add("HE21003A");
            list.add("HH20254A");
            list.add("HH20253A");
            list.add("HH20003A");
            list.add("HH20185A");
            list.add("HZ20187A");
            list.add("HZ20121A");
            list.add("HH20270A");
            list.add("HH20271A");
            list.add("HH21180A");
            list.add("HZ21031A");
            list.add("HZ21032A");
            list.add("HZ21033A");
            list.add("HZ21164A");
            list.add("HZ21083A");
            list.add("HH21037A");
            list.add("HH21057A");
            list.add("HZ21019A");
            list.add("HZ21119A");
            list.add("HZ21138A");
            list.add("HZ21139A");
            list.add("HZ21163A");
            list.add("HZ21023A");
            list.add("HZ21142A");
            list.add("HH21129A");
            list.add("HZ21022A");
            list.add("HH21104A");
            list.add("HZ21095A");
            list.add("HZ21128A");
            list.add("HH21130A");
            list.add("HH21146A");
            list.add("HH21171A");
            list.add("HZ21051A");
            list.add("HZ21118A");
            list.add("HZ21117A");
            list.add("HE21039A");
            list.add("HZ21091A");
            list.add("HH21096A");
            list.add("HH21076A");
            list.add("HH21186A");
            list.add("HZ21221A");
            list.add("HZ21165A");


            int chuli = 0;
            for (int i = 0; i < list.size(); i++) {
                String hetong = list.get(i);

                boolean chushihua = false;
                rs = statement.executeQuery("select a.fid from CT_BD_InitDataEntry a,CT_BD_InitData c,CT_YCY_XyOrHt b where c.fid=a.FParentID and c.cfcontractid=b.fid and b.fnumber in ('"+hetong+"')");
                while (rs.next()){
                    String fid = rs.getString("fid");
                    chushihua = true;
                }

                boolean jiesuan = false;
                rs = statement.executeQuery("select fnumber from CT_KP_SettlementBill where cfcontractcode in ('"+hetong+"')");
                while (rs.next()){
                    String fnumber = rs.getString("fnumber");
                    jiesuan = true;
                }

                boolean kaipiao = false;
                rs = statement.executeQuery("select a.cfamount,a.CFContractCode from CT_IM_MakeInvoiceAmtEntry a, T_IM_MakeInvoice b where a.fmakeinvoiceid=b.fid and a.CFContractCode in ('"+hetong+"')");
                while (rs.next()){
                    String CFContractCode = rs.getString("CFContractCode");
                    kaipiao = true;
                }

                boolean huikuan = false;
                rs = statement.executeQuery("select a.cfthistimeclaim,c.fnumber from CT_KP_ReceiptConfirm3Entry a,CT_KP_ReceiptConfirm3 b,CT_YCY_XyOrHt c where a.fparentid=b.fid and c.fid=a.cfconcodeid and c.fnumber in ('"+hetong+"')");
                while (rs.next()){
                    String fnumber = rs.getString("fnumber");
                    huikuan = true;
                }

                boolean tiaozheng = false;
                rs = statement.executeQuery("select fid from CT_KP_ReceiptCCE where cfconcodeid in (select fid from CT_YCY_XyOrHt where fnumber in ('"+hetong+"'))");
                while (rs.next()){
                    String fid = rs.getString("fid");
                    tiaozheng = true;
                }

                if(chushihua||jiesuan||kaipiao||huikuan||tiaozheng){
                    System.out.println("该合同做过业务了："+hetong);
                }else{
                    System.out.println("该合同可以置为失效："+hetong);
                    int i1 = 0;
                    i1 = statement.executeUpdate("update CT_YCY_XyOrHt set cfeffectiveornot=0 where fnumber='" + hetong + "'");
                    if(i1>0){
                        chuli++;
                        System.out.println("处理成功了："+hetong);
                    }


                }


                
            }

            System.out.println("一共处理了："+chuli+"条");









            /*rs = statement.executeQuery("select x.bfid,x.afid\n" +
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



            }*/



        }catch (Exception e){
            e.printStackTrace();
        }finally {
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