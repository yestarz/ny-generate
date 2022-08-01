package ${servicePackage};

import ${entityPackage}.${entityName};
import com.baomidou.mybatisplus.extension.service.IService;
import com.kangyouyun.yunyuhealth.common.operator.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#assign voName = entityName + 'VO'>

/**
 * <p>
 * ${comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${serviceName} extends IService<${entityName}> {

    /**
     * 新增记录
     */
    Long create(${entityName}CreateRequest request,Operator operator);

    /**
     * 分页查询
     */
    Page<${voName}> queryPage(${entityName}QueryRequest request);

    /**
    * 删除记录
    */
    void delete(Long id,Operator operator);
}
