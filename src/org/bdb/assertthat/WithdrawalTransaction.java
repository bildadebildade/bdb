package org.bdb.assertthat;

public class WithdrawalTransaction implements Transaction {
    private int amount;

    public WithdrawalTransaction(int amount) {
        this.amount = amount;
    }

    public int getValue() {
        return -amount;
    }
}
