package link.yangxin.my.generate.po;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public interface JdbcType {

    <T> Class<T> getJdbcType(String type);

}
