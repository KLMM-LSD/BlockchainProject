/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.entities;

import dk.klmm.blockchain.classUtilities.Broadcaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mart_
 */
public class Peers {

    public static List<String> listOfPeers = new ArrayList<>();

    public static void addPeer(String uri) {
        if (!listOfPeers.contains(uri)) {
            listOfPeers.add(uri);
        }
    }

    public static void removePeer(String uri) {
        listOfPeers.remove(uri);
    }

    public static List<String> getPeers() {
        return listOfPeers;
    }
}
