package org.bdb.domain;

/**
 * Transfer of money to or from a bank account.
 * The direction is indicated by the transaction type:
 * <ul>
 * <li>Deposit: Money is put into the account</li>
 * <li>Withdrawal: Money is taken out of the account</li>
 * </ul>
 */
public class Transaction {

    private int id;
    private TransactionType type;
    private double amount = 0.0;
    
    public Transaction(int id, TransactionType type, double amount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }

    public boolean isValid() {
        return getId() >= 0 && getType() != null;
    }

    public boolean isWithdrawal() {
        return getType() == TransactionType.WITHDRAWAL;
    }

    public boolean isDeposit() {
        return getType() == TransactionType.DEPOSIT;
    }

}
