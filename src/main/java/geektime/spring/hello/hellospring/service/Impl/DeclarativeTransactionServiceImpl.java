package geektime.spring.hello.hellospring.service.Impl;

import geektime.spring.hello.hellospring.exception.RollbackException;
import geektime.spring.hello.hellospring.service.DeclarativeTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DeclarativeTransactionServiceImpl implements DeclarativeTransactionService {

    /**
     *  Transaction:
     *      transactionManager --> DataSourceTransactionManager
     *      propagation        --> 传播特性
     *      isolation          --> 隔离特性
     *      timeout
     *      readOnly
     *      怎么判断回滚
     *  事务传播特性：
     *      所谓事务传播行为就是多个事务方法相互调用时，事务如何在这些方法间传播。
     *      Spring 支持 7 种事务传播行为（Transaction Propagation Behavior）:
     *      传播行为	描述
     *          PROPAGATION_REQUIRED	如果没有，就开启一个事务；如果有，就加入当前事务（方法B看到自己已经运行在 方法A的事务内部，就不再起新的事务，直接加入方法A）
     *          RROPAGATION_REQUIRES_NEW	如果没有，就开启一个事务；如果有，就将当前事务挂起。（方法A所在的事务就会挂起，方法B会起一个新的事务，等待方法B的事务完成以后，方法A才继续执行）
     *          PROPAGATION_NESTED	如果没有，就开启一个事务；如果有，就在当前事务中嵌套其他事务
     *          PROPAGATION_SUPPORTS	如果没有，就以非事务方式执行；如果有，就加入当前事务（方法B看到自己已经运行在 方法A的事务内部，就不再起新的事务，直接加入方法A）
     *          PROPAGATION_NOT_SUPPORTED	如果没有，就以非事务方式执行；如果有，就将当前事务挂起，（方法A所在的事务就会挂起，而方法B以非事务的状态运行完，再继续方法A的事务）
     *          PROPAGATION_NEVER	如果没有，就以非事务方式执行；如果有，就抛出异常。
     *          PROPAGATION_MANDATORY	如果没有，就抛出异常；如果有，就使用当前事务
     */

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord(){
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Override     // 不加事务，事务的执行链就断了
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    @Override
    public void declarativeTransaction() {

        this.insertRecord();
        log.info("AAA {}",
                jdbcTemplate
                        .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
        try {
            this.insertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate
                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }

        try {
            this.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate
                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }
    }
}
