package org.bdb.domain;

import static org.bdb.builder.AccountBuilder.*;
import static org.bdb.builder.TransactionBuilder.*;
import static org.bdb.matcher.AccountBalanceMatcher.*;
import static org.bdb.matcher.AccountHistorySizeMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.bdb.builder.AccountBuilder;
import org.bdb.domain.Account;
import org.bdb.domain.Transaction;
import org.junit.Before;
import org.junit.Test;

/**
 * Developer specification of empty account bevaviour.
 * <p></p>
 * This spec is better on at least three accounts:
 * <ol>
 * <li>It is focused on the account in a specific state (empty, no transactions).
 * This makes it easier to have better names for test cases describing behaviour.</li>
 * <li>It employs data builders to better express needed test data.</li>
 * <li>It employs customer matchers with <pre>assertThat()</pre>
 * for the domain to better express assertions.</li>
 * </ol>
 */
public class EmptyAccountSpec {

    private Account emptyAccount;
    private final Transaction invalidTransaction = aTransaction().ofType(null).build();

    @Before
    public void setupEmptyAccount() {
        emptyAccount = anAccount().build();        
    }
    
    @Test
    public void shouldHaveZeroTransactions() throws Exception {
        assertThat(emptyAccount, hasHistorySize(0));
    }
    
    @Test
    public void shouldAcceptFirstTransaction() throws Exception {
        emptyAccount.addTransaction(aTransaction().build());
        assertThat(emptyAccount, hasHistorySize(1));
    }

    @Test
    public void shouldHaveZeroBalance() throws Exception {
        assertThat(emptyAccount, hasBalance(0.0));
    }

    @Test
    public void shouldRejectUnknownTransactionType() throws Exception {
        try {
            emptyAccount.addTransaction(invalidTransaction);
            fail("Invalid transaction added");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString(AccountBuilder.ACCOUNT_NAME));
            assertThat(e.getMessage(), containsString("invalid transaction"));
        }
    }
}
