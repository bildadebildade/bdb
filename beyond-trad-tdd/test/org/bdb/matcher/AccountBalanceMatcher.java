package org.bdb.matcher;

import org.bdb.domain.Account;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Customer matcher for Account balance.
 */
public class AccountBalanceMatcher extends TypeSafeMatcher<Account> {
    private double expectedBalance;

    @Factory
    public static Matcher<Account> hasBalance(double expectedBalance) {
        return new AccountBalanceMatcher(expectedBalance);
    }

    public AccountBalanceMatcher(double expectedBalance) {
        this.expectedBalance = expectedBalance;
    }

    @Override
    public boolean matchesSafely(Account account) {
        return Math.abs(account.balance() - expectedBalance) < 0.001;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("account balance to be <" + expectedBalance + ">");
    }

}