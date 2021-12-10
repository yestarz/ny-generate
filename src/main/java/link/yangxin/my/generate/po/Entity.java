package link.yangxin.my.generate.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Entity {

    /**
     * 实体类名字
     */
    private String entityName;

    /**
     * 父类
     */
    private Class<?> superClass;

}
