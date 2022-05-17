package com.wjc.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BeanListHandler<T>implements IResultSetHandler <List<T>> {
    private Class<T> clazz;

    public BeanListHandler(Class<T> clazz){
        this.clazz = clazz;
    }



    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        //获取指定字节码信息
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz,Object.class);
        //获取所有属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        List<T> list = new ArrayList<>();
        while (rs.next()){
            T obj = clazz.newInstance();
            for (PropertyDescriptor pd:pds){
                try{
                    //获取结果集中对应字段名的值
                    Object o = rs.getObject(pd.getName());
                    //执行当前方法并传入参数
                    pd.getWriteMethod().invoke(obj,o);
                }catch (SQLException e){
//                    continue;
                    log.error("sql异常，可能是参数名与字段名不同");
                }

            }
            list.add(obj);
        }
        return list;

    }
}