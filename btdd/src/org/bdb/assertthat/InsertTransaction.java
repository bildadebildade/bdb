package org.bdb.assertthat;

public class InsertTransaction implements Transaction {
    private int amount;

    public InsertTransaction(int amount) {
        this.amount = amount;
    }

    public int getValue() {
        return amount;
    }
}
