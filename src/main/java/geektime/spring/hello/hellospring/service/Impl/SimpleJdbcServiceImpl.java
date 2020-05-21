package geektime.spring.hello.hellospring.service.Impl;

import geektime.spring.hello.hellospring.service.SimpleJdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Service
public class SimpleJdbcServiceImpl implements SimpleJdbcService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }

    @Override
    public void showData(){
        jdbcTemplate.queryForList("SELECT *FROM FOO").forEach(row-> log.info(row.toString()));
    }
}
