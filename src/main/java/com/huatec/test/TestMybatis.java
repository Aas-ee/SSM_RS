package com.huatec.test;

import com.huatec.dao.AdminDao;
import com.huatec.domain.Admin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

    public class TestMybatis {

        private InputStream in;
        private SqlSessionFactory factory;
        private SqlSession session;
        private AdminDao adminDao;

        @Before
        public void init() throws Exception{
            //加载配置文件
            in = Resources.getResourceAsStream("mybatis-config.xml");
            //创建SqlSessionFactory对象
            factory = new SqlSessionFactoryBuilder().build(in);
            //创建SqlSession对象
            session = factory.openSession();
            //获取代理对象
            adminDao = session.getMapper(AdminDao.class);
        }
        @After
        public void  destory()throws  Exception{
            session.commit();
            session.close();
            in.close();
        }
       @Test
        public void run1(){
            Admin admin = new Admin();
            admin.setUsername("钟俊源");
            admin.setId(30);

        }


    }
