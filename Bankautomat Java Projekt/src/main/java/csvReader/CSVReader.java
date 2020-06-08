/*
 * CSVReader.java
 *
 * Created on 2020-06-08
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private CSVReader() {
    }

    public static List<String> readCSVFile(final File file) throws IOException {

        List<String> lines = new ArrayList<>();
        final BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        br.readLine();  //skip first line in the file

        do {

            line = br.readLine();

            if (line != null) {

                lines.add(line);
            }
        } while (line != null);

        return lines;
    }
}
