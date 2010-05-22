package org.bdb.builder;

import static org.bdb.builder.TransactionBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.bdb.domain.Account;
import org.bdb.domain.Transaction;
import org.bdb.domain.TransactionType;

/**
 * Test data builder of account domain objects.
 */
public class AccountBuilder {

    public static final String ACCOUNT_NAME = "Joakim's checkings account";

    private String name = ACCOUNT_NAME;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    /**
     * Factory method saying what is built.
     * 
     * @return a new builder
     */
    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    /**
     * Used to control the transactions in the account.
     * 
     * @param transaction transaction to add to the account
     * @return this
     */
    public AccountBuilder withTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        return this;
    }

    /**
     * Convenience method to add deposit to the account being built.
     * 
     * @param amount amount of the deposit
     * @return this
     */
    public AccountBuilder withDeposit(double amount) {
        transactions.add(aTransaction()
                .ofType(TransactionType.DEPOSIT)
                .withAmount(amount)
                .build());        
        return this;
    }
    
    /**
     * Convenience method to add withdrawal to the account being built.
     * 
     * @param amount amount of the withdraw
     * @return this
     */
    public AccountBuilder withWithdrawal(double amount) {
        transactions.add(aTransaction()
                .ofType(TransactionType.WITHDRAWAL)
                .withAmount(amount)
                .build());        
        return this;
    }
    
    /**
     * Finally, builds the Account using given specifications.
     * If no specification is given for a constructor argument, sensible defaults are used.
     * 
     * @return an initialised Account domain object
     */
    public Account build() {
        final Account account = new Account(name);
        for (Transaction transaction : transactions) {
            account.addTransaction(transaction);
        }
        return account;
    }

}
