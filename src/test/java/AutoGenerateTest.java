import base.SysBase;
import cn.hutool.core.util.ReflectUtil;
import link.yangxin.my.generate.AutoGenerate;
import link.yangxin.my.generate.config.Config;
import link.yangxin.my.generate.convert.DefaultEntityNameConvert;
import link.yangxin.my.generate.convert.MysqlFieldConvert;
import link.yangxin.my.generate.po.DatabaseProperties;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class AutoGenerateTest {

    DatabaseProperties databaseProperties = new DatabaseProperties();

    Config config = new Config();

    @Before
    public void init() {
        databaseProperties.setJdbcUrl("jdbc:mysql://192.168.1.245:3306/yunyutong_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        databaseProperties.setUsername("root");
        databaseProperties.setPassword("root");
        databaseProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setCamel(false);
        config.setModuleName("sys");
        config.setTableName("sys_admin_tab");
        config.setDaoDir("F:\\yx\\git\\yunyutong-new\\mami-guardian\\src\\main\\java\\com\\yyt\\yunyutong\\mamiguardian\\mapper");
        config.setServiceDir("F:\\yx\\git\\yunyutong-new\\mami-guardian\\src\\main\\java\\com\\yyt\\yunyutong\\mamiguardian\\service");
        config.setXmlDir("F:\\yx\\git\\yunyutong-new\\mami-guardian\\src\\main\\resources\\mapper");
        config.setEntityDir("F:\\yx\\git\\yunyutong-new\\mami-guardian\\src\\main\\java\\com\\yyt\\yunyutong\\mamiguardian\\entity");
        config.setDaoPackageName("com.yyt.yunyutong.mamiguardian.mapper");
        config.setServicePackageName("com.yyt.yunyutong.mamiguardian.service");
        config.setEntityPackageName("com.yyt.yunyutong.mamiguardian.entity");
        config.setDirContainsModule(false);
        config.setPackageContainsModule(false);
        config.setSwagger(true);
        config.setGenerateEntity(true);
        config.setGenerateService(true);
        config.setGenerateMapperXml(true);
        config.setGenerateDao(true);
        config.setEntityNameConvert(new DefaultEntityNameConvert(null,"Tab"));
        config.setSuperEntityClass(SysBase.class);
        config.setFieldConvert(new MysqlFieldConvert());

    }

    @Test
    public void test1() {
        AutoGenerate autoGenerate = new AutoGenerate(config, databaseProperties);
        autoGenerate.generateEntity();
    }

    @Test
    public void test2() {
        AutoGenerate autoGenerate = new AutoGenerate(config, databaseProperties);
        autoGenerate.generateDao();
    }

    @Test
    public void test3() {
        AutoGenerate autoGenerate = new AutoGenerate(config, databaseProperties);
        autoGenerate.generateMapperXml();
    }

    @Test
    public void test4() {
        AutoGenerate autoGenerate = new AutoGenerate(config, databaseProperties);
        autoGenerate.generateService();
    }


    @Test
    public void test1111(){
        Field[] fields = ReflectUtil.getFields(SysBase.class, field -> !field.getName().equals("serialVersionUID"));
        Arrays.stream(fields).forEach(t-> System.out.println(t.getName()));
    }

}
