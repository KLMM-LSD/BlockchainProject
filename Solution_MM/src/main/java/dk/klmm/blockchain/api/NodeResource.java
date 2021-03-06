/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import dk.klmm.blockchain.entities.Peers;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Micha
 */
@RestController
@RequestMapping("node")
@Produces(MediaType.APPLICATION_JSON)
public class NodeResource {
    
    @Autowired
    public NodeResource() {
    }

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String echo() {
        return "Herro I am hero";
    }

    @RequestMapping(path = "/greet", method = RequestMethod.POST)
    public String greet() {
        try {
            String uri = "http://localhost:8080/node/hello";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
           // ResponseEntity<String> response = restTemplate.getForEntity(uri + "/Hello", String.class);
            return result;
        } catch (RestClientException ex) {
            return ex.getMessage();
        }
    }

    //Gets a list of known nodes.
    @RequestMapping("/known")
    public List<String> getNodes() {
        return Peers.listOfPeers;
    }

    @RequestMapping(value="register/{uri}", method = RequestMethod.GET)
    public void registering(@PathVariable String uri) {
        System.out.println("Adding Uri: " + uri);
        Peers.addPeer(uri); 
    }

}
