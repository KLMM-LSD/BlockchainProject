/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.registry;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Micha
 */
public class RegisterPeers {
    
    private static List<String> registry;
    
    private RegisterPeers(){
        registry = new ArrayList<>();
        registry.add("http://localhost:8081");
        registry.add("http://localhost:8082");
        registry.add("http://localhost:8083");
        registry.add("http://localhost:8084");
        registry.add("http://localhost:8085");
    }
    
    public static void addPeer(String peerAddress){
        registry.add(peerAddress);
    }
    
    public static List<String> getPeerRegistry(){
        return registry;
    }
}
