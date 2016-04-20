package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface UserMapper extends BaseMapper<User>,SelectByExampleMapper<User> {
    public List<User> selectByName(@Param("name") String name);
}
