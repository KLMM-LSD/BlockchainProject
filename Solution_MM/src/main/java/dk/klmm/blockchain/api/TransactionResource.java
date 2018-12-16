/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import dk.klmm.blockchain.classUtilities.Broadcaster;
import dk.klmm.blockchain.classUtilities.ManageBlocks;
import dk.klmm.blockchain.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mart_
 */
@RestController
@RequestMapping("transaction")
public class TransactionResource {
    
    Broadcaster broadcaster = new Broadcaster();
    
    @Autowired
    public TransactionResource(){
    }

    //Gets a list of known nodes.
    @RequestMapping(path = "/retrieve", method = RequestMethod.POST)
    public Boolean getTransaction(@RequestBody Transaction t) {
        // Process, Mine and then send to peers
        return true;
    }
    
    @RequestMapping(path = "/broadcast", method = RequestMethod.POST)
    public Boolean broadcastTransaction(@RequestBody Transaction t){
        broadcaster.broadcastTransaction(t, 500);
        ManageBlocks.addTransaction(t);
        return true;
    }

}
