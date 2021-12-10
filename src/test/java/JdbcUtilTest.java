import link.yangxin.my.generate.po.DatabaseProperties;
import link.yangxin.my.generate.po.TableInfo;
import link.yangxin.my.generate.utils.JdbcUtils;
import org.junit.Test;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class JdbcUtilTest {

    @Test
    public void test1(){
        DatabaseProperties databaseProperties = new DatabaseProperties();
        databaseProperties.setJdbcUrl("jdbc:mysql://192.168.1.245:3306/yunyutong_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        databaseProperties.setUsername("root");
        databaseProperties.setPassword("root");
        databaseProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");

        TableInfo tableInfo = JdbcUtils.getTableInfo("yz_item_tab", databaseProperties);
        System.out.println(tableInfo);
    }

}
