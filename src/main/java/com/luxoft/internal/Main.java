package com.luxoft.internal;


import com.luxoft.internal.validator.*;

import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String errorMessage = null;

    public static void main(String[] args) {
        int result = 0;
        List<List<Integer>> parsed;

        CsvParser parser = new CsvParser();
        try {
             parsed = parser.parseCsv(new File(args[0]));
        } catch (IOException e) {
            errorMessage = "Error reading file. Please verify if it exists and check if the name is correct.";
            result = 1;
            System.out.println(result + " "+ errorMessage);
            return;
        }
        List<SudokuValidator> validators = new ArrayList<>();
        validators.add(new StructureValidator());
        validators.add(new RangeValidator());
        validators.add(new RowValidator());
        validators.add(new ColumnValidator());
        validators.add(new SquaresValidator());

        for (SudokuValidator v : validators) {
            if (!v.validate(parsed)) {
                result = 1;
                break;
            }
        }

        System.out.println(result + ( (errorMessage != null) ?  " " + errorMessage : ""));
    }
}
