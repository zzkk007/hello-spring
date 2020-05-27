package geektime.spring.hello.hellospring;

import geektime.spring.hello.hellospring.po.FooDao;
import geektime.spring.hello.hellospring.service.DeclarativeTransactionService;
import geektime.spring.hello.hellospring.service.DruidFunctionService;
import geektime.spring.hello.hellospring.service.ProgrammaticTransactionService;
import geektime.spring.hello.hellospring.service.SimpleJdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class HelloSpringApplication implements CommandLineRunner {

    @Autowired
    private FooDao fooDao;

    @Autowired
    private SimpleJdbcService jdbcService;

    @Autowired
    private ProgrammaticTransactionService programmaticTransactionService;

    @Autowired
    private DeclarativeTransactionService declarativeTransactionService;

    @Autowired
    private DruidFunctionService druidFunctionService;

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //fooDao.insertData();                                       // jdbc简单操作，插入数据
        //fooDao.listData();                                         // jdbc简单操作，列表展示
        //jdbcService.showConnection();                              // jdbc简单操作，显示连接数
        //jdbcService.showData();                                    // jdbc简单操作，显示数据

        //programmaticTransactionService.programmaticTransaction();  // 编程式事务
        //declarativeTransactionService.declarativeTransaction();      // 声明式事务
        //declarativeTransactionService.propagation();                // 事务传播特性

        druidFunctionService.showDruidSlowLog();
    }


}
