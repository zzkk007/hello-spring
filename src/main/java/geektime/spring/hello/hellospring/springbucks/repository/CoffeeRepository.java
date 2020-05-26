package geektime.spring.hello.hellospring.springbucks.repository;

import geektime.spring.hello.hellospring.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
