/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Micha
 */
public class Block {
    //private int nonce = 0;
    //private Stack<Transaction> transaction = new Stack<>();
    //private String previousHash = null;
    //private String thisBlocksHash = null;
    private int previousHash; 
    Transaction transactions; 
    
    private int blockHash;

    public Block(int previousHash, Transaction transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;
        
        Object[] contents = {transactions.hashCode(), previousHash};
        this.blockHash = Arrays.hashCode(contents);
    }

    public int getPreviousHash() {
        return previousHash;
    }

    public Transaction getTransactions() {
        return transactions;
    }

    public int getBlockHash() {
        return blockHash;
    }
    
    
}
