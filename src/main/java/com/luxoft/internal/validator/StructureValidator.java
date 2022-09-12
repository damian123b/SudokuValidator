package com.luxoft.internal.validator;

import com.luxoft.internal.Main;

import java.util.List;

public class StructureValidator implements SudokuValidator {

    private final int SIZE = 9;

    @Override
    public boolean validate(List<List<Integer>> parsedCsv) {

        if (parsedCsv.size() != SIZE) {
            Main.errorMessage =
                    "Wrong structure of the file. Either number of rows or size of a row does not equal " + SIZE + ".";
            return false;
        }

        for (List<Integer> list : parsedCsv) {
            if (list.size() != SIZE) {
                Main.errorMessage =
                        "Wrong structure of the file. Either number of rows or size of a row does not equal " + SIZE + ".";
                return false;
            }
            if (list.contains(-1)) {
                Main.errorMessage =
                        "Wrong structure of the file. One or more values is null or empty.";
                return false;
            }
            if (list.contains(-2)) {
                Main.errorMessage =
                        "Wrong structure of the file. One or more values is not numeric.";
                return false;
            }
        }
        return true;
    }
}
