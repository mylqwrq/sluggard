# Sluggard

一款方便快捷的Java Web项目及代码生成服务

## 描述

基于SpringBoot、Lombok、Freemarker + Layui开发，提供项目和代码生成服务。

持久化采用自定义文件的方式实现，不依赖任何其他第三方组件，项目启动时会自动加载文件。

## 优势

1、提供映射管理功能，用户可根据实际业务需求管理映射关系；

2、提供模板管理功能，用户可根据实际的项目技术栈定制模板；

3、提供项目管理功能，代码生成时可选择项目自动获取相关信息；

4、支持项目自定义全局配置；

5、支持项目生成和单表生成；

6、内置了一些全局配置项：date（当前日期，格式为：yyyy/M/d）、tableName（表名称）、tableComment（表注释）、moduleName（首字母大写的模块名）、lowerModuleName（首字母小写的模块名）、
primary（主键列信息）、javaTypes（表字段映射的Java类型集合）、columns（所有列信息集合）、columnName（列名称）、columnComment（列注释）、fieldName（字段名）、
columnType（列映射的Java类型）、javaType（列映射的Java类型全路径）、jdbcType（列映射的Jdbc类型）

## 配置

默认配置在sluggard\sluggard-server\src\main\resources\sluggard.properties中。

## 打包

cd sluggard

mvn clean package -DskipTests

## 启动

将sluggard-server-xxx.jar与sluggard\deploy\目录下的SluggardServer.sh脚本置于相同的服务器目录下，并执行命令：

sh SluggardServer.sh start

## 注意

由于Oracle授权问题，无法从Maven中央仓库下载驱动包，如有需要请从官方下载手动安装到本地然后引入依赖即可。