<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"[]>
<mapper namespace="${basePackage}.mapper.${moduleName}Mapper">
 <resultMap id="BaseResultMap" type="${basePackage}.common.entity.${moduleName}Entity">
  <id column="${primary["columnName"]}" jdbcType="${primary["jdbcType"]}" property="${primary["fieldName"]}" />
<#if columns??>
 <#list columns as column>
  <#if !(column["columnName"] == primary["columnName"])>
  <result column="${column["columnName"]}" jdbcType="${column["jdbcType"]}" property="${column["fieldName"]}" />
  </#if>
 </#list>
</#if>
 </resultMap>

 <sql id="Select_Field">
<#if columns??>
 <#list columns as column>
  ${tableName}.${column["columnName"]}
 </#list>
</#if>
 </sql>

 <sql id="Base_Where_Clause">
  <where>
   <trim prefixOverrides="and">
 <#if columns??>
  <#list columns as column>
   <#if column["javaType"] == "java.lang.String">
    <if test="${column["fieldName"]} != null and ${column["fieldName"]} != ''">and ${tableName}.${column["columnName"]} = ${r'#'}{${column["fieldName"]}}</if>
   <#else>
    <if test="${column["fieldName"]} != null">and ${tableName}.${column["columnName"]} = ${r'#'}{${column["fieldName"]}}</if>
   </#if>
  </#list>
 </#if>
   </trim>
  </where>
  <if test="sortString != null and sortString != ''">
   order by ${r'#'}{sortString}
  </if>
 </sql>

 <select id="selectCount" resultType="java.lang.Long" parameterType="${basePackage}.common.vo.${moduleName}QueryVO">
  select count(*) from ${tableName}
  <include refid="Base_Where_Clause" />
 </select>

 <select id="select" resultMap="BaseResultMap" parameterType="${basePackage}.common.vo.${moduleName}QueryVO">
  select
  <include refid="Select_Field" />
  from ${tableName}
  <include refid="Base_Where_Clause" />
 </select>

 <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.Long">
  select
  <include refid="Select_Field" />
  from ${tableName}
  where ${tableName}.${primary["columnName"]} = ${r'#'}{${primary["columnName"]}}
 </select>

 <delete id="deleteById" parameterType="java.lang.Long">
  delete from ${tableName}
  where ${tableName}.${primary["columnName"]} =${r'#'}{${primary["columnName"]}}
 </delete>

 <update id="updateById" parameterType="${basePackage}.common.vo.${moduleName}UpdateVO">
  update ${tableName}
  <set>
 <#if columns??>
  <#list columns as column>
   <if test="${column["fieldName"]} != null">${column["columnName"]} = ${r'#'}{${column["fieldName"]}},</if>
  </#list>
 </#if>
  </set>
  <where>
   ${primary["columnName"]} = ${r'#'}{${primary["fieldName"]}}
  </where>
 </update>

 <insert id="insert" parameterType="${basePackage}.common.vo.${moduleName}UpdateVO">
  insert into ${tableName}
  <trim prefix="(" suffix=")" suffixOverrides=",">
 <#if columns??>
  <#list columns as column>
   <if test="${column["fieldName"]} != null">${column["columnName"]},</if>
  </#list>
 </#if>
  </trim>
  <trim prefix="values (" suffix=")" suffixOverrides=",">
 <#if columns??>
  <#list columns as column>
   <if test="${column["fieldName"]} != null">${r'#'}{${column["fieldName"]}},</if>
  </#list>
 </#if>
  </trim>
 </insert>
</mapper>