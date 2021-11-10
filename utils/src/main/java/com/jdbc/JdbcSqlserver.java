package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcSqlserver {
    public static void main(String[] args) throws Exception{
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        String url= "jdbc:sqlserver://192.168.9.8:1433;databaseName=eastest1";//测试
        String user = "sa";
        String password = "hsjt.com";

//        String url= "jdbc:sqlserver://192.168.9.32:2163;databaseName=eas860";//生产
//        String user = "sa";
//        String password = "";

//        String url= "jdbc:sqlserver://localhost:1433;databaseName=db_hengsen_test";//本机
//        String user = "sa";
//        String password = "root";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            rs = statement.executeQuery("select fid,fname_l2 from T_PM_User ");
            while (rs.next()){
                System.out.println(rs.getString("fid")+"\t"+rs.getString("fname_l2"));
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