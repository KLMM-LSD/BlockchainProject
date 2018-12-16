/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.klmm.blockchain.classUtilities;

import dk.klmm.blockchain.entities.Block;
import dk.klmm.blockchain.entities.Transaction;

/**
 *
 * @author Micha
 */
public class ManageBlocks {
    
    private ManageBlocks(){
    }
    
    public static Boolean chain(Block b){
        return true;
    }
    
    public static synchronized void addTransaction(Transaction t){
        // TODO
    }
    
    public static void generateNewBlock(){
        // TODO
    }
    
    public void createNewBlock(Transaction tran){
        // TODO
    }
}
