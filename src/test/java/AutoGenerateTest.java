import base.SysBase;
import cn.hutool.core.util.ReflectUtil;
import link.yangxin.my.generate.AutoGenerate;
import link.yangxin.my.generate.config.Config;
import link.yangxin.my.generate.convert.DefaultEntityNameConvert;
import link.yangxin.my.generate.convert.MysqlFieldConvert;
import link.yangxin.my.generate.po.DatabaseProperties;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class AutoGenerateTest {

    DatabaseProperties databaseProperties = new DatabaseProperties();

    Config config = new Config();

    private String tableName = "hospital_home_page_tab";

    private String moduleName = "hospital";

    @Before
    public void init() {
        databaseProperties.setJdbcUrl("jdbc:mysql://192.168.0.101:3306/yunyutong_dev?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        databaseProperties.setUsername("root");
        databaseProperties.setPassword("123456");
        databaseProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setCamel(false);
        config.setModuleName(moduleName);
        config.setTableName(tableName);
        config.setDaoDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-dao\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\dao\\mapper");
        config.setServiceDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-service\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\service");
        config.setXmlDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-dao\\src\\main\\resources\\mapperxml");
        config.setEntityDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-entity\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\entity\\dbo");
        config.setVoDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-entity\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\entity\\vo");
        config.setCreateRequestDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-entity\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\entity\\request");
        config.setQueryRequestDir("E:\\code\\yunyu-health-parent\\yunyu-health-backend\\yunyu-health-entity\\src\\main\\java\\com\\kangyouyun\\yunyuhealth\\entity\\request");
        config.setDaoPackageName("com.kangyouyun.yunyuhealth.dao.mapper");
        config.setServicePackageName("com.kangyouyun.yunyuhealth.service");
        config.setEntityPackageName("com.kangyouyun.yunyuhealth.entity.dbo");
        config.setVoPackageName("com.kangyouyun.yunyuhealth.entity.vo");
        config.setCreateRequestPackageName("com.kangyouyun.yunyuhealth.entity.request");
        config.setQueryRequestPackageName("com.kangyouyun.yunyuhealth.entity.request");
        config.setDirContainsModule(false);
        config.setPackageContainsModule(false);
        config.setSwagger(true);
        config.setGenerateEntity(true);
        config.setGenerateService(true);
        config.setGenerateMapperXml(true);
        config.setGenerateDao(true);
        config.setEntityNameConvert(new DefaultEntityNameConvert(null, "Tab"));
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
    public void test1111() {
        Field[] fields = ReflectUtil.getFields(SysBase.class, field -> !field.getName().equals("serialVersionUID"));
        Arrays.stream(fields).forEach(t -> System.out.println(t.getName()));
    }

    @Test
    public void testGenerateAll() {
        AutoGenerate autoGenerate = new AutoGenerate(config, databaseProperties);
        autoGenerate.generateEntity();
        autoGenerate.generateDao();
        autoGenerate.generateMapperXml();
        autoGenerate.generateService();
        autoGenerate.generateVO();
        autoGenerate.generateCreateRequest();
        autoGenerate.generateQueryRequest();
    }

}
