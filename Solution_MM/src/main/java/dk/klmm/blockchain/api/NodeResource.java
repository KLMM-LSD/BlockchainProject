/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Micha
 */
@RestController
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