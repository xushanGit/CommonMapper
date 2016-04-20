package spring;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import pojo.User;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * DESC: 低入侵式 插件/框架设计,不影响现有的功能
 *  松散耦合 将减少系统设计的复杂程度,提高系统负载
 *  高聚合 使模块内部彼此依赖增高，是系统变模块化，便于分割任务。
 * Created by Administrator on 2016/4/19.
 */
public class MapperCommonTest extends BaseTest{

    /**
     * DESC: 继承接口后内建查询
     *
     * @throws Exception
     */
    @Test
    public void testCRUD() throws Exception {
        UserMapper mapper = getUserMapper();
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
        //Assert.assertEquals("1","2");
        List<User> w = mapper.selectByName("w");
        System.out.println(w.get(0).toString());
    }

    /**
     * DESC: 原生方式查询
     *
     */
    @Test
    public void testCustomMethod(){
        UserMapper userMapper = getUserMapper();
        List<User> w = userMapper.selectByName("w");
        System.out.println(w.get(0).toString());
    }

    /**
     * Criteria 通用查询
     */
    @Test
    public void testExample(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "w");
        UserMapper userMapper = getUserMapper();
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users.get(0).toString());
    }

    protected UserMapper getUserMapper(){
        return sqlSession.getMapper(UserMapper.class);
    }
}

