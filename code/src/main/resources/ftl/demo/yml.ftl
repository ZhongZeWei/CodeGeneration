server:
  port: ${modelData.serverPort}
spring:
  application:
    name: ${modelData.applicationName}  # 微服务名称，如果只有单个微服务，服务名称没用。在服务间调用的时候，服务名称非常有用！！！
  datasource: #数据源
    url: jdbc:mysql://${mySQLMetaData.host}/${modelData.database}?characterEncoding=UTF8
    driver-class-name: com.mysql.jdbc.Driver
    username: ${mySQLMetaData.user}
    password: ${mySQLMetaData.password}