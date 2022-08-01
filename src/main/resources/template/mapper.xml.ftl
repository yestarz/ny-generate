<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoName}">

    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${entityName}">
            <#list table.columnList as field>
                <#if field.pk><#--生成主键排在第一位-->
                    <id column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
            <#list table.columnList as field>
                <#if !field.pk><#--生成普通字段 -->
                    <result column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <!--@sql select -->
           ${allColumns!}
            <!--@sql from ${tableName!} t -->
        </sql>
    </#if>
</mapper>
