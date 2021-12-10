
package base;



import java.util.Date;

/**
 * @author <a href="mailto:xuyy@yyt.com">Xu Yuanyuan</a>
 * @version 1.0
 * @date 2019年3月17日 下午4:43:49
 * @desc 系统管理基础类
 */
public class SysBase extends EntityBase {

    private static final long serialVersionUID = 769253799175569696L;

    /**
     * 创建人Id
     */
    protected Long create_user_id;
    /**
     * 创建人名称
     */
    protected String create_user_name;
    /**
     * 更新人ID
     */
    protected Long update_user_id;
    /**
     * 更新人名称
     */
    protected String update_user_name;

    public Long getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Long create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }
}
