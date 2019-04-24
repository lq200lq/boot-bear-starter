# boot-bear-starter

#### 介绍
项目快速启动器

#### yml示例
````
bear:
  enable: true

beetl:
  suffix: html

spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/pro_brand?useUnicode=true&characterEncoding=utf-8&serverTimezone=CTT
    username: root
    password: root

mybatis-plus:
  mapper-locations: classpath*:mapper/*.xml
  type-aliases-package: com.bearcloud.example.entity
  configuration:
    ## 开启驼峰
    map-underscore-to-camel-case: true
````
