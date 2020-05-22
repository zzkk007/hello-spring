package geektime.spring.hello.hellospring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
        log.info("=============================================");
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
        log.info("=============================================");

    }
}
