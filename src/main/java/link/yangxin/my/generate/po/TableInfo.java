package link.yangxin.my.generate.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 字段
     */
    private List<Column> columnList;

    /**
     * 实际生成的实体类字段（不含实体类父类）
     */
    private List<Column> entityColumnList;

}
