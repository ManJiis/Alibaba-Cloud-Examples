# 工程简介

为了区分环境或项目间的配置，我们需要了解 Nacos 给出的如下3个概念：

- 命名空间(Namespace)

命名空间可用于进行不同环境的配置隔离。一般一个环境划分到一个命名空间

- 配置分组(Group)

配置分组用于将不同的服务可以归类到同一分组。一般将一个项目的配置分到一组

- 配置集(Data ID)

在系统中，一个配置文件通常就是一个配置集。一般微服务的配置就是一个配置集

[配置中心](src/main/resources/static/nacos-config-01.jpg)

**在nacos新建配置文件时的`Data ID`命名注意事项**

在 Nacos Spring Cloud 中，dataId 的完整格式如下：

${prefix}-${spring.profile.active}.${file-extension}

> `prefix` 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
> 
> `spring.profile.active` 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。
>> 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
> 
> `file-exetension` 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。


# 延伸阅读

