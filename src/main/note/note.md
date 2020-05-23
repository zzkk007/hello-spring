第一章：

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
                                     