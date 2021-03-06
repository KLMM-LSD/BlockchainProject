/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mart_
 */
public class Blockchain {

    private static ArrayList<Block> myChain = new ArrayList<>();

    //Does the request fit the consensus
    public static ArrayList<Block> consensus(ArrayList<ArrayList<Block>> listOfChains) {
        HashMap<ArrayList<Block>, Integer> hm = new HashMap<>();
        ArrayList<Block> consensus = null;
        for (ArrayList<Block> chain : listOfChains) {
            if (hm.containsKey(chain)) {
                hm.put(chain, hm.get(chain) + 1);
            } else {
                hm.put(chain, 1); // Create Key,Value pair if they do not exsist. 
            }
        }
        Integer max = 0;
        for (ArrayList<Block> key : hm.keySet()) {
            if (hm.get(key) > max) {
                max = hm.get(key);
                consensus = key;
            }
        }
        return consensus;
    }

    public static boolean isChainValid(ArrayList<Block> chain) {

        if (chain.size() < 2) {
            return false;
        }
        for (int i = 1; i < chain.size(); i++) {
            String temp = chain.get(i - 1).getBlockHash();

            if (temp != chain.get(i).getPreviousHash()) {
                return false;
            }
        }
        return true;
    }

    public static void addBlock(Block b) {
        myChain.add(b);
    }

    public static ArrayList<Block> getMyChain() {
        return myChain;
    }

    public static Block getLatestBlock() {
        if (myChain.isEmpty()) {
            return new Block("0", null);
        }
        return myChain.get(myChain.size() - 1);
    }

    public static void setChain(ArrayList<Block> newChain) {
        System.out.println("My Chain has been updated \n From: " + myChain.toString());
        myChain = newChain;
        System.out.println("To: " + newChain.toString());
    }
}
