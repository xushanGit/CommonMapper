## 通用Mapper 简单教程 
### I. 使用mybatis 原生方式
1. 在SessionFactory 中配置 mapper 的xml 文件位置（xml 与 java文件最好不要放到同一目录）。在mybatis的xml
中已经指定namespace，也就是说只要指定xml文件mybatis 就能生成代理类。扫描器一个很大的作用就是生成
代理类的实例放到spring容器中。通用Mapper项目的原理是通过对pojo的配置生成mapper的xml SQL语句，所以如果仅仅需要
基本dao方法可以不用写xml文件了，仅仅配置扫描器即可。扫描器会根据继承的接口生成代理方法。
```xml 
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <property name="mapperLocations">
            <array>
                <value>classpath:mapper/mysql/*.xml</value>
            </array>
        </property>
</bean>
```
2. 使用改写后的扫描器(org 改成tk 即可)
```
 <bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="mapper"></property>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
 </bean><!-- sqlSessionFactory -->
```
### II. Criteria查询
Criteria 不依赖具体的SQL语句而是使用对象描述查询关系，所以Criteria可以适应多种数据库。
在通用mapper中可以使用下列方式创建Criteria 查询。
**查询参考**
```
Example example = new Example(User.class);
Example.Criteria criteria = example.createCriteria();
criteria.andEqualTo("name", "w");
UserMapper userMapper = getUserMapper();
List<User> users = userMapper.selectByExample(example);
```
### III. 内建查询 
此种方式，要求mapper 接口继承现有的Dao接口。通用Mapper会帮我们自动实现继承过来
的接口方法而不是需要我们在xml中手写SQL语句。作者为我们提供多个实现的接口。参考：
[Mapper3通用接口大全](http://git.oschina.net/free/Mapper/blob/master/wiki/mapper3/5.Mappers.md/)。 这些接口需要一个泛型参数来指定该Dao操作的实体类型。还需要使
用JPA注解实现实体bean与表的映射。有了实体bean与数据表的映射关系Mapper就能给我们
的Mapper接口生成Dao代理类了。
**pojo 配置**
```java
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String name ;
    private String password ;
    private String email ;
    /*get & set */
}
```
**mapper接口**
```
public interface UserMapper extends BaseMapper<User>,SelectByExampleMapper<User> {
    public List<User> selectByName(@Param("name") String name);
}
```
**查询参考**
```
 @Test
    public void testCRUD() throws Exception {
        UserMapper mapper = getUserMapper();
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
        //Assert.assertEquals("1","2");
        List<User> w = mapper.selectByName("w");
        System.out.println(w.get(0).toString());
    }
```