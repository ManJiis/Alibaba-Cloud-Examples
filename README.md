# Spring Cloud Alibaba Dubbo Examples

## Spring Cloud Alibaba 组件。
> Spring Cloud Alibaba版本说明: [https://github.com/alibaba/spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

1. 服务注册与发现
```xml
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

2. 配置中心

```xml

<dependency>
   <groupId>com.alibaba.cloud</groupId>
   <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

Nacos配置中心：

为了区分环境或项目间的配置，我们需要了解 Nacos 给出的如下3个概念：

- 命名空间(Namespace)

命名空间可用于进行不同环境的配置隔离。一般一个环境(dev,demo,prod)划分到一个命名空间

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

例如:

- b0x0-cloud-test-dev.yaml
- b0x0-cloud-order-dev.yaml
- b0x0-cloud-user-dev.yaml
- b0x0-cloud-auth-dev.yaml
- b0x0-cloud-gateway-dev.yaml

3. 流量控制、熔断降级、系统负载保护

```xml
        <!-- sentinel 限流 服务监控 -->
<dependency>
   <groupId>com.alibaba.cloud</groupId>
   <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
        <!-- 将服务接入到sentinel控制台 -->
<dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-transport-simple-http</artifactId>
         </dependency>
        <!-- 对Dubbo接口做限流控制，需要添加一个Sentinel-Dubbo的适配器 -->
         <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-apache-dubbo-adapter</artifactId>
         </dependency>
        <!-- sentinel规则持久化 引入之后使用nacos作为sentinel配置中心-->
         <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
         </dependency>
```
4. 网关
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
```
5. Java诊断工具
```xml
        <dependency>
            <groupId>com.taobao.arthas</groupId>
            <artifactId>arthas-spring-boot-starter</artifactId>
        </dependency>
```

## 模块实现

nacos版本: 1.3.1

| 模块 | 描述 | 端口 |
| --- | --- | --- |
| **cloud-gateway**  | 网关服务，负责请求转发和鉴权功能，整合JWT；               |   8093| 
| **cloud-auth**     | 认证服务，负责对登录用户进行认证，整合JWT；                |   8092| 
| **cloud-user**     | 受保护的API服务，用户鉴权通过后可以访问该服务，不整合JWT； |  8091| 
| **cloud-order**    | 受保护的API服务，用户鉴权通过后可以访问该服务，不整合JWT； |  8090|

![流程图](admin_docs/images/Alibaba-Cloud-Examples.png)

问题补充:

1 怎么防止客户端直接访问微服务
我们都知道网关适合做认证和鉴权，但是在安全层面，我们要求更严格的权限，对于有些项目来说，本身网络跟外部隔离，再加上其它的安全手段，所以我们只要求在网关上鉴权就可以了。

具体来说，**cloud-user**等微服务所在服务器设置防火墙限定IP和端口只能由**cloud-gateway**来访问，客户端所有请求，都先通过**cloud-gateway**的权限校验才能转发访问到**cloud-user**等微服务接口

2 服务与服务之前的权限怎么控制？
一般情况下，服务与服务之间可以直接调用，不需要加权限控制。但有些项目权限控制要求比较高，要求服务对服务之间的调用进行鉴权，知道某个用户是否有权限调用某个接口，这些都需要进行鉴权，这时的方案如下。

1、在Gateway网关层做认证，通过用户校验后，传递用户信息到Header中，后台服务在收到Header后进行解析，解析完后查看是否有调用此服务或者某个url的权限，然后完成鉴权。

2、从服务内部发出的请求，在出去时进行拦截，把用户信息保存在Header里，然后传出去,被调用方取到Header后进行解析和鉴权

# 外置应用

## Arthas
Arthas 是Alibaba开源的Java诊断工具。在线排查问题，无需重启；动态跟踪Java代码；实时监控JVM状态。

Arthas 支持JDK 6+，支持Linux/Mac/Windows，采用命令行交互模式，同时提供丰富的 Tab 自动补全功能，进一步方便进行问题的定位和诊断。

当你遇到以下类似问题而束手无策时，Arthas可以帮助你解决：
- 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
- 我改的代码为什么没有执行到？难道是我没 commit？分支搞错了？
- 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
- 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
- 是否有一个全局视角来查看系统的运行状况？
- 有什么办法可以监控到JVM的实时运行状态？
- 怎么快速定位应用的热点，生成火焰图？

官方文档演示Web Console。
- Github: https://github.com/alibaba/arthas
- 文档: https://arthas.aliyun.com/doc/

### Arthas Tunnel
通过Arthas Tunnel Server/Client 来远程管理/连接多个Agent。

比如，项目部署Java进程一般是在不同的机器启动的，想要使用 Arthas 去诊断会比较麻烦，因为用户通常没有机器的权限，即使登陆机器也分不清是哪个Java进程。

在这种情况下，可以使用 Arthas Tunnel Server/Client。

1. springboot项目添加依赖
```xml
        <dependency>
            <groupId>com.taobao.arthas</groupId>
            <artifactId>arthas-spring-boot-starter</artifactId>
            <version>3.5.3</version>
        </dependency>
```
配置文件中添加:
```yml
# arthas client 配置
arthas.appName: ${spring.application.name}
arthas.agent-id: you_customize_agentId # 命名中不能有 -
arthas.tunnel-server: ws://127.0.0.1:7777/ws
arthas.telnetPort: -1
arthas.httpPort: -1
```
> 注意，agentId要保持唯一，否则会在tunnel server上冲突，不能正常工作。
2. 下载部署arthas tunnel server
   下载地址: https://github.com/alibaba/arthas/releases
   使用如下命令启动
```shell
java -Dserver.port=8887 -Xms=500m -Xmx=500m -jar arthas-tunnel-server-3.5.3-fatjar.jar 
```
> arthas agent 连接的端口是7777，是注册端口，远程服务器的 arthas 通过该端口注册到 tunnel-server。
> 默认情况下，arthas tunnel server的web端口是8080。

通过Spring Boot的Endpoint：http://127.0.0.1:8887/actuator/arthas ，可以查看到具体的连接信息,登陆用户名是 arthas，密码在 arthas tunnel server 的日志里可以找到，比如：
```java
2021-08-09 16:10:26.961  INFO 23160 --- [           main] .s.s.UserDetailsServiceAutoConfiguration :

Using generated security password: 636a9684-ef30-4c7f-b182-47e54b8a6e24

```
arthas tunnel server管理页面： http://localhost:8887/apps.html

启动之后，可以访问 http://127.0.0.1:8887/ ，再通过agentId连接到已注册的 arthas agent 上。
例如：
```
http://localhost:8887/?targetServer=127.0.0.1&agentId=arthas_agent_user
```


## Sentinel 控制台
Sentinel 控制台包含如下功能:
- 查看机器列表以及健康情况：收集 Sentinel 客户端发送的心跳包，用于判断机器是否在线。
- 监控 (单机和集群聚合)：通过 Sentinel 客户端暴露的监控 API，定期拉取并且聚合应用监控信息，最终可以实现秒级的实时监控。
- 规则管理和推送：统一管理推送规则。
- 鉴权：生产环境中鉴权非常重要。这里每个开发者需要根据自己的实际情况进行定制。

下载地址： https://handson.oss-cn-shanghai.aliyuncs.com/sentinel-dashboard-1.8.0.jar

使用如下命令启动控制台：

```shell
java -Dserver.port=8888 -Xms=500m -Xmx=500m -jar  sentinel-dashboard-1.8.0.jar  
```

> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。

访问地址： http://127.0.0.1:8888/#/dashboard

默认账号密码： sentinel / sentinel

# 访问地址

Nacos

- http://localhost:8848/nacos/index.html

Arthas tunnel server

- http://localhost:8887/apps.html

Sentinel

- http://127.0.0.1:8888/#/dashboard


