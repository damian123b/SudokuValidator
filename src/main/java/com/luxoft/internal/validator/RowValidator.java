package com.luxoft.internal.validator;

import com.luxoft.internal.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RowValidator implements SudokuValidator {

    @Override
    public boolean validate(List<List<Integer>> parsedCsv) {

        for (List<Integer> row : parsedCsv) {
            Set<Integer> rowSet = new HashSet<>(row);
            if (rowSet.size() != 9) {
                Main.errorMessage = "A row containing duplicate numbers was found.";
                return false;
            }
        }
        return true;
    }
}
