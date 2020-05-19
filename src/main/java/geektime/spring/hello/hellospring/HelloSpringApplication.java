package geektime.spring.hello.hellospring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@Slf4j
public class HelloSpringApplication implements CommandLineRunner {

	private final DataSource dataSource;
	public HelloSpringApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private FooDao fooDao;

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello(){

		return "hello Spring";

	}

	@Bean
	@Autowired
	public SimpleJdbcInsert simpleJdbcInsert (JdbcTemplate jdbcTemplate){
		return new SimpleJdbcInsert(jdbcTemplate).withTableName("Foo").usingGeneratedKeyColumns("ID");

	}

	@Override
	public void run(String... args) throws Exception {

		fooDao.insertData();
		fooDao.listData();

		//log.info(dataSource.toString());
		//showConnection();
		//showData();
	}

	private void showConnection() throws SQLException {

		log.info(dataSource.toString());
		Connection conn = dataSource.getConnection();
		log.info(conn.toString());
		conn.close();
	}

	private void showData(){

		jdbcTemplate.queryForList("SELECT *FROM FOO").forEach(row-> log.info(row.toString()));
	}
}
