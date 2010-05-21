package org.bdb.assertthat;

import java.util.ArrayList;
import java.util.List;

public class Account {

    List<Transaction> history = new ArrayList<Transaction>();

    public void add(Transaction transaction) {
        history.add(transaction);
    }

    public int balance() {
        int balance = 0;
        for (Transaction transaction : history) {
            balance = balance+transaction.getValue();
        }
        return balance;
    }

    @Override
    public String toString() {
        return String.valueOf(balance());
    }
}
