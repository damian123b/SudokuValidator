package com.luxoft.internal.validator;

import com.luxoft.internal.Main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SquaresValidator implements SudokuValidator {

    @Override
    public boolean validate(List<List<Integer>> parsedCsv) {

        boolean newSquare = false;
        List<Set<Integer>> subsquares = new ArrayList<>();
        Set<Integer> subsq = null;

        for (int i = 0; i <= parsedCsv.size() - 3; i += 3) {
            for (int j = 0; j < parsedCsv.get(i).size(); j++) {

                if (i % 3 == 0 && j % 3 == 0) {
                    subsq = new LinkedHashSet<>();
                    subsquares.add(subsq);
                    newSquare = true;
                    subsq.add(parsedCsv.get(i).get(j));
                    subsq.add(parsedCsv.get(i + 1).get(j));
                    subsq.add(parsedCsv.get(i + 2).get(j));
                } else {
                    newSquare = false;
                }

                if (!newSquare) {
                    subsq.add(parsedCsv.get(i).get(j));
                    subsq.add(parsedCsv.get(i + 1).get(j));
                    subsq.add(parsedCsv.get(i + 2).get(j));
                }
            }
        }

        for (Set<Integer> subsquare : subsquares) {
            if (subsquare.size() != 9) {
                Main.errorMessage = "A square containing duplicate numbers was found.";
                return false;
            }
        }
        return true;
    }
}
