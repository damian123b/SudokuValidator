package com.luxoft.internal;

import com.luxoft.internal.validator.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuValidatorTest {

    private final String PREFIX = "src/main/resources/csv/";
    private static CsvParser parser;
    private static SudokuValidator validator;
    private static List<List<Integer>> list;

    @BeforeClass
    public static void initializeParser() {
        parser = new CsvParser();
        list = new ArrayList<>();
    }

    @After
    public void clearList() {
        list.clear();
    }

    @Test
    public void structureValidationShouldFail() {
        try {
            list = parser.parseCsv(new File(PREFIX + "wrong_structure.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new StructureValidator();
        assertFalse(validator.validate(list));
    }

    @Test
    public void rowsValidationShouldFail() {
        try {
            list = parser.parseCsv(new File(PREFIX + "wrong_rows_good_cols.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new RowValidator();
        assertFalse(validator.validate(list));
    }

    @Test
    public void columnsValidationShouldFail() {
        try {
            list = parser.parseCsv(new File(PREFIX + "good_rows_wrong_cols.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new ColumnValidator();
        assertFalse(validator.validate(list));
    }

    @Test
    public void squaresValidationShouldFail() {
        try {
            list = parser.parseCsv(new File(PREFIX + "wrong_rows_and_cols.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new SquaresValidator();
        assertFalse(validator.validate(list));
    }

    @Test
    public void rowsValidationShouldPass() {
        try {
            list = parser.parseCsv(new File(PREFIX + "good_rows_wrong_cols.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new RowValidator();
        assertTrue(validator.validate(list));
    }

    @Test
    public void columnValidationShouldPass() {
        try {
            list = parser.parseCsv(new File(PREFIX + "wrong_rows_good_cols.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new ColumnValidator();
        assertTrue(validator.validate(list));
    }

    @Test
    public void squaresValidationShouldPass() {
        try {
            list = parser.parseCsv(new File(PREFIX + "all_ok.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new SquaresValidator();
        assertTrue(validator.validate(list));
    }

    @Test
    public void rangeValidationShouldFail() {
        try {
            list = parser.parseCsv(new File(PREFIX + "too_high.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new RangeValidator();
        assertFalse(validator.validate(list));
    }

    @Test
    public void allValidationShouldPass() {
        try {
            list = parser.parseCsv(new File(PREFIX + "all_ok.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        validator = new SquaresValidator();
        assertTrue(new StructureValidator().validate(list)
                    && new RangeValidator().validate(list)
                    && new RowValidator().validate(list)
                    && new ColumnValidator().validate(list)
                    && new SquaresValidator().validate(list));
    }
}
