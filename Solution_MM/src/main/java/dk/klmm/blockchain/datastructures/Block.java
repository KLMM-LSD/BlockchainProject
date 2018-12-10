/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.datastructures;

import java.util.Stack;

/**
 *
 * @author Micha
 */
public class Block {
    private int nonce = 0;
    private Stack<Transaction> transaction = new Stack<>();
    private String previousHash = null;
    private String thisBlocksHash = null;
}
