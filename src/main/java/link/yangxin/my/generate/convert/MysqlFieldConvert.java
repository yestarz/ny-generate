package link.yangxin.my.generate.convert;

import link.yangxin.my.generate.po.DbColumnType;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class MysqlFieldConvert implements FieldConvert {
    @Override
    public DbColumnType convertToJavaField(String fileType) {
        if (fileType.contains("bigint")) {
            return DbColumnType.BIGINT;
        }
        if (fileType.contains("int")) {
            return DbColumnType.INT;
        }
        if (fileType.contains("decimal")) {
            return DbColumnType.DECIMAL;
        }
        if (fileType.contains("date")) {
            return DbColumnType.DATETIME;
        }
        if (fileType.contains("varchar") || fileType.contains("text")) {
            return DbColumnType.VARCHAR;
        }
        return DbColumnType.VARCHAR;
    }
}
