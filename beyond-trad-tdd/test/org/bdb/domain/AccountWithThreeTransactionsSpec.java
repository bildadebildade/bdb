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
 * Developer specification of account bevaviour when there are three transaction in it.
 * <p></p>
 * This spec is better on at least three accounts:
 * <ol>
 * <li>It is focused on the account in a specific state (non-empty, three transactions).
 * This makes it easier to have better names for test cases describing behaviour.</li>
 * <li>It employs data builders to better express needed test data.</li>
 * <li>It employs customer matchers with <pre>assertThat()</pre>
 * for the domain to better express assertions.</li>
 * </ol>
 */
public class AccountWithThreeTransactionsSpec {

    private static final double DEPOSIT_AMOUNT = 100.0;
    private static final double WITHDRAW_AMOUNT = 50.0;

    private Account accountWithSomeTransactions;

    @Before
    public void setupEmptyAccount() {
        accountWithSomeTransactions = anAccount()
                .withDeposit(DEPOSIT_AMOUNT)
                .withWithdrawal(WITHDRAW_AMOUNT)
                .withDeposit(DEPOSIT_AMOUNT)
                .build();        
    }
    
    @Test
    public void shouldHaveThreeTransaction() throws Exception {
        assertThat(accountWithSomeTransactions, hasHistorySize(3));
    }

    @Test
    public void shouldAcceptAnotherTransaction() throws Exception {
        accountWithSomeTransactions.addTransaction(aTransaction().build());
        assertThat(accountWithSomeTransactions, hasHistorySize(4));
    }

    @Test
    public void shouldHaveBalanceEqualToSumOfAllTransactions() throws Exception {
        Double expectedBalance = Double.valueOf(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT + DEPOSIT_AMOUNT);
        assertThat(accountWithSomeTransactions, hasBalance(expectedBalance));
    }
}
