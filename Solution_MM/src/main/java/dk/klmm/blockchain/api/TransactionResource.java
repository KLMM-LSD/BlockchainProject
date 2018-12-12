/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mart_
 */
@RestController
@RequestMapping("")
public class TransactionResource {

    //Gets a list of known nodes.
    @RequestMapping("/new")
    public String newTrans() {
        System.out.println("Oprette new ");
        return "Oprette new ";
    }

}
