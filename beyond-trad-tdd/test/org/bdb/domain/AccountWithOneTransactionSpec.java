package org.bdb.domain;

import static org.bdb.builder.AccountBuilder.*;
import static org.bdb.builder.TransactionBuilder.*;
import static org.bdb.matcher.AccountBalanceMatcher.*;
import static org.bdb.matcher.AccountHistorySizeMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.bdb.domain.Account;
import org.junit.Before;
import org.junit.Test;

/**
 * Developer specification of account bevaviour when there is one transaction in it.
 * <p></p>
 * This spec is better on three accounts:
 * <ol>
 * <li>It is focused on the account in a specific state (non-empty, one transaction).
 * This makes it easier to have better names for test cases describing behaviour.</li>
 * <li>It employs data builders to better express needed test data.</li>
 * <li>It employs customer matchers with <pre>assertThat()</pre>
 * for the domain to better express assertions.</li>
 * </ol>
 */
public class AccountWithOneTransactionSpec {

    private static final double TRANSACTION_AMOUNT = 100.0;
    private Account accountWithOneTransaction;

    @Before
    public void setupEmptyAccount() {
        accountWithOneTransaction = anAccount().withDeposit(TRANSACTION_AMOUNT).build();        
    }
    
    @Test
    public void shouldHaveOneTransaction() throws Exception {
        assertThat(accountWithOneTransaction, hasHistorySize(1));
    }

    @Test
    public void shouldAcceptAnotherTransaction() throws Exception {
        accountWithOneTransaction.addTransaction(aTransaction().build());
        assertThat(accountWithOneTransaction, hasHistorySize(2));
    }

    @Test
    public void shouldHaveBalanceFromFirstTransaction() throws Exception {
        assertThat(accountWithOneTransaction, hasBalance(TRANSACTION_AMOUNT));
    }
}
