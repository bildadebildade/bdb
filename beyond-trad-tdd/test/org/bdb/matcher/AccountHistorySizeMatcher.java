package org.bdb.matcher;

import org.bdb.domain.Account;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Customer matcher for number of transactions in history of an Account.
 */
public class AccountHistorySizeMatcher extends TypeSafeMatcher<Account> {
    private int expectedSize;

    @Factory
    public static Matcher<Account> hasHistorySize(int expectedSize) {
        return new AccountHistorySizeMatcher(expectedSize);
    }

    public AccountHistorySizeMatcher(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    public boolean matchesSafely(Account account) {
        return account.historySize() == expectedSize;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("number of transactions to be <" + expectedSize + ">");
    }

}