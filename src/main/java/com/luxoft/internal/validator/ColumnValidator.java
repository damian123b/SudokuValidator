package com.luxoft.internal.validator;

import com.luxoft.internal.Main;

import java.util.*;

public class ColumnValidator implements SudokuValidator {

    @Override
    public boolean validate(List<List<Integer>> parsedCsv) {

        List<Set<Integer>> columns = new ArrayList<>();

        for (int i = 0; i < parsedCsv.size(); i++) {
            for (int j = 0; j < parsedCsv.get(i).size(); j++) {
                if (i == 0) {
                    columns.add(new LinkedHashSet<>());
                }
                columns.get(j).add(parsedCsv.get(i).get(j));
            }
        }

        for (Set<Integer> c : columns) {
            if (c.size() != 9) {
                Main.errorMessage = "A column containing duplicate numbers was found.";
                return false;
            }
        }
        return true;
    }
}
