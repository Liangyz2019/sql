package com.example.sqlconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
    public static Connection getConnection(String dbName) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "120.24.175.214";
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/" + dbName,
                    "root", "12345678");
            MainActivity.conn_on=1;//用于向主函数传参，判断连接是否成功
        }catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            MainActivity.conn_on=2;//用于向主函数传参，判断连接是否成功
        }
        return conn;//返回Connection型变量conn用于后续连接
    }
    public static int register(final String username, final String password) throws SQLException {//注册
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "insert INTO login (username,password)VALUES('"+username+"','"+password+"')";//把用户名和密码插入到数据库中
        return stmt.executeUpdate(sql);
        //执行DML语句，返回受影响的记录条数
    }
    public static String search_pw(final String username) throws SQLException {//读取某一行密码
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select password from login where username='"+username+"'");//从数据库中查询用户名对应的密码并返回
        rs.first();
        //if(rs.next());
        a=rs.getString(1);
        //rs.getInt();
        rs.close();
        return a;
        //把查询结果输出来
    }
    public static int updatexy(final String username, final int x,final int y) throws SQLException {//更新用户位置信息
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "update login set x="+x+",y="+y+" where username='"+username+"')";//更新特定用户x，y信息到数据库中
        return stmt.executeUpdate(sql);
        //执行DML语句，返回受影响的记录条数
    }
    public static int updatecar(final String username, final String carnum) throws SQLException {//增加数据
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "update login set carnum='"+carnum+"' where username='"+username+"')";//把用户名和密码插入到数据库中
        return stmt.executeUpdate(sql);
        //执行DML语句，返回受影响的记录条数
    }
    public static String checksta(final String park) throws SQLException {//读取车位状态
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select state from login where park='"+park+"'");//从数据库中查询车位状态并返回
        rs.first();
        a=rs.getString(1);
        rs.close();
        return a;
        //把查询结果输出来
    }
    public static String checkpeo(final String park) throws SQLException {//读取未停车的车辆位置信息
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select x,y from login where park='null'");//从数据库中查询位置信息并返回
        rs.first();
        a=rs.getString(1);
        rs.close();
        return a;
        //把查询结果输出来
    }
    //select username from login where x between 15 and 18 and y between 15 and 18;
    public static String back(final String carnum) throws SQLException {//获取该车牌所在车位位置信息
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("whoere");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select x,y from parking where carnum='"+carnum+"'");//从数据库中查询车牌对应的位置信息并返回
        rs.first();
        a=rs.getString(1);
        rs.close();
        return a;
        //把查询结果输出来
    }
}

