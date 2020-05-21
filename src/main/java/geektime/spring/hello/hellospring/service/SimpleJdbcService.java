package geektime.spring.hello.hellospring.service;

import java.sql.SQLException;

public interface SimpleJdbcService {

    void showConnection() throws SQLException;

    void showData();
}
