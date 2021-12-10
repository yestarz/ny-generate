package base;


import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:xuyy@yyt.com">Xu Yuanyuan</a>
 * @version 1.0
 * @date 2019年3月17日 下午4:38:37
 * @desc EntityBase
 */
public class EntityBase implements Serializable {

    private static final long serialVersionUID = 6013100344516308988L;

    /**
     * 主键
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date create_time;

    /**
     * 更新时间
     */
    protected Date update_time;

    /**
     * 状态
     */
    protected Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public <T extends EntityBase> void copyBaseInfo(T copyEntity) {
        this.id = copyEntity.getId();
        this.create_time = copyEntity.getCreate_time();
        this.status = copyEntity.getStatus();
    }


}
