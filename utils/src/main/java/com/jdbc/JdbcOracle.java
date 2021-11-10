package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcOracle {
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

            list.add("重庆长安汽车股份有限公司");
            list.add("北京理工华创电动车技术有限公司");
            list.add("合肥美桥汽车传动及底盘系统有限公司");
            list.add("合肥美桥汽车传动及底盘系统有限公司");
            list.add("合肥美桥汽车传动及底盘系统有限公司");









            for (int i = 0; i < list.size(); i++) {
                rs = statement.executeQuery("select FNumber,fname_l2 from T_BD_Customer where fname_l2='"+list.get(i).trim()+"'");
                while (rs.next()){
                    System.out.println(rs.getString("FNumber")+"\t"+rs.getString("fname_l2"));
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