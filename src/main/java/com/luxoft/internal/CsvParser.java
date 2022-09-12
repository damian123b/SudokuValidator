package com.luxoft.internal;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {

    private final String DELIMITER = ",";

    public List<List<Integer>> parseCsv(File file) throws IOException {
        List<List<Integer>> parsed = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                List<Integer> sublist = new ArrayList<>();
                Arrays.stream(line.split(DELIMITER))
                        .map(this::tryMappingToInt)
                        .forEach(i -> sublist.add(i));
                ;
                parsed.add(sublist);
            }
        }
        return parsed;
    }

    private Integer tryMappingToInt(String s) {
        try {
            return StringUtils.isEmpty(s) ? -1 : Integer.valueOf(s);
        } catch (NumberFormatException e) {
           // e.printStackTrace();
            return -2;
        }
    }
}
