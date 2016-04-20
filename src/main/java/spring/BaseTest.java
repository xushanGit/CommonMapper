package spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/4/19.
 */
public  class BaseTest {


    protected ApplicationContext applicationContext ;
    SqlSession sqlSession = null ;

    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContenxt.xml");
        DefaultSqlSessionFactory defaultSqlSessionFactory = applicationContext.getBean("sqlSessionFactory", DefaultSqlSessionFactory.class);
        sqlSession = defaultSqlSessionFactory.openSession();
    }

    public static void main(String[] args) {

    }
    @After
    public void after(){
        if(sqlSession !=null){
            sqlSession.close();
        }
    }
}
