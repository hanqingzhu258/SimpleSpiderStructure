/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:SqlSessionFactoryGet.java
 * Date:18-8-10 上午10:55
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/10 10:55 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class SqlSessionFactoryGet {

    public static SqlSessionFactory getSqlSessionFactory(){
        String resource="mybatis-config.xml";
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static void closeSqlSession(SqlSession sqlSession){
        sqlSession.commit();
        sqlSession.close();
    }

}
