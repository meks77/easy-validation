package org.meks.validation;

import org.junit.Test;
import org.meks.validation.result.ValidationResult;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.meks.validation.TestUtils.assertErrorResult;
import static org.meks.validation.TestUtils.assertValidResult;


public class StringValidationsTest {

    private static final String FIVE_LETTER_WORD = "apple";
    private static final String FOUR_LETTER_WORD = "bath";
    private static final String THREE_LETTER_WORD = "age";
    private static final String SIX_LETTER_WORD = "aspect";
    private static final String SEVEN_LETTER_WORD = "arrival";
    private static final String ERROR_IS_DATE = "must match to date format Value(YearOfEra,4,19,EXCEEDS_PAD)Value" +
            "(MonthOfYear,2)Value(DayOfMonth,2)";

    @Test
    public void givenStringLength5WhenLengthIsMoreThan4ThenResultIsValid() {
        assertValidResult(StringValidations.lengthIsMoreThan(4).test(() -> FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenLengthIsMoreThan4ThenResultIsError() {
        ValidationResult result = StringValidations.lengthIsMoreThan(4).test(() -> FOUR_LETTER_WORD);
        assertErrorResult(result, "must have more than 4 chars");
    }

    @Test
    public void givenStringLength3WhenLengthIsLessThan4ThenResultIsValid() {
        assertValidResult(StringValidations.lengthIsLessThan(4).test(() -> THREE_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenLengthIsLessThan4ThenResultIsError() {
        ValidationResult result = StringValidations.lengthIsLessThan(4).test(() -> FOUR_LETTER_WORD);
        assertErrorResult(result, "must have less than 4 chars");
    }

    @Test
    public void givenStringLength4WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(StringValidations.lengthIsBetween(4,  6).test(() -> FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength5WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(StringValidations.lengthIsBetween(4,  6).test(() -> FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringLength6WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(StringValidations.lengthIsBetween(4,  6).test(() -> SIX_LETTER_WORD));
    }

    @Test
    public void givenStringLength3WhenLengthIsBetween4And6ThenResultIsError() {
        ValidationResult result = StringValidations.lengthIsBetween(4, 6).test(() -> THREE_LETTER_WORD);
        assertErrorResult(result, "must have more than 3 chars");
    }

    @Test
    public void givenStringLength7WhenLengthIsBetween4And6ThenResultIsError() {
        ValidationResult test = StringValidations.lengthIsBetween(4, 6).test(() -> SEVEN_LETTER_WORD);
        assertErrorResult(test, "must have less than 7 chars");
    }

    @Test
    public void givenStringLength4WhenHasLength4ThenResultIsValid() {
        assertValidResult(StringValidations.hasLength(4).test(() -> FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength3WhenHasLength4ThenResultIsError() {
        ValidationResult result = StringValidations.hasLength(4).test(() -> THREE_LETTER_WORD);
        assertErrorResult(result, "length must be 4 chars");
    }

    @Test
    public void givenStringLength5WhenHasLength4ThenResultIsError() {
        ValidationResult result = StringValidations.hasLength(4).test(() -> FIVE_LETTER_WORD);
        assertErrorResult(result, "length must be 4 chars");
    }

    @Test
    public void givenStringContainsAWhenContainsAThenResultIsValid() {
        assertValidResult(StringValidations.contains("a").test(() -> FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringContainNosAWhenContainsAThenResultIsError() {
        ValidationResult result = StringValidations.contains("a").test(() -> "believe");
        assertErrorResult(result, "must contain a");
    }

    @Test
    public void givenNotEmptyStringWhenIsNotBlankThenResultIsValid() {
        assertValidResult(StringValidations.isNotBlank().test(() -> FOUR_LETTER_WORD));
    }

    @Test
    public void givenSpacesStringWhenIsNotBlankThenResultIsError() {
        ValidationResult result = StringValidations.isNotBlank().test(() -> "   ");
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenLineBreaksStringWhenIsNotBlankThenResultIsError() {
        ValidationResult result = StringValidations.isNotBlank().test(() -> "\n\n");
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenCarriageReturnStringWhenIsNotBlankThenResultIsError() {
        ValidationResult result = StringValidations.isNotBlank().test(() -> "\r\r");
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenEmptyStringWhenIsNotBlankThenResultIsError() {
        ValidationResult result = StringValidations.isNotBlank().test(() -> "");
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenNullStringWhenIsNotBlankThenResultIsError() {
        ValidationResult result = StringValidations.isNotBlank().test(() -> null);
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenStringContainedInArrayWhenIsInListThenResultIsValid() {
        assertValidResult(StringValidations.isInArray(() -> new String[] {"a", "b", "c"}).test(() -> "b"));
    }

    @Test
    public void givenStringNotContainedInArrayWhenIsInListThenResultIsError() {
        ValidationResult result = StringValidations.isInArray(() -> new String[]{"a", "b", "c"}).test(() -> "d");
        assertErrorResult(result, "must be in list: [a, b, c]");
    }

    @Test
    public void givenStringContainedInListWhenIsInListThenResultIsValid() {
        Set<String> listOfValues = new HashSet<>(Arrays.asList("a", "b", "c"));
        assertValidResult(StringValidations.isInList(() -> listOfValues).test(() -> "b"));
    }

    @Test
    public void givenStringNotContainedInListWhenIsInListThenResultIsError() {
        Set<String> listOfValues = new HashSet<>(Arrays.asList("a", "b", "c"));
        ValidationResult result = StringValidations.isInList(() -> listOfValues).test(() -> "d");
        assertErrorResult(result, "must be in list: [a, b, c]");
    }

    @Test
    public void givenValidDateStringWhenIsDateThenResultIsValid() {
        assertValidResult(StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() -> "19900102"));
    }

    @Test
    public void givenDateStringWitDay0WhenIsDateThenResultIsError() {
        ValidationResult result = StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() ->
                "19900100");
        assertErrorResult(result, ERROR_IS_DATE);
    }

    @Test
    public void givenDateStringWitMonth0WhenIsDateThenResultIsError() {
        ValidationResult result = StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() ->
                "19900001");
        assertErrorResult(result, ERROR_IS_DATE);
    }

    @Test
    public void givenDateStringWitDay32WhenIsDateThenResultIsError() {
        ValidationResult result = StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() ->
                "19900132");
        assertErrorResult(result, ERROR_IS_DATE);
    }

    @Test
    public void givenDateWithWrongFormatWhenIsDateThenResultIsError() {
        ValidationResult result = StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() ->
                "1990-10-32");
        assertErrorResult(result, ERROR_IS_DATE);
    }

    @Test
    public void givenAlphanumericStringWhenIsDateThenResultIsError() {
        ValidationResult result = StringValidations.isDate(DateTimeFormatter.ofPattern("yyyyMMdd")).test(() ->
                "invalid");
        assertErrorResult(result, ERROR_IS_DATE);
    }

    @Test
    public void givenNumericStringWhenIsNumericThenResultIsValid() {
        assertValidResult(StringValidations.isNumeric().test(() -> "12345"));
    }

    @Test
    public void givenHugeNumericStringWhenIsNumericThenResultIsValid() {
        String hugeNumericString = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        assertValidResult(StringValidations.isNumeric().test(() -> hugeNumericString));
    }

    @Test
    public void givenAlphaNumericStringWhenIsNumericThenResultIsError() {
        ValidationResult result = StringValidations.isNumeric().test(() -> "a1234");
        assertErrorResult(result, "value must be numeric");
    }

    @Test
    public void givenStringContainsNotOnlyMyWhenContainsNotOnlyThenResultIsOk() {
        assertValidResult(StringValidations.containsNotOnly("my").test(() -> "myWay"));
    }

    @Test
    public void givenStringContainsOnlyMyWhenContainsNotOnlyThenResultIsError() {
        ValidationResult result = StringValidations.containsNotOnly("ma").test(() -> "mama");
        assertErrorResult(result, "value mustn't contain only ma");
    }

}