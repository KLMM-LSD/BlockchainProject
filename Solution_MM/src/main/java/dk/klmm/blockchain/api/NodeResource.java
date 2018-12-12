/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Micha
 */
@RestController
@RequestMapping("node")
@Produces(MediaType.APPLICATION_JSON)
public class NodeResource {
    
    @Autowired
    public NodeResource(){
    }
    
    @RequestMapping("/Hello")
    public String echo(){
        System.out.println("Hello from node");
        return "test";
    }
    
    
    
}
