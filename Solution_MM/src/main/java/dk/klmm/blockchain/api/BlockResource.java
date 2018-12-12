/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Micha
 */
@RestController
@RequestMapping("blocks")
@Produces(MediaType.APPLICATION_JSON)
public class BlockResource {
    
    //Get
    @GetMapping("latest")
    public String getLatest(){
        String latest = "this is latest: ";
        return latest;
    }
    
    //Post
    @PostMapping("catchup")
    public String getCatchup(){
        String latest = "this is catchup: ";
        return latest;
    }
    
    //Post
    @PostMapping("submit")
    public String submitBlock(){
        String latest = "this is submit: ";
        return latest;
    }
    
    //Post
    @PostMapping("latest")
    public String receiveMicha(){
        String latest = "this is latest: ";
        return latest;
    }
    
}
