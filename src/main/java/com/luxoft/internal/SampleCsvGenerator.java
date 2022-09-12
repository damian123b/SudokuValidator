package com.luxoft.internal;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SampleCsvGenerator {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> content = initializeBoard();

        try (CSVPrinter printer = new CSVPrinter(new FileWriter("src/main/resources/sample.csv"), CSVFormat.DEFAULT))
        {
            content.forEach(row -> {
                try {
                    printer.printRecord(row);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static List<List<Integer>> initializeBoard() {
        List<List<Integer>> content = new ArrayList<>();
        Random r = new Random();

        Integer[] row1 = {4,3,5,2,6,9,7,8,1};
        content.add(Arrays.asList(row1));

        // the right value for row 2 is 7
        int x = r.nextInt(6,10);

        // if previous value is ok(= 7), then once three draws
        // switch it with value below to be able to test more validation rules
        int y = 3;
        if (x == 7) {
            if (r.nextInt(1,4) == 3) {
                x = 3;
                y = 7;
            }
        }

        Integer[] row2 = {6,8,2,5, x ,1,4,9,3};
        content.add(Arrays.asList(row2));

        Integer[] row3 = {1,9,7,8,y,4,5,6,2};
        content.add(Arrays.asList(row3));
        Integer[] row4 = {8,2,6,1,9,5,3,4,7};
        content.add(Arrays.asList(row4));
        Integer[] row5 = {3,7,4,6,8,2,9,1,5};
        content.add(Arrays.asList(row5));
        Integer[] row6 = {9,5,1,7,4,3,6,2,8};
        content.add(Arrays.asList(row6));
        Integer[] row7 = {5,1,9,3,2,6,8,7,4};
        content.add(Arrays.asList(row7));
        Integer[] row8 = {2,4,8,9,5,7,1,3,6};
        content.add(Arrays.asList(row8));
        Integer[] row9 = {7,6,3,4,1,8,2,5,9};
        content.add(Arrays.asList(row9));

        return content;
    }
}
