package com.example.packdel;

import com.example.packdel.data.Package;
import com.example.packdel.exception.InputParseException;
import com.example.packdel.io.input.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class InputParserTest {


    private final InputParser inputParser = new InputParser();
    private static final String TEST_INPUT1 = "3.5 13000";
    private static final String TEST_BAD_INPUT1 = "3.5 130000";
    private static final String TEST_BAD_INPUT2 = "3,5 13000";

    @Test
    public void testInput() {
        Package pcg = inputParser.parsePackage(TEST_INPUT1);
        Assert.assertEquals(pcg.getWeight(), BigDecimal.valueOf(3.5));
        Assert.assertEquals(pcg.getPostalCode(), "13000");
    }

    @Test(expected = InputParseException.class)
    public void testBadInput1() {
        inputParser.parsePackage(TEST_BAD_INPUT1);
    }

    @Test(expected = InputParseException.class)

    public void testBadInput2() {
        inputParser.parsePackage(TEST_BAD_INPUT2);
    }


}
