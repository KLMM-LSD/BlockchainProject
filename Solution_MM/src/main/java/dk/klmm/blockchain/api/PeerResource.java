/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Micha
 */
@RestController
@RequestMapping("peer")
@Produces(MediaType.APPLICATION_JSON)
public class PeerResource {
    
    @GetMapping
    public String receivePeer(){
        String peer = "Peer received";
        return peer;
    }
}
