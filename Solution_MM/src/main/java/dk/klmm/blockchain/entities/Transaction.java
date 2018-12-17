/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

/**
 *
 * @author Micha
 */
public class Transaction {
    private String sender;
    private Integer amount;
    private String receiver;
    
    public Transaction(){
    }

    public Transaction(String sender, Integer amount, String receiver) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String givsender) {
        this.sender = sender;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return sender + "-" + amount + "->" + receiver;
    }
}

