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
import java.util.Date;
import com.kangyouyun.yunyuhealth.common.request.ApiModelRequest;
import com.kangyouyun.yunyuhealth.common.request.ApiCreateRequest;

/**
 * ${table.tableName}
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@ApiModel(value = "${table.tableComment!}对象新增请求")
</#if>
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entity} extends ApiCreateRequest {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.entityColumnList as field>

    <#if field.comment!?length gt 0>
        <#if swagger>
    @ApiModelProperty("${field.comment}")
        <#else>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
