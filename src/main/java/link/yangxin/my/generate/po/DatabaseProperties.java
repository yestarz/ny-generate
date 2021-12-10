package link.yangxin.my.generate.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangxin
 * @date 2021/12/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseProperties {

    private String jdbcUrl;

    private String username;

    private String password;

    private String driverClassName;

}
