package org.bdb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A bank account that has a name and contains a list of ordered transactions, 
 * the history, on the account.
 */
public class Account {

    private String name;
    private List<Transaction> history;

    public Account(String name) {
        this.name = name;
        this.history = new ArrayList<Transaction>();
    }

    public int historySize() {
        return history.size();
    }

    public void addTransaction(Transaction transaction) {
        if (!transaction.isValid()) {
            throw new IllegalArgumentException("Account " + name + ": Tried to add invalid transaction");
        }
        
        history.add(transaction);
    }

    public double balance() {
        double balance = 0;
        for (Transaction transaction : history) {
            // Note: If statement implies we need different transaction classes
            if (transaction.isDeposit()) {
                balance += transaction.getAmount();
            } else if (transaction.isWithdrawal()) {
                balance -= transaction.getAmount();
            } else {
                // Note: Ignored for now, some kind of logging suitable here at least
            }
        }

        return balance;
    }

    @Override
    public String toString() {
        return "{" + "Balance: " + String.valueOf(balance()) + ", " + "#Transactions: " + historySize() + "}";
    }
}
