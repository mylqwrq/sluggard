<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"[]>
<mapper namespace="${project.basePackage}.dao.${table.moduleName}Mapper">
 <resultMap id="BaseResultMap" type="${project.basePackage}.entity.${table.moduleName}Entity">
  <id column="${primary.columnName}" jdbcType="${primary.jdbcType}" property="${primary.fieldName}" />
<#if columns??>
 <#list columns as column>
  <#if !(column.columnName == primary.columnName)>
  <result column="${column.columnName}" jdbcType="${column.jdbcType}" property="${column.fieldName}" />
  </#if>
 </#list>
</#if>
 </resultMap>

 <sql id="Select_Field">
<#if columns??>
 <#list columns as column>
  ${table.tableName}.${column.columnName}
 </#list>
</#if>
 </sql>

 <sql id="Base_Where_Clause">
  <where>
   <trim prefixOverrides="and">
 <#if columns??>
  <#list columns as column>
   <#if column.javaType == "java.lang.String">
    <if test="${column.fieldName} != null and ${column.fieldName} != ''">and ${table.tableName}.${column.columnName} = ${r'#'}{${column.fieldName}}</if>
   <#else>
    <if test="${column.fieldName} != null">and ${table.tableName}.${column.columnName} = ${r'#'}{${column.fieldName}}</if>
   </#if>
  </#list>
 </#if>
   </trim>
  </where>
  <if test="sortString != null and sortString != ''">
   order by ${r'#'}{sortString}
  </if>
 </sql>

 <select id="selectCount" resultType="java.lang.Long" parameterType="${project.basePackage}.entity.${table.moduleName}Entity">
  select count(*) from ${table.tableName}
  <include refid="Base_Where_Clause" />
 </select>

 <select id="selectList" resultMap="BaseResultMap" parameterType="${project.basePackage}.entity.${table.moduleName}Entity">
  select
  <include refid="Select_Field" />
  from ${table.tableName}
  <include refid="Base_Where_Clause" />
 </select>

 <select id="selectById" resultMap="BaseResultMap" parameterType="${primary.javaType}">
  select
  <include refid="Select_Field" />
  from ${table.tableName}
  where ${table.tableName}.${primary.columnName} = ${r'#'}{${primary.fieldName}}
 </select>

 <delete id="deleteById" parameterType="${primary.javaType}">
  delete from ${table.tableName}
  where ${table.tableName}.${primary.columnName} =${r'#'}{${primary.fieldName}}
 </delete>

 <update id="updateById" parameterType="${project.basePackage}.entity.${table.moduleName}Entity">
  update ${table.tableName}
  <set>
 <#if columns??>
  <#list columns as column>
   <if test="${column.fieldName} != null">${column.columnName} = ${r'#'}{${column.fieldName}},</if>
  </#list>
 </#if>
  </set>
  <where>
   ${primary.columnName} = ${r'#'}{${primary.fieldName}}
  </where>
 </update>

 <insert id="insert" parameterType="${project.basePackage}.entity.${table.moduleName}Entity">
  insert into ${table.tableName}
  <trim prefix="(" suffix=")" suffixOverrides=",">
 <#if columns??>
  <#list columns as column>
   <if test="${column.fieldName} != null">${column.columnName},</if>
  </#list>
 </#if>
  </trim>
  <trim prefix="values (" suffix=")" suffixOverrides=",">
 <#if columns??>
  <#list columns as column>
   <if test="${column.fieldName} != null">${r'#'}{${column.fieldName}},</if>
  </#list>
 </#if>
  </trim>
 </insert>
</mapper>