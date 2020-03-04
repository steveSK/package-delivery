package com.example.packdel.io.input;

import com.example.packdel.data.Package;
import com.example.packdel.exception.InputParseException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final String delimiter = " ";
    private static final String correctInputExample = "2.5 13000";

    private final Pattern postalCodePattern = Pattern.compile("^[0-9]{5,5}$");
    private final Pattern weightPattern = Pattern.compile("^[0-9]{1,1}\\.[0-9]{1,3}$");

    public Package parsePackage(String input) {
        System.out.println("Processing record: " + input);
        String[] inputArgs = input.split(delimiter);

        validateInputLength(input, inputArgs);

        String weight = inputArgs[0];
        String postalCode = inputArgs[1];

        validatePostalCode(input, postalCode);
        validateWeight(input, weight);

        return new Package(postalCode, new BigDecimal(weight));
    }

    private void validateInputLength(String inputLine, String[] input) {
        if (input.length > 2) {
            throw new InputParseException(inputLine, "There is too many arguments at the input: " + input.length + " The correct input looks like: '" + correctInputExample + "'");
        }

        if (input.length < 2) {
            throw new InputParseException(inputLine, "There is too few arguments at the input: " + input.length + " The correct input looks like: '" + correctInputExample + "'");
        }
    }


    private void validateWeight(String inputLine, String weight) {
        Matcher matcher = weightPattern.matcher(weight);
        if (!matcher.matches()) {
            throw new InputParseException(inputLine, "Weight is in the wrong format: " + weight + " The correct input looks like: '" + correctInputExample + "'");
        }
    }


    private void validatePostalCode(String inputLine, String postalCode) {
        Matcher matcher = postalCodePattern.matcher(postalCode);
        if (!matcher.matches()) {
            throw new InputParseException(inputLine, "Postal Code is in the wrong format: " + postalCode + " The correct input looks like: '" + correctInputExample + "'");
        }
    }

}
