package geektime.spring.hello.hellospring.service.Impl;

import geektime.spring.hello.hellospring.service.ProgrammaticTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
public class ProgrammaticTransactionServiceImpl implements ProgrammaticTransactionService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void programmaticTransaction(){
        log.info("COUNT BEFORE TRANSACTION:[{}]", getCount());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (6, 'cc')");
                log.info("COUNT INT TRANSACTION:[{}]", getCount());
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("COUNT AFTER TRANSACTION :[{}]", getCount());

    }

    private long getCount(){
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM  FOO").get(0).get("CNT");
    }
}
