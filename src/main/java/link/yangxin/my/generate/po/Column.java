package link.yangxin.my.generate.po;

import link.yangxin.my.generate.convert.FieldConvert;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@lombok.Data
@lombok.EqualsAndHashCode(callSuper = false)
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 注释
     */
    private String comment;

    /**
     * 是否为主键
     */
    private boolean pk;

    /**
     * 类型
     */
    private String type;

    /**
     * Java类型
     */
    private String propertyType;

    /**
     * Java字段名字
     */
    private String propertyName;

    private DbColumnType dbColumnType;

    public void fieldConvert(FieldConvert fieldConvert) {
        DbColumnType dbColumnType = fieldConvert.convertToJavaField(this.type);
        Class<?> clazz = dbColumnType.getClazz();
        this.propertyType = clazz.getSimpleName();
        this.propertyName = this.name;
        this.dbColumnType = dbColumnType;
    }
}
