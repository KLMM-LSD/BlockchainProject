/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockchainTester {

    private static ArrayList<Block> blocks = new ArrayList<>();

//    public static void main(String[] args) {
//        ArrayList<Block> chain = new ArrayList<>();
//
//        Transaction gensisTrans = new Transaction("Michael", 100, "Morten");
//        Transaction trans2 = new Transaction("Morten", 150, "Bob");
//        Transaction trans3 = new Transaction("Keksimus", 100, "Maxiumus");
//
//        Block genesisBlock = new Block(0, gensisTrans);
//        Block block1 = new Block(genesisBlock.getBlockHash(), trans2);
//        Block block2 = new Block(block1.getBlockHash(), trans3);
//
//        chain.add(genesisBlock);
//        chain.add(block1);
//        chain.add(block2);
//
//        ArrayList<Block> chain2 = chain;
//        ArrayList<Block> chain3 = chain;
//        ArrayList<Block> chain4 = chain;
//        chain4.remove(2);
//        
//        ArrayList<ArrayList<Block>> al = new ArrayList<>();
//        al.add(chain4);
//        al.add(chain);
//        al.add(chain2);
//        al.add(chain3);
//        
//        System.out.println("Did I reach consensus?: " + Blockchain.consensus(al).equals(chain));
//        System.out.println("My Hash: " + chain.get(0).getBlockHash());
//        System.out.println("Consensus: " + Blockchain.consensus(al).get(0).getBlockHash());

//          chain.add(new Block(gensisBlock.getBlockHash(), trans2));
//       
//        System.out.println("Block: " + gensisBlock.getBlockHash());
//        System.out.println("Block2: " + block2.getBlockHash());
//        System.out.println("BLock3: " + block3.getBlockHash());
//    }

}
