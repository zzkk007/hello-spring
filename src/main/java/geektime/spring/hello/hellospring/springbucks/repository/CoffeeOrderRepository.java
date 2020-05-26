package geektime.spring.hello.hellospring.springbucks.repository;

import geektime.spring.hello.hellospring.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
