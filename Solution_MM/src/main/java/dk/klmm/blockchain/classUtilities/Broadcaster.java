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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Micha
 */
public class Broadcaster {
    
    private ApiTimeoutConfig conf = new ApiTimeoutConfig();
    
    public void broadcastPingFriends(int timeout){
        // TODO
    }
    
    public void broadcastTransaction(Transaction t, int timeout){
        //Times out response if something happens
        RestTemplate restTemplate = conf.getApiRestTemplateTimeout(timeout);
    }
    
    public void broadcastNewBlock(Block b, int timeout){
        // TODO
    }
    
    public void broadcastNewBlockchain(Blockchain bc, int timeout){
        // TODO
    }
}
