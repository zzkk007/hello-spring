第一章：

第二章 spring JDBC

1、一些常用注解：
    
    Java Config 相关注解：
        @Configuration          配置类
        @ImportResource         引入配置文件
        @ComponentScan          告诉springboot去扫描那些bean
        @Bean                   
        @ConfigurationProperties
        
    定义相关注解：
        
        @Component @Repository @Service
        @Controller @RestController
        @RequesetMapping
        
    注入相关注解：
        
        @Autowired/@Qualifier 根据类型注入 @Resource 根据名称注入
        @Value            
        
2、Actuator 提供的一些好用的 Endpoint
    
    URL                  作用
    /actuator/health     健康检查
    /actuator/beans      查看容器中所有的 Bean
    /actuator/mapping    查看所有的Web的URL映射
    /actuator/env        查看环境信息
    
    默认：/actuator/health 和 actuator/info 可 Web 访问
    解禁所有的 EndPoint
        application.properties  / application.yml
        management.endpoints.web.exposure.include=*
        
        http://localhost:8080/actuator/env
        
3、数据库中间件：
    
    TDDL、Cobar、MyCAT、Sharding-Shpere 
    
4、Spring 事务的本质
    
    Spring 的声明式事务本质上是通过 AOP 来增强了类的功能
    Spring 的 AOP 本质上就是为类做了一个代理
        看似在调用自己写的类，实际用的是增强后的代理类。
        
    
    事务的传播特性：
        REQUIRES_NEW vs NESTED
        
        REQUIRES_NEW:始终启动一个新事务，两个事务没有关联
        NESTED: 在原事务内启动一个内嵌事务
            两个事务有关联
            外部事务回滚，内部事务也回滚
            内嵌事务回滚，不会响应外面的事务
            
5、 Druid 慢日志：
  
    #spring 慢日志
    spring.datasource.druid.filter.stat.log-slow-sql=true
    spring.datasource.druid.filter.stat.slow-sql-millis=100            
                                     
    没特殊情况，不要在⽣产环境打开监控的 Servlet
    • 没有连接泄露可能的情况下，不要开启 removeAbandoned
    • testXxx 的使⽤需要注意
    • 务必配置合理的超时时间                               
                                     
第三章    O/R Mapping 实践

1、认识 Spring Data JPA

    对象与关系的范式不匹配
                 Object          RMBS
    粒度           类              表
    继承           有              没有
    唯一性         a.equals(b)     主键 （不管是组合还是单一主键）
    关联           引用            外键
    数据访问        逐级访问        SQL数量要少
    
    Hibernate
    
        一款开源的对象关系映射（Object/ Relational Mapping）框架
        将开发者从数据持久化工作中解放出来
        屏蔽了底层数据库的各种细节
        
    Hibernate 发展历程
    
        • 2001年，Gavin King 发布第⼀个版本
        • 2003年，Hibernate 开发团队加⼊ JBoss
        • 2006年，Hibernate 3.2 成为 JPA 实现
    
    Java Persistence API
        
        JPA 为对象关系映射提供了一种基于 POJO 的持久化模型
        简化数据持久化代码的开发工作
        为 Java 社区屏蔽不同持久化 API 的差异。
            
    Spring Data
        
        在保留底层存储特性的同时，提供相对一致的、基于 Spring 的编程模型
        <dependency>
            <groupId>org.spingframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        <dependency>
        
        主要模块：
            Sping Data Commons
            Sping Data JDBC
            Sping Data JPA
            Sping Data Redis
            ....
            
    定义 JPA 实体对象：
        
        实体： 
              @Entity
              @MappedSuperClass        多个实体
              @Table(name)             实体对应的表
                     
        主键：
             
             @Id
                @GeneratedValue(Strategy, generator)      // 主键生成策略，生成器
                @SequenceGenerator(name, sequenceName)    // 使用什么序列
                   
        
            @Entity(Name = "Product)     
            public static class Product{
                
                @Id
                @GeneratedValue(
                    strategy = GenerationType.SEQUENCE,
                    generator = "sequence-generator"
                )
                @SequenceGenerator(
                    name = "sequence-generator"
                    sequenceName = "product_sequence"
                )
                pricate Long id;
                
                
                @Column(name = "product_name")
                private String name;    
            }                                           
            
        
        映射：
            
            @Column(name, nullable, length, insertable, updateable) 
            @JoinTable(name)、@JoinColumn(name)  //表关联使用
            
        关系：
            
            @OneToOne、@OneToMany、@ManyToOne、@ManyToMany
            @OrderBy                              
            
                    
"=================================================================="

1、Docker 常用命令：
    
    镜像相关：
        docker pull <image>       : docker pull mongo:latest
        docker search <image>       
    容器相关：
        docker run                : docker run -itd --name mongo -p 27017:27017 mongo --auth  
            -d 后台运行容器
            -e 设置环境变量
            --expose/-p, 宿主端口：容器接口
            --name, 指定容器名称
            --link, 链接不同容器
            -v 宿主目录：容器目录，挂载磁盘卷
            -p 27017:27017 ：映射容器服务的 27017 端口到宿主机的 27017 端口。外部可以直接通过 宿主机 ip:27017 访问到 mongo 的服务。
            --auth：需要密码才能访问容器服务。
        docker start/stop <容器名>
        docker ps <容器名>
        docker logs <容器名>
    
    国内 Docker 镜像配置:
        官⽅ Docker Hub 
        • https://hub.docker.com 
        官⽅镜像
        • 镜像 https://www.docker-cn.com/registry-mirror
        • 下载 https://www.docker-cn.com/get-docker 
        阿⾥云镜像
        • https://dev.aliyun.com
                           