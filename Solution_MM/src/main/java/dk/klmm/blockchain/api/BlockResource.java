/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.klmm.blockchain.classUtilities.Broadcaster;
import dk.klmm.blockchain.entities.Block;
import dk.klmm.blockchain.entities.Blockchain;
import dk.klmm.blockchain.entities.Peers;
import dk.klmm.blockchain.entities.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Micha
 */
@RestController
@RequestMapping("blocks")
@Produces(MediaType.APPLICATION_JSON)
public class BlockResource {

    Gson gson = new Gson();

    //When behind, catchup to date
    @RequestMapping(value = "catchup", method = RequestMethod.GET)
    public String catchup() throws IOException {
        Broadcaster.pingFriends(10);                   // Denne metode skal kaldes på init under normale omstændigheder. 
        ArrayList<String> urls = Peers.getPeers();
        ArrayList<ArrayList<Block>> chains = new ArrayList<>();
        for (String url : urls) {
            String uri = "http://" + url + "/blocks/getchain";
            RestTemplate restTemplate = new RestTemplate();
            JsonArray result = restTemplate.getForObject(uri, JsonArray.class);
            System.out.println("Adding chain to my List");
            chains.add(jsonToBlock(result));
        }
        return "Det virkede sådan da" + chains;
    }

    //Post
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public void submitBlock(@RequestBody Map<String, Object> payload) {
        Transaction t = new Transaction((String) payload.get("sender"), (Integer) payload.get("amount"), (String) payload.get("receiver"));
        Block prev = Blockchain.getLatestBlock();
        Block newB = new Block(prev.getPreviousHash(), t);
        Blockchain.addBlock(newB);
    }

    @RequestMapping("getchain")
    public String getChain() {
        Gson gson = new Gson();
        String json = gson.toJson(Blockchain.getMyChain());
        return json;
    }

    //Get
    @RequestMapping("latest")
    public String receiveLatest() {
        Gson gson = new Gson();
        Block b = Blockchain.getLatestBlock();
        String json = gson.toJson(b);
        return json;
    }

    private ArrayList<Block> jsonToBlock(JsonArray chain) {

        ArrayList<Block> tempChain = new ArrayList<>();

        JsonObject tmp;
        for (int i = 0; i < chain.size(); i++) {
            tmp = (JsonObject) chain.get(i);
            Block b = gson.fromJson(tmp, Block.class);
            tempChain.add(b);
        }
        return tempChain;
    }
}
