/**
 * Created by Administrator on 2016/4/19.
 */
package common;

import org.junit.Test;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class CommonTest {


    @Test
    public void testRead(){
        MapperHelper mapperHelper = new MapperHelper();
        //特殊配置
        Config config = new Config();
        //具体支持的参数看后面的文档
        //设置配置
        mapperHelper.setConfig(config);
        // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
        mapperHelper.registerMapper(Mapper.class);
        //配置完成后，执行下面的操作
        //mapperHelper.processConfiguration(session.getConfiguration());
    }
}
