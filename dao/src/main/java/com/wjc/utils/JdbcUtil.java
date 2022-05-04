package com.wjc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author LONG
 * 数据库连接工具类
 */
public class JdbcUtil {
    private static DataSource ds;
    //静态代码块来初始化
    static {
        try {  //1.加载配置文件
            Properties properties = new Properties();
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取连接对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接对象
    public static Connection getConnnection() throws SQLException {
        return ds.getConnection();
    }
    //返回连接池
    public  static DataSource getDataSource(){
        return ds;
    }
    //关闭/返回资源
    public static void close(Connection connection, Statement statement){
        close(connection,statement,null);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}