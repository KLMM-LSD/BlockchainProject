/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.classUtilities;

import dk.klmm.blockchain.api.ApiTimeoutConfig;
import dk.klmm.blockchain.entities.Block;
import dk.klmm.blockchain.entities.Blockchain;
import dk.klmm.blockchain.entities.Peers;
import dk.klmm.blockchain.entities.Transaction;
import dk.klmm.blockchain.registry.RegisterPeers;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author Micha
 */
public class Broadcaster {

    private final ApiTimeoutConfig conf = new ApiTimeoutConfig();
    private final List<String> peers = RegisterPeers.getPeerRegistry();
    private static int[] ports = {8080, 8081, 8082, 8083, 8084, 8085};

    public static void pingFriends(int timeout) throws IOException {
        for (int port : ports) {
            if (pingFriend("localhost", port, timeout)) {
                Peers.addPeer("localhost:" + port);
            }
        }
    }

    public void broadcastTransaction(Transaction t, int timeout) {
        //Times out response if something happens
        RestTemplate restTemplate = conf.getApiRestTemplateTimeout(timeout);

        HttpEntity<Transaction> request = new HttpEntity<>(t);

        for (String URLPeer : peers) {
            try {
                restTemplate.patchForObject(URLPeer + "transaction/receive", request, Boolean.class);
            } catch (RestClientException ex) {
                System.out.println(ex + "broadcastTransaction failed");
            }
        }
        ManageBlocks.addTransaction(t);
    }

    public void broadcastNewBlock(Block b, int timeout) {
        // TODO
    }

    public void broadcastNewBlockchain(Blockchain bc, int timeout) {
        // TODO
    }

    private static boolean pingFriend(String host, int port, int timeout) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException ex) {
            System.out.println("Fejlen: " + ex.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        pingFriends(10);
    }

}
