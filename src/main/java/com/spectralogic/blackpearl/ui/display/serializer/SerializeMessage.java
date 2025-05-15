//===================================================================
// SerializeMessage.java
//      Description:
//          This class serializes the message response for
//          output by converting the fields to a key-value pair.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.blackpearl.nacre.ui.display.serializer;

import com.spectralogic.blackpearl.nacre.model.Message;
import com.spectralogic.blackpearl.nacre.model.Output;

import java.util.ArrayList;

public class SerializeMessage {
    public static ArrayList<Output> forOutput(ArrayList<Message> messages) {
        ArrayList<Output> output = new ArrayList<Output>();
        Output pair = null;

        if(messages != null) {
            for(Message message : messages) {
                pair = new Output("severity", String.valueOf(message.getSeverity()));
                output.add(pair);
                
                pair = new Output("created", String.valueOf(message.getCreatedAt()));
                output.add(pair);
                
               
                if(message.isRead()) {
                    pair = new Output("read", "read");
                } else {
                    pair = new Output("read", "");
                }
                output.add(pair);
                
                pair = new Output("message", message.getDescriptionText());
                output.add(pair);

                pair = new Output("new-line", "");
                output.add(pair);
            }
        }

        return output;
    }
}
