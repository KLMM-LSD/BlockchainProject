/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import java.util.ArrayList;

/**
 *
 * @author Mart_
 */
public class Peers {

    public static ArrayList<String> listOfPeers = new ArrayList<>();

    public static void addPeer(String uri) {
        if (!listOfPeers.contains(uri)) {
            listOfPeers.add(uri);
        }
    }

    public static void removePeer(String uri) {
        listOfPeers.remove(uri);
    }

    public static ArrayList<String> getPeers() {
        return listOfPeers;
    }
}
