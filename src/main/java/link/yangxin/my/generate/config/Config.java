package link.yangxin.my.generate.config;

import link.yangxin.my.generate.convert.DefaultEntityNameConvert;
import link.yangxin.my.generate.convert.EntityNameConvert;
import link.yangxin.my.generate.convert.FieldConvert;
import link.yangxin.my.generate.convert.MysqlFieldConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Config {

    /**
     * 作者
     */
    private String author = "";

    /**
     * 是否为驼峰命名
     */
    private boolean camel = true;

    /**
     * 模块名字
     */
    private String moduleName;

    /**
     * 要生成的表名
     */
    private String tableName;

    /**
     * 项目中dao文件存放的路径(不含模块名字)
     */
    private String daoDir;

    /**
     * 项目中Service文件存放的路径（不含模块名字）
     */
    private String serviceDir;

    /**
     * 项目中xml文件存放的路径（不含模块名字）
     */
    private String xmlDir;

    /**
     * 项目中实体类文件存放的路径（不含模块名字）
     */
    private String entityDir;

    /**
     * dao包名（不含模块名字）
     */
    private String daoPackageName;

    /**
     * service包名（不含模块名字）
     */
    private String servicePackageName;

    /**
     * 实体类包名（不含模块名字）
     */
    private String entityPackageName;

    /**
     * 指定的文件目录是否已含模块名，如果是，那么生成的时候，就不会再加模块名字
     */
    private boolean dirContainsModule;

    /**
     * 指定的包名是否已含模块名，如果是，那么生成的时候，就不会再加模块名字
     */
    private boolean packageContainsModule;

    /**
     * 是否添加swagger注解
     */
    private boolean swagger = false;

    /**
     * 是否创建实体类
     */
    private boolean generateEntity = true;

    /**
     * 是否创建Service
     */
    private boolean generateService = true;

    /**
     * 是否创建xml文件
     */
    private boolean generateMapperXml = true;

    /**
     * 是否创建Dao文件
     */
    private boolean generateDao = true;

    /**
     * 实体类名字转换器
     */
    private EntityNameConvert entityNameConvert = new DefaultEntityNameConvert(null, "Tab");

    /**
     * 实体类父类
     */
    private Class<?> superEntityClass;

    /**
     * 字段类型转为JavaBean的类型
     */
    private FieldConvert fieldConvert = new MysqlFieldConvert();

    /**
     * Dao类名后缀，比如UserDao，如果是Mapper，则生成的类名为UserMapper
     */
    private String daoSuffix = "Dao";


}
