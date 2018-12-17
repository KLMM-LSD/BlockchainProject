/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.Date;

/**
 *
 * @author Micha
 */
public class Block {

    private int nonce = 0;
    private int diff = 0;

    private String previousHash;
    Transaction transactions;
    private long timeStamp;

    private String blockHash;

    public Block(String previousHash, Transaction transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;

        this.timeStamp = new Date().getTime();
        this.blockHash = calculateHash();
    }

//    private int calculateHash(Transaction transaction1, String prevHash) {
//        Object[] contents = {transaction1.hashCode(), prevHash};
//        return Arrays.hashCode(contents);
//    }
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + transactions
        );
        return calculatedhash;
    }

    public void mine(int previousHash) {
        String target = new String(new char[diff]).replace('\0', '0'); //Create a string with difficulty * "0" 
        while (!blockHash.substring(0, diff).equals(target)) {
            nonce++;
            blockHash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + blockHash);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Transaction getTransactions() {
        return transactions;
    }

    public String getBlockHash() {
        return blockHash;
    }

    @Override
    public String toString() {
        return "Block{" + "nonce=" + nonce + ", diff=" + diff + ", previousHash=" + previousHash + ", transactions=" + transactions + ", timeStamp=" + timeStamp + ", blockHash=" + blockHash + '}';
    }

}
