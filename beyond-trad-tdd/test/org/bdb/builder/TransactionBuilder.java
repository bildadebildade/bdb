package org.bdb.builder;

import org.bdb.domain.Transaction;
import org.bdb.domain.TransactionType;

/**
 * Test data builder of account domain objects.
 */
public class TransactionBuilder {

    private int id = 1;
    private TransactionType transactionType = TransactionType.DEPOSIT;
    private double amount = 0.0;

    /**
     * Factory method saying what is built.
     * 
     * @return a new builder
     */
    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    /**
     * Used to control the transaction type.
     * 
     * @param transactionType type of transaction to build
     * @return this
     */
    public TransactionBuilder ofType(TransactionType transactionType) {
        this.transactionType  = transactionType;
        return this;
    }

    /**
     * Used to control the transaction amount.
     * 
     * @param transactionType amount of the transaction to build
     * @return this
     */
    public TransactionBuilder withAmount(double amount) {
        this.amount  = amount;
        return this;
    }
    
    /**
     * Finally, builds the Transaction using given specifications.
     * If no specification is given for a constructor argument, sensible defaults are used.
     * 
     * @return an initialised Transaction domain object
     */
    public Transaction build() {
        return new Transaction(id, transactionType, amount);
    }

}
