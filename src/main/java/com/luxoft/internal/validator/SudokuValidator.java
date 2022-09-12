package com.luxoft.internal.validator;

import java.util.List;

public interface SudokuValidator {

     boolean validate(List<List<Integer>> parsedCsv);
}
