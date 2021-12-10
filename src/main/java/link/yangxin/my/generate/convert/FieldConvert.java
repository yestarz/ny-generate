package link.yangxin.my.generate.convert;

import link.yangxin.my.generate.po.DbColumnType;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public interface FieldConvert {

    /**
     * mysql字段类型转换为Java的类型
     *
     * @param fileType
     * @return
     */
    DbColumnType convertToJavaField(String fileType);

}
