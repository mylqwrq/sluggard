server:
  port: ${project.serverPort}
  servlet:
    context-path: "${project.serverPath}"

spring:
  application:
    name: ${project.name}
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    url: ${datasource.url}
    username: ${datasource.user}
    password: ${datasource.pwd}
    # 初始化大小，最小，最大
    initialSize: 1
    minIdle: 3
    maxActive: 200
    # 配置获取连接等待超时的时间
    maxWait: 60000
    # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 30000
    validationQuery: select 'x'
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    # 打开PSCache，并且指定每个连接上PSCache的大小
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat,wall,slf4j
    # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
  mapper-locations: classpath:/mapper/*Mapper.xml

pagehelper:
  helperDialect: ${datasource.dbType.getName()}
  reasonable: false
  supportMethodsArguments: true
  params: count=countSql

knife4j:
  # 是否开启增强模式
  enable: false
  # 是否开启生产环境保护策略
  production: false
  # 访问权限控制
  basic:
    enable: false
    username: test
    password: 123456
