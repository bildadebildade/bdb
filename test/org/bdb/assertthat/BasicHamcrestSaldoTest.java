package org.bdb.assertthat;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class BasicHamcrestSaldoTest {

    private Account account;

    @Before
    public void createAccount() {
        account = new Account();
    }

    @Test
    public void initialBalance() {
        assertThat(account.balance(), is(equalTo(0)));
    }

    @Test
    public void shouldCountBalanceOfTransactions() {
        account.add(new InsertTransaction(100));
        account.add(new InsertTransaction(200));
        account.add(new WithdrawalTransaction(50));
        assertThat(account.balance(), equalTo(250));
    }
}