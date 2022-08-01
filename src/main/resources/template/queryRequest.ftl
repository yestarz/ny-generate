package ${package};

<#list importPackages as pkg>
import ${pkg};
</#list>

<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.kangyouyun.yunyuhealth.common.request.ApiQueryRequest;

/**
 * ${table.tableName}
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@ApiModel(value = "${table.tableComment!}对象查询请求")
</#if>
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entity} extends ApiQueryRequest {

}
