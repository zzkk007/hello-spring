package geektime.spring.hello.hellospring.service.Impl;

import geektime.spring.hello.hellospring.service.DruidFunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
@Slf4j
public class DruidFunctionServiceImpl implements DruidFunctionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DruidFunctionService druidFunctionService;

    @Override
    @Transactional
    public void selectForUpdate() {

        jdbcTemplate.queryForObject("select id from foo where id = 1 for update", Long.class);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDruidSlowLog() {
        log.info(dataSource.toString());
        new Thread(() -> druidFunctionService.selectForUpdate()).start();
        new Thread(() -> druidFunctionService.selectForUpdate()).start();

    }
}
