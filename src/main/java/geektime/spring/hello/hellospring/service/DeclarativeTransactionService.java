package geektime.spring.hello.hellospring.service;

import geektime.spring.hello.hellospring.exception.RollbackException;

public interface DeclarativeTransactionService {

    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
    void declarativeTransaction();

    //验证事务的传播特性
    void propagationInsertThenRollback() throws RollbackException;
    void propagationInvokeInsertThenRollback() throws RollbackException;
    void propagation();

}
