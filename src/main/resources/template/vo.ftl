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

/**
 * ${table.tableName}
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@ApiModel(value = "${table.tableComment!}对象VO")
</#if>
@Data
@EqualsAndHashCode(callSuper = false)
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass} {
<#else>
public class ${entity} {
</#if>

    /**id*/
    @ApiModelProperty("主键id")
    private Long id;
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

    /**修改时间*/
    @ApiModelProperty("修改时间")
    private Date update_time;
}
