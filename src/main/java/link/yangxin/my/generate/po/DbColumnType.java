package link.yangxin.my.generate.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public enum DbColumnType {

    INT(Integer.class,true),
    BIGINT(Long.class,true),
    DATETIME(Date.class,false),
    DECIMAL(BigDecimal.class,false),
    VARCHAR(String.class,true),

    ;
    /**
     * 包路径
     */
    private final Class<?> clazz;

    /**
     * 是否为基本数据类型
     */
    private boolean baseDataType;

    DbColumnType(final Class<?> clazz,boolean baseDataType) {
        this.clazz = clazz;
        this.baseDataType = baseDataType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean isBaseDataType() {
        return baseDataType;
    }
}
