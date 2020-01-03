# Sluggard

一款方便快捷的Java Web项目及代码生成服务

## 描述

基于SpringBoot、Freemarker + Layui开发，提供项目和代码生成服务。

持久化采用自定义文件的方式实现，不依赖任何其他第三方组件，项目启动时会自动加载文件。

## 优势

1、提供映射管理功能，用户可根据实际业务需求管理映射关系；

2、提供模板管理功能，用户可根据实际的项目技术栈定制模板；

3、提供项目管理功能，用户可根据实际需要保留常用项目信息；

4、支持自定义模板配置；

5、支持MySQL和Oracle两种数据库；

6、支持项目一键生成和单表代码生成；

## 配置

### 内置对象

对象|描述
---|---
project|项目信息
datasource|数据源信息
table|表信息
primary|主键列信息
columns|列信息集合

### 内置对象属性

属性|描述
---|---
project.name|项目名称
project.description|项目描述
project.basePackage|项目基本包路径
project.groupId|项目GroupId
project.artifactId|项目ArtifactId
project.version|项目版本
project.packaging|项目打包方式
project.serverPort|项目服务端口
project.serverPath|项目服务基本路径
datasource.url|数据库链接
datasource.dbType.getName()|数据库类型
datasource.ip|数据库地址
datasource.port|数据库端口
datasource.name|数据库地址
datasource.user|数据库用户
datasource.pwd|数据库密码
datasource.ignorePrefix|数据库忽略前缀
datasource.ignoreSuffix|数据库忽略后缀
table.tableName|表名称
table.tableComment|表注释
table.moduleName|表映射的模块名
column.columnName|列名称
column.dataType|列类型
column.columnDefault|列默认值
column.isNullable|列是否允许为空
column.columnLength|列长度
column.columnKey|列主键
column.columnComment|列注释
column.fieldName|列映射的字段名
column.javaType|列映射的Java类型（含包路径）
column.jdbcType|列映射的Jdbc类型
column.columnType|列映射的Java类型（不含包路径）

### 内置变量

属性|描述
---|---
javaTypeImports|POJO类应引入的数据类型包集合（java.lang包无须引入）
date|当前日期（yyyy/M/d）

## 打包

cd sluggard

mvn clean package -DskipTests

## 启动

将sluggard-server-xxx.jar与sluggard\deploy\目录下的SluggardServer.sh脚本置于相同的服务器目录下，并执行命令：

sh SluggardServer.sh start

## 注意

1、由于Oracle授权问题，无法从Maven中央仓库下载驱动包，如有需要请从官方下载手动安装到本地然后引入依赖即可。

2、配置自定义模板变量时不可与上述对象和变量重名，否则自定义模板变量不会生效。

3、使用Sluggard应遵循一定的开发规范。例如数据库设计表时应只有一个主键。
