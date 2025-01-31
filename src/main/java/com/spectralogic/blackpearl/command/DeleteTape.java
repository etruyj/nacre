//===================================================================
// DeleteTape.java
//      Description:
//          This class handles the functionality associated with
//          deleting a tape from the database.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.command;

import com.spectralogic.blackpearl.nacre.api.BpConnector;
import com.spectralogic.blackpearl.nacre.model.Tape;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteTape {
    private static final Logger log = LoggerFactory.getLogger(DeleteTape.class);

    public static void byBarcode(String barcode, BpConnector pearl) {
        log.warn("Deleting tape [" + barcode + "] from database.");

    }

    public static void byId(String id, BpConnector pearl) throws Exception {
        log.warn("Sending delete command for tape: " + id);
        
        pearl.deleteTape(id);
    }

    public static void lostOrEjected(BpConnector pearl) {
        log.warn("Deleting all lost or ejected tapes from the BlackPearl.");

        log.info("Searching for lost tapes.");
        List<Tape> filtered_tapes = ListTapes.byState("lost", pearl);

        for(Tape tape : filtered_tapes) {
            log.warn("Deleting tape [" + tape.getBarCode() + "] from database.");
            try {
                byId(tape.getId(), pearl); // delete tape by barcode.
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to delete tape: " + tape.getBarCode() + "]");
            }
        }
        
        log.info("Searching for ejected tapes.");
        filtered_tapes = ListTapes.byState("ejected", pearl);

        for(Tape tape : filtered_tapes) {
            log.warn("Deleting tape [" + tape.getBarCode() + "] from database.");
            try {
                byId(tape.getId(), pearl); // delete tape by barcode.
            } catch(Exception e) {
                log.error(e.getMessage());
                log.error("Failed to delete tape: " + tape.getBarCode() + "]");
            }
        }
    }
}
