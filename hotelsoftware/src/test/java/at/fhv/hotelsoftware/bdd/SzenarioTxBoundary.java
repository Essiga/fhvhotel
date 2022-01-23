package at.fhv.hotelsoftware.bdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class SzenarioTxBoundary {
    @Autowired
    private PlatformTransactionManager txManager;
    private TransactionStatus tx;

    // NOTE: intended to be called @Before to start a TX
    protected void beginTX(){
        this.tx = this.txManager.getTransaction(new DefaultTransactionDefinition());
    }
    // NOTE: intended to be called in an @After to roll back all changes made to the DB in the Scenario
    protected void rollbackTX(){
        this.txManager.rollback(this.tx);
    }

}
