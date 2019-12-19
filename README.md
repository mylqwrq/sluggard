# Sluggard

一款方便快捷的Java Web项目及代码生成服务

## 描述

基于SpringBoot、Lombok、Freemarker + Layui开发，提供项目和代码生成服务。

持久化采用自定义文件的方式实现，不依赖任何其他第三方组件，项目启动时会自动加载文件。

## 优势

1、提供映射管理功能，用户可根据实际业务需求管理映射关系；

2、提供模板管理功能，用户可根据实际的项目技术栈定制模板；

3、提供项目管理功能，用户可根据实际需要保留常用项目信息；

4、支持自定义模板配置；

5、支持MySQL和Oracle两种数据库；

6、支持项目一键生成和单表代码生成；

## 配置

下面是内置的一些模板配置：

name：项目名称

description：项目描述

groupId：Maven项目的groupId

artifactId：Maven项目的artifactId

version：Maven项目的version

packaging：Maven项目的打包方式

date：当前日期（注释用，yyyy/M/d格式）

author：作者（注释用，默认为Sluggard）

tableName：表名称

tableComment：表注释

moduleName：首字母大写的模块名

lowerModuleName：首字母小写的模块名

primary：主键列信息

javaTypes：表字段映射的Java类型集合

columns：所有列信息集合

columnName：列名称

columnComment：列注释

fieldName：字段名

columnType：列映射的Java类型

javaType：列映射的Java类型（带包名）

jdbcType：列映射的Jdbc类型

## 打包

cd sluggard

mvn clean package -DskipTests

## 启动

将sluggard-server-xxx.jar与sluggard\deploy\目录下的SluggardServer.sh脚本置于相同的服务器目录下，并执行命令：

sh SluggardServer.sh start

## 注意

由于Oracle授权问题，无法从Maven中央仓库下载驱动包，如有需要请从官方下载手动安装到本地然后引入依赖即可。
