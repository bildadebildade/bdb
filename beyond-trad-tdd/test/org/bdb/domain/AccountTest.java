package org.bdb.domain;

import static org.junit.Assert.*;

import org.bdb.domain.Account;
import org.bdb.domain.Transaction;
import org.bdb.domain.TransactionType;
import org.junit.Before;
import org.junit.Test;

/**
 * Traditional unit test class developed in a manner typically employed by 
 * good Java developers in 2010. It has some decent names, short test cases,
 * and has been written using fairly strict TDD. All tests are in working order.
 * <p></p>
 * However, there are problems...
 * <ol>
 * <li>The test case mixes tests of accounts in different state, hence the need to setup
 * test data in each test case.</li>
 * <li>The building of test data is clumsy. It is difficult to determine what is important 
 * and what is not.</li>
 * <li>The assertions are not as readable as they could be.</li>
 * </ol> 
 */
public class AccountTest {

    private static final String ACCOUNT_NAME = "Joakim's check account";

    private Account account;
    private Transaction deposit100 = new Transaction(1, TransactionType.DEPOSIT, 100.00);
    private Transaction withdraw50 = new Transaction(2, TransactionType.WITHDRAWAL, 50.00 );

    @Before
    public void setupEmptyAccount() {
        account = new Account(ACCOUNT_NAME);        
    }
    
    @Test
    public void testAddTransaction() throws Exception {
        account.addTransaction(deposit100);
        assertEquals("number of transactions:", 1, account.historySize());
    }
    
    @Test
    public void testCountTransactions() throws Exception {
        account.addTransaction(deposit100);
        account.addTransaction(withdraw50);
        assertEquals("number of transactions:", 2, account.historySize());
    }
    
    @Test
    public void testCalculateBalance() throws Exception {
        account.addTransaction(deposit100);
        account.addTransaction(deposit100);
        account.addTransaction(withdraw50);
        assertEquals(Double.valueOf(100 + 100 - 50), account.balance(), 0.001);
    }
    
    @Test
    public void testRejectInvalidTransaction() throws Exception {
        try {
            account.addTransaction(new Transaction(1, null, 0.0));
            fail("Invalid transaction added");
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            assertTrue(errorMessage.contains(ACCOUNT_NAME));
            assertTrue(errorMessage.contains("invalid transaction"));
        }
    }
}
