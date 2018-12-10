/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.datastructures;

/**
 *
 * @author Micha
 */
public class Transaction {
    private String give;
    private Integer amount;
    private String receive;
    
    public Transaction(){
    }

    public Transaction(String give, Integer amount, String receive) {
        this.give = give;
        this.amount = amount;
        this.receive = receive;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    @Override
    public String toString() {
        return give + "-" + amount + ">" + receive;
    }
}

