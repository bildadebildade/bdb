package org.bdb.assertthat;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SaldoTest {
    private Account account;

    @Before
    public void createAccount() {
        account = new Account();
    }

    @Test
    public void initialBalance() {
        assertEquals(0, account.balance());
    }

    @Test
    public void shouldCountBalanceOfTransactions() {
        account.add(new InsertTransaction(100));
        account.add(new InsertTransaction(200));
        account.add(new WithdrawalTransaction(50));
        assertEquals(250, account.balance());
    }
}
