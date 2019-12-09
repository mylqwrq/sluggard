# Sluggard

Sluggard代码生成器

## 描述

基于SpringBoot和Freemarker模板引擎开发，以项目为核心概念，提供项目管理和代码生成服务。

项目信息缓存在内存中，用户可以直接操作。
持久化用自定义文件的方式实现，不依赖任何其他第三方组件。
内存和文件作定时同步，项目启动时会自动加载文件。

## 优势

1、提供项目管理功能，代码生成时可选择项目自动获取相关信息；

2、项目密码默认为数据源密码并加密，简化操作的同时，也防止了其他人误操作；

3、提供项目模板和配置项（模板需要的参数）克隆功能，创建项目时可以选择从其他项目中复制模板和配置项，也可以选择系统提供的默认模板和配置项，非常灵活；

4、生成代码时只需要选择项目和要生成的表，然后输入配置项的值，就可以下载相关代码的压缩包，非常简便；

5、配置项支持项目级全局配置；

6、模板和配置项可自定义修改，根据项目架构不同灵活配置；

7、内置了一些全局配置项：date（当前日期，格式为：yyyy/M/d）、tableName（表名称）、tableComment（表注释）、moduleName（首字母大写的模块名）、lowerModuleName（首字母小写的模块名）、
primary（主键列信息）、javaTypes（表字段映射的Java类型集合）、columns（所有列信息集合）、columnName（列名称）、columnComment（列注释）、fieldName（字段名）、
columnType（列映射的Java类型）、javaType（列映射的Java类型全路径）、jdbcType（列映射的Jdbc类型）

## 配置

配置在sluggard\sluggard-server\src\main\resources\config.properties中：

default.keys：默认的配置项键，多个以英文逗号分隔；

*.url：数据库连接字符串

*.java.*：数据库类型与Java类型的映射

*.jdbc.*：数据库类型与Jdbc类型的映射

## 打包

### sluggard-parent

cd sluggard\sluggard-parent

mvn clean install -DskipTests

### sluggard-core

cd sluggard\sluggard-core

mvn clean install -DskipTests

### sluggard-server

cd sluggard\sluggard-server

mvn clean install -DskipTests

## 启动

### sluggard-server

将sluggard-server-xxx.jar与sluggard\deploy\目录下的SluggardServer.sh脚本置于相同的服务器目录下，并执行命令：

sh SluggardServer.sh start
