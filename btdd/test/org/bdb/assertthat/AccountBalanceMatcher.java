package org.bdb.assertthat;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AccountBalanceMatcher extends TypeSafeMatcher<Account> {
    private int expectedBalance;

    public AccountBalanceMatcher(int expectedBalance) {
        this.expectedBalance = expectedBalance;
    }

    @Override
    protected boolean matchesSafely(Account account) {
        return account.balance() == expectedBalance;
    }

    public void describeTo(Description description) {
        description.appendText("account balance to be <" + expectedBalance + ">");
    }

    @Factory
    public static Matcher<Account> hasBalance(int expectedBalance) {
        return new AccountBalanceMatcher(expectedBalance);
    }
}
