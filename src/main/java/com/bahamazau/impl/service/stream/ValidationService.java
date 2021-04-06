package com.bahamazau.impl.service.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class ValidationService {

    private static final String SPACES_REGEX = "\\s+";

    private static final String NOT_DIGITS_REGEX = "[^-?\\d]";
    private static final Pattern PATTERN_NOT_DIGITS = Pattern.compile(NOT_DIGITS_REGEX);

    /**
     * Validation of String on containing only digits validation.
     *
     * @param nextLine string for parse on array
     * @return true if string is not empty and contain only digits
     */
    boolean containOnlyDigits(String nextLine) {
        Optional<String> notDigitsStringsOptional = Arrays.stream(nextLine.split(SPACES_REGEX))
                .filter(element -> PATTERN_NOT_DIGITS.matcher(element).find())
                .findAny();
        return !(nextLine.isEmpty() || notDigitsStringsOptional.isPresent());
    }
}
