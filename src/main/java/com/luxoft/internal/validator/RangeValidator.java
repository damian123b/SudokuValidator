package com.luxoft.internal.validator;

import com.luxoft.internal.Main;

import java.util.List;

public class RangeValidator implements SudokuValidator {

    @Override
    public boolean validate(List<List<Integer>> parsedCsv) {

        for (List<Integer> row : parsedCsv) {
            for (Integer i : row) {
                if (i.intValue() < 1 || i.intValue() > 9) {
                    Main.errorMessage = "A number out of 1-9 range was found.";
                    return false;
                }
            }
        }
        return true;
    }
}
