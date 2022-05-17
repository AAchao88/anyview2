package com.wjc.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LONG
 * 数据库增删查该工具类
 */
@Slf4j
public class CRUDUtil {

    /**
     * 增删改操作
     * @param sql 传入的SQL语句
     * @param params 可变参数
     * @return 操作结果
     */
    public static int executeUpdate(String sql,Object... params){
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = 0;
        try {
            //获取数据库连接对象
            conn = JdbcUtil.getConnnection();
            //开启事务，即关闭数据库的自动提交
            conn.setAutoCommit(false);

            //获取预编译语句对象
            psmt = conn.prepareStatement(sql);
            //给预编译语句赋值
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
            //执行SQL语句获取执行结果
            result = psmt.executeUpdate();
            //业务完毕，提交事务
            conn.commit();

        } catch (SQLException e) {
//            e.printStackTrace();
            log.error("sql异常，可能是传入参数与所需参数不匹配");
        } finally {
            //关闭数据库连接
            JdbcUtil.close(conn,psmt,null);
        }
        return result;
    }

    /**
     * 查询操作
     * @param sql SQL语句
     * @param params 可变参数
     * @return 返回Resultset
     */
    public static  <T> T executeQuery(String sql, IResultSetHandler<T> handler, Object... params){
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            //获取数据库连接对象
            conn = JdbcUtil.getConnnection();
            //开启事务，即关闭数据库的自动提交
            conn.setAutoCommit(false);

            //获取预编译语句对象
            psmt = conn.prepareStatement(sql);
            //给预编译语句赋值
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
            //执行SQL语句获取结果集
            rs = psmt.executeQuery();

            //业务完毕，提交事务
            conn.commit();

            //处理结果
            return handler.handle(rs);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("sql异常，可能是传入参数与所需参数不匹配");
        }finally {
            JdbcUtil.close(conn,psmt,rs);
        }
        return null;
    }

}