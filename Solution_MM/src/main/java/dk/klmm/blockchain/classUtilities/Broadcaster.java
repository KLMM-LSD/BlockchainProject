/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.classUtilities;

import dk.klmm.blockchain.api.ApiTimeoutConfig;
import dk.klmm.blockchain.entities.Block;
import dk.klmm.blockchain.entities.Blockchain;
import dk.klmm.blockchain.entities.Transaction;
import dk.klmm.blockchain.registry.RegisterPeers;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Micha
 */
public class Broadcaster {
    
    private final ApiTimeoutConfig conf = new ApiTimeoutConfig();
    private final List<String> peers = RegisterPeers.getPeerRegistry();
    
    public void broadcastPingFriends(int timeout){
        // TODO
    }
    
    public void broadcastTransaction(Transaction t, int timeout){
        //Times out response if something happens
        RestTemplate restTemplate = conf.getApiRestTemplateTimeout(timeout);
        
        HttpEntity<Transaction> request = new HttpEntity<>(t);
        
        for(String URLPeer : peers){
            try{
                restTemplate.patchForObject(URLPeer + "transaction/receive", request, Boolean.class);
            } catch (RestClientException ex){
                System.out.println(ex + "broadcastTransaction failed");
            }
        }
        ManageBlocks.addTransaction(t);
    }
    
    public void broadcastNewBlock(Block b, int timeout){
        // TODO
    }
    
    public void broadcastNewBlockchain(Blockchain bc, int timeout){
        // TODO
    }
}
