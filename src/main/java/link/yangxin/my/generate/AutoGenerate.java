package link.yangxin.my.generate;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import link.yangxin.my.generate.config.Config;
import link.yangxin.my.generate.po.Column;
import link.yangxin.my.generate.po.DatabaseProperties;
import link.yangxin.my.generate.template.FreemarkerUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class AutoGenerate {

    private Config config;

    private GenerateContext context;

    public AutoGenerate(Config config, DatabaseProperties databaseProperties) {
        this.config = config;
        context = new GenerateContext(config, databaseProperties);
    }

    public void generateEntity() {
        File file = new File(context.getEntityFilePath());

        Map<String, Object> param = new HashMap<>();
        param.put("package", context.getEntityPackage());
        param.put("swagger", config.isSwagger());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());

        param.put("entity", context.getEntityName());
        if (config.getSuperEntityClass() != null) {
            param.put("superEntityClass", config.getSuperEntityClass().getSimpleName());
            param.put("superEntityClass", "com.kangyouyun.yunyuhealth.entity.base.SysBase");
        }
        dealSuperEntityClass(config.getSuperEntityClass());

        List<Column> columnList = context.getTableInfo().getEntityColumnList();
        Set<String> importPackages = new HashSet<>();
        for (Column column : columnList) {
            if (!column.getDbColumnType().isBaseDataType()) {
                importPackages.add(column.getDbColumnType().getClazz().getName());
            }
        }
        if (config.getSuperEntityClass() != null) {
            importPackages.add(config.getSuperEntityClass().getName());
        }

        param.put("importPackages", importPackages);

        String string = FreemarkerUtils.freemarkerTemplateString("template/entity.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }

    public void generateVO() {
        File file = new File(context.getVOFilePath());
        if (file.exists()) {
            //throw new RuntimeException("VO已存在");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("package", context.getVoPackage());
        param.put("swagger", config.isSwagger());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());

        param.put("entity", context.getVoName());

        dealSuperEntityClass(config.getSuperEntityClass());

        List<Column> columnList = context.getTableInfo().getEntityColumnList();
        Set<String> importPackages = new HashSet<>();
        for (Column column : columnList) {
            if (!column.getDbColumnType().isBaseDataType()) {
                importPackages.add(column.getDbColumnType().getClazz().getName());
            }
        }

        param.put("importPackages", importPackages);

        String string = FreemarkerUtils.freemarkerTemplateString("template/vo.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }

    public void generateCreateRequest() {
        File file = new File(context.getCreateRequestFilePath());
        if (file.exists()) {
           // throw new RuntimeException("CreateRequest已存在");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("package", context.getCreateRequestPackage());
        param.put("swagger", config.isSwagger());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());

        param.put("entity", context.getCreateRequestName());

        dealSuperEntityClass(config.getSuperEntityClass());

        List<Column> columnList = context.getTableInfo().getEntityColumnList();
        Set<String> importPackages = new HashSet<>();
        for (Column column : columnList) {
            if (!column.getDbColumnType().isBaseDataType()) {
                importPackages.add(column.getDbColumnType().getClazz().getName());
            }
        }

        param.put("importPackages", importPackages);

        String string = FreemarkerUtils.freemarkerTemplateString("template/createRequest.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }
    public void generateQueryRequest() {
        File file = new File(context.getQueryRequestFilePath());
        if (file.exists()) {
            //throw new RuntimeException("QueryRequest已存在");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("package", context.getQueryRequestPackage());
        param.put("swagger", config.isSwagger());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());

        param.put("entity", context.getQueryRequestName());

        dealSuperEntityClass(config.getSuperEntityClass());

        List<Column> columnList = context.getTableInfo().getEntityColumnList();
        Set<String> importPackages = new HashSet<>();
        for (Column column : columnList) {
            if (!column.getDbColumnType().isBaseDataType()) {
                importPackages.add(column.getDbColumnType().getClazz().getName());
            }
        }

        param.put("importPackages", importPackages);

        String string = FreemarkerUtils.freemarkerTemplateString("template/queryRequest.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }

    public void generateDao() {
        File file = new File(context.getDaoFilePath());

        Map<String, Object> param = new HashMap<>();
        param.put("daoPackage", context.getDaoPackage());
        param.put("entityPackage", context.getEntityPackage() + "." + context.getEntityName());
        param.put("entityName", context.getEntityName());
        param.put("daoName", context.getDaoName());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());

        String string = FreemarkerUtils.freemarkerTemplateString("template/dao.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }

    public void generateMapperXml() {
        File file = new File(context.getMapperXmlFilePath());

        Map<String, Object> param = new HashMap<>();
        param.put("daoName", context.getDaoPackage() + "." + context.getDaoName());
        param.put("entityName", context.getEntityPackage() + "." + context.getEntityName());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));
        param.put("table", context.getTableInfo());
        param.put("baseResultMap", true);
        param.put("baseColumnList", true);
        param.put("allColumns", StrUtil.join(",", context.getTableInfo().getColumnList().stream().map(t -> "t.`" + t.getName() + "`").collect(Collectors.toList())));
        param.put("tableName", context.getTableInfo().getTableName());

        String string = FreemarkerUtils.freemarkerTemplateString("template/mapper.xml.ftl", param);
        FileUtil.writeBytes(string.getBytes(StandardCharsets.UTF_8), file);
    }

    public void generateService() {
        File serviceFile = new File(context.getServiceFilePath());
        File serviceImplFile = new File(context.getServiceImplFilePath());

        Map<String, Object> param = new HashMap<>();

        param.put("servicePackage", context.getServicePackage());
        param.put("entityPackage", context.getEntityPackage());
        param.put("entityName", context.getEntityName());
        param.put("comment", context.getTableInfo().getTableComment());
        param.put("serviceName", context.getServiceName());
        param.put("author", config.getAuthor());
        param.put("date", DateUtil.formatDate(new Date()));

        param.put("serviceImplName", context.getServiceImplName());
        param.put("serviceImplPackage", context.getServiceImplPackage());
        param.put("daoPackage", context.getDaoPackage());
        param.put("daoName", context.getDaoName());

        String serviceString = FreemarkerUtils.freemarkerTemplateString("template/service.java.ftl", param);
        String serviceImplString = FreemarkerUtils.freemarkerTemplateString("template/serviceImpl.java.ftl", param);
        FileUtil.writeBytes(serviceString.getBytes(StandardCharsets.UTF_8), serviceFile);
        FileUtil.writeBytes(serviceImplString.getBytes(StandardCharsets.UTF_8), serviceImplFile);
    }

    private void dealSuperEntityClass(Class<?> superClass) {
        List<Column> columnList = context.getTableInfo().getColumnList();
        if (superClass == null) {
            context.getTableInfo().setEntityColumnList(columnList);
            return;
        }
        Field[] fields = ReflectUtil.getFields(superClass, field -> !field.getName().equals("serialVersionUID"));
        List<Column> entityColumnList = new ArrayList<>();
        for (Column column : columnList) {
            Optional<Field> optional = Arrays.stream(fields).filter(f -> f.getName().equals(column.getPropertyName())).findFirst();
            if (optional.isPresent()) {
                continue;
            }
            entityColumnList.add(column);
        }
        context.getTableInfo().setEntityColumnList(entityColumnList);
    }
}
