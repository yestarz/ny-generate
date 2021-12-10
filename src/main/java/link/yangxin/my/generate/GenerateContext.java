package link.yangxin.my.generate;

import link.yangxin.my.generate.config.Config;
import link.yangxin.my.generate.po.DatabaseProperties;
import link.yangxin.my.generate.po.TableInfo;
import link.yangxin.my.generate.utils.JdbcUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GenerateContext {

    /**
     * 表结构信息
     */
    private TableInfo tableInfo;

    /**
     * 实体类目录
     */
    private String entityDir;

    /**
     * 实体类名
     */
    private String entityName;

    /**
     * 实体类包
     */
    private String entityPackage;

    /**
     * dao文件目录
     */
    private String daoDir;

    /**
     * dao文件名
     */
    private String daoName;

    /**
     * dao包名
     */
    private String daoPackage;

    /**
     * xml文件目录
     */
    private String xmlDir;

    /**
     * service类文件目录
     */
    private String serviceDir;

    /**
     * service类名
     */
    private String serviceName;

    /**
     * service包名
     */
    private String servicePackage;

    /**
     * service实现类目录
     */
    private String serviceImplDir;

    /**
     * service实现类类名
     */
    private String serviceImplName;

    /**
     * service实现类包名
     */
    private String serviceImplPackage;

    public GenerateContext(Config config, DatabaseProperties databaseProperties) {
        this.tableInfo = JdbcUtils.getTableInfo(config.getTableName(), databaseProperties);

        // 根据数据库类型，获取字段的JavaBean类型
        this.tableInfo.getColumnList().forEach(t -> t.fieldConvert(config.getFieldConvert()));
        entityName = config.getEntityNameConvert().convertEntityName(tableInfo);

        entityDir = config.isDirContainsModule() ? config.getEntityDir() : (config.getEntityDir() + File.separator + config.getModuleName());

        entityPackage = config.isPackageContainsModule() ? config.getEntityPackageName() : (config.getEntityPackageName() + "." + config.getModuleName());

        daoDir = config.isDirContainsModule() ? config.getDaoDir() : (config.getDaoDir() + File.separator + config.getModuleName());
        daoName = entityName + config.getDaoSuffix();

        daoPackage = config.isPackageContainsModule() ? config.getDaoPackageName() : (config.getDaoPackageName() + "." + config.getModuleName());
        xmlDir = config.isDirContainsModule() ? config.getXmlDir() : (config.getXmlDir() + File.separator + config.getModuleName());

        serviceDir = config.isDirContainsModule() ? config.getServiceDir() : (config.getServiceDir() + File.separator + config.getModuleName());
        serviceName = entityName + "Service";
        servicePackage = config.isPackageContainsModule() ? config.getServicePackageName() : (config.getServicePackageName() + "." + config.getModuleName());

        serviceImplDir = serviceDir + File.separator + "impl";
        serviceImplName = serviceName + "Impl";
        serviceImplPackage = servicePackage + ".impl";
    }

    /**
     * 获取实体类的文件路径
     *
     * @return
     */
    public String getEntityFilePath() {
        return entityDir + File.separator + entityName + ".java";
    }

    /**
     * 获取Dao类的文件路径
     *
     * @return
     */
    public String getDaoFilePath() {
        return daoDir + File.separator + daoName + ".java";
    }

    /**
     * 获取xml文件路径
     *
     * @return
     */
    public String getMapperXmlFilePath() {
        return xmlDir + File.separator + entityName + "Mapper" + ".xml";
    }

    /**
     * 获取service类的文件路径
     *
     * @return
     */
    public String getServiceFilePath() {
        return serviceDir + File.separator + serviceName + ".java";
    }

    /**
     * 获取service实现类的文件路径
     *
     * @return
     */
    public String getServiceImplFilePath() {
        return serviceImplDir + File.separator + serviceImplName + ".java";
    }
}
