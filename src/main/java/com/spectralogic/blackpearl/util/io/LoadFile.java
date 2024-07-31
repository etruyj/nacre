//===================================================================
// LoadFile.java
//      Description:
//          This class handles the commands associated with loading
//          a file.
//
// Created by Sean Snyder
// Copyright Spectra Logic 2024
//===================================================================

package com.spectralogic.blackpearl.nacre.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;

public class LoadFile {
    public static String toString(String file) throws IOException {
        StringBuilder file_string = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(new File(file)));

        String line = null;

        while((line = br.readLine()) != null) {
            file_string.append(line);
            file_string.append("\n");
        }

        return file_string.toString();
    }
}
