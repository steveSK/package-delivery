package com.example.packdel;

import com.example.packdel.data.PackagesSummary;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class OutputParserTest {

    private final ByteArrayOutputStream outNew = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final PackagesSummary testPackageSummery = new PackagesSummary("13000", BigDecimal.valueOf(3.5));


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outNew));
    }

    public void testStandardWriter(){

    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
