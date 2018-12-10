/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.ArrayList;

public class Main {

    private static ArrayList<Block> blocks = new ArrayList<>();

    public static void main(String[] args) {

        Transaction gensisTrans = new Transaction("Michael", 100, "Michaels Mor");
        Block gensisBlock = new Block(0, gensisTrans);

        Transaction trans2 = new Transaction("Morten", 150, "Bob");
        Block block2 = new Block(gensisBlock.getBlockHash(), trans2);

        Transaction trans3 = new Transaction("Keksimus", 100, "Maxiumus");
        Block block3 = new Block(block2.getBlockHash(), trans3);

        System.out.println("Block: " + gensisBlock.getBlockHash());
        System.out.println("Block2: " + block2.getBlockHash());
        System.out.println("BLock3: " + block3.getBlockHash());
    }

}
