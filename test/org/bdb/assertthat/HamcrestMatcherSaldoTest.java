package org.bdb.assertthat;

import org.junit.Before;
import org.junit.Test;

import static org.bdb.assertthat.AccountBalanceMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatcherSaldoTest {

    private Account account;

    @Before
    public void createAccount() {
        account = new Account();
    }

    @Test
    public void initialBalance() {
        assertThat(account, hasBalance(0));
    }

    @Test
    public void shouldCountBalanceOfTransactions() {
        account.add(new InsertTransaction(100));
        account.add(new InsertTransaction(200));
        account.add(new WithdrawalTransaction(50));
        assertThat(account, hasBalance(250));
    }
}