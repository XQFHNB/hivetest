package com.xqf.hive;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-24 15:02
 */
public class HiveService {
    static Logger logger = Logger.getLogger(HiveService.class);
    //hive的jdbc驱动类
    public static String dirverName = "org.apache.hive.jdbc.HiveDriver";
    //连接hive的URL hive1.2.1版本需要的是jdbc:hive2，而不是 jdbc:hive
    public static String url = "jdbc:hive2://master:10000/graduation_1";
    //登录linux的用户名  一般会给权限大一点的用户，否则无法进行事务形操作
    public static String user = "root";
    //登录linux的密码
    public static String pass = "125880";

    /**
     * 创建连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(dirverName);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 创建命令
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static Statement getStmt(Connection conn) throws SQLException {
        logger.debug(conn);
        if (conn == null) {
            logger.debug("this conn is null");
        }
        return conn.createStatement();
    }

    /**
     * 关闭连接
     *
     * @param conn
     */
    public static void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 关闭命令
     *
     * @param stmt
     */
    public static void closeStmt(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = getConn();
        if (connection != null) {
            System.out.println("连接成功");
        }
    }
}