package ${package};

<#list importPackages as pkg>
import ${pkg};
</#list>
import com.baomidou.mybatisplus.annotation.TableName;

<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.tableName}
 * @author ${author}
 * @since ${date}
 */
@TableName("${table.tableName}")
<#if swagger>
@ApiModel(value = "${table.tableComment!}对象")
</#if>
@Data
@EqualsAndHashCode(callSuper = false)
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass} {
<#else>
public class ${entity} {
</#if>
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
    <#if field.pk>
    <#-- 主键 -->
    @TableId(type = IdType.AUTO)
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
}
