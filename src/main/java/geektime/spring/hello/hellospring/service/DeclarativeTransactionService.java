package geektime.spring.hello.hellospring.service;

import geektime.spring.hello.hellospring.exception.RollbackException;

public interface DeclarativeTransactionService {

    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;

    void declarativeTransaction();
}
