//===================================================================
// Table.java
//      Description:
//          This class handles output to the shell in the table format.
//===================================================================

package com.spectralogic.blackpearl.nacre.ui.display;

import com.spectralogic.blackpearl.nacre.model.Output;

import java.util.ArrayList;

public class Table {
    public static void print(ArrayList<Output> output) {
        ArrayList<String> headers = getHeaders(output);
        ArrayList<Integer> field_sizes = getFieldSize(output);
        int i=0;

        if(headers == null) {
            System.err.println("Failed to build headers for table output.");
        } else {
            printLine(field_sizes);

            //===================================
            // Print headers
            //===================================
            for(String header : headers) {
                printLeftAligned(header, field_sizes.get(i));
                i++;
            }

            //===================================
            // Reset for Printing Values
            //===================================
            // reset iterator for field sizes
            i=0;
            // Print a new line
            System.out.println("|");
            printLine(field_sizes);

            //===================================
            // Print values
            //===================================
            for(Output pair : output) {
                if(pair.getKey().equals("new-line")) {
                    System.out.println("|"); // row terminator
                    i=0;

                    printLine(field_sizes);

                } else {
                    printLeftAligned(pair.getValue(), field_sizes.get(i));

                    i++;
                }
            }
        }
    }

    private static ArrayList<Integer> getFieldSize(ArrayList<Output> output) {
        ArrayList<Integer> max_size = new ArrayList<Integer>();
        int i = 0;

        for(Output pair : output) {
            if(pair.getKey().equals("new-line")) {
                // reset the field iterator
                i=0;
            } else {
                // Initialize the max size as the header
                if(max_size.size() <= i) { // <= i is used as max_size.size() == 0 means no values. i = 0 points to first value
                    max_size.add(pair.getKey().length());
                } 
            
                // Check to see if the value is better
                if(pair.getValue().length() > max_size.get(i)) {
                    max_size.set(i, pair.getValue().length());
                }

                i++;
            }
        }

        return max_size;
    }

    private static ArrayList<String> getHeaders(ArrayList<Output> output) {
        ArrayList<String> headers = new ArrayList<String>();

        for(Output pair : output) {
            if(pair.getKey().equals("new-line")) {
                return headers;
            } else {
                headers.add(pair.getKey());
            }
        }

        return null;
    }

    private static void printLeftAligned(String value, int field_size) {
        System.out.print("| " + value);

        for(int i=value.length(); i<field_size + 1; i++) {
            System.out.print(" ");
        }
    }

    private static void printLine(ArrayList<Integer> maxSizes) {
        for(Integer counter : maxSizes) {
            System.out.print("+"); // column header

            for(int i=0; i<counter + 2; i++) {
                System.out.print("-"); // row marker
            }
        }

        System.out.println("+"); // column terminator
    }
}
