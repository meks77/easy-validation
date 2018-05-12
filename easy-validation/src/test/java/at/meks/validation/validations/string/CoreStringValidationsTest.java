package at.meks.validation.validations.string;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class CoreStringValidationsTest extends AbstractCoreValidationsTest {


    private static final String FIVE_LETTER_WORD = "apple";
    private static final String FOUR_LETTER_WORD = "bath";
    private static final String THREE_LETTER_WORD = "age";
    private static final String SIX_LETTER_WORD = "aspect";
    private static final String SEVEN_LETTER_WORD = "arrival";
    private static final String ERROR_IS_DATE = "must match to date format Value(YearOfEra,4,19,EXCEEDS_PAD)Value" +
            "(MonthOfYear,2)Value(DayOfMonth,2)";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private CoreStringValidations validations = new CoreStringValidations();

    @Test
    public void givenStringLength5WhenLengthIsMoreThan4ThenResultIsValid() {
        assertValidResult(validations.lengthIsMoreThan(() -> 4, () -> errorDescription).test(FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenLengthIsMoreThan4ThenResultIsError() {
        assertErrorResult(validations.lengthIsMoreThan(() -> 4, () -> errorDescription).test(FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength3WhenLengthIsLessThan4ThenResultIsValid() {
        assertValidResult(validations.lengthIsLessThan(() -> 4, () -> errorDescription).test(THREE_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenLengthIsLessThan4ThenResultIsError() {
        assertErrorResult(validations.lengthIsLessThan(() -> 4, () -> errorDescription).test(FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(validations.lengthIsBetween(() -> 4,  () -> 6, () -> errorDescription, () -> errorDescription)
                .test(FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength5WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(validations.lengthIsBetween(() -> 4,  () -> 6, () -> errorDescription, () -> errorDescription)
                .test(FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringLength6WhenLengthIsBetween4And6ThenResultIsValid() {
        assertValidResult(validations.lengthIsBetween(() -> 4,  () -> 6, () -> errorDescription, () -> errorDescription)
                .test(SIX_LETTER_WORD));
    }

    @Test
    public void givenStringLength3WhenLengthIsBetween4And6ThenResultIsError() {
        assertErrorResult(validations.lengthIsBetween(() -> 4, () -> 6, () -> errorDescription, () -> errorDescription)
                .test(THREE_LETTER_WORD));
    }

    @Test
    public void givenStringLength7WhenLengthIsBetween4And6ThenResultIsError() {
        assertErrorResult(validations.lengthIsBetween(() -> 4, () -> 6, () -> errorDescription, () -> errorDescription)
                .test(SEVEN_LETTER_WORD));
    }

    @Test
    public void givenStringLength4WhenHasLength4ThenResultIsValid() {
        assertValidResult(validations.hasLength(() -> 4, () -> errorDescription).test(FOUR_LETTER_WORD));
    }

    @Test
    public void givenStringLength3WhenHasLength4ThenResultIsError() {
        assertErrorResult(validations.hasLength(() -> 4, () -> errorDescription).test(THREE_LETTER_WORD));
    }

    @Test
    public void givenStringLength5WhenHasLength4ThenResultIsError() {
        assertErrorResult(validations.hasLength(() -> 4, () -> errorDescription).test(FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringContainsAWhenContainsAThenResultIsValid() {
        assertValidResult(validations.contains(() -> "a", () -> errorDescription).test(FIVE_LETTER_WORD));
    }

    @Test
    public void givenStringContainNosAWhenContainsAThenResultIsError() {
        assertErrorResult(validations.contains(() -> "a", () -> errorDescription).test("believe"));
    }

    @Test
    public void givenNotEmptyStringWhenIsNotBlankThenResultIsValid() {
        assertValidResult(validations.isNotBlank(errorDescription).test(FOUR_LETTER_WORD));
    }

    @Test
    public void givenSpacesStringWhenIsNotBlankThenResultIsError() {
        assertErrorResult(validations.isNotBlank(errorDescription).test("   "));
    }

    @Test
    public void givenLineBreaksStringWhenIsNotBlankThenResultIsError() {
        assertErrorResult(validations.isNotBlank(errorDescription).test("\n\n"));
    }

    @Test
    public void givenCarriageReturnStringWhenIsNotBlankThenResultIsError() {
        assertErrorResult(validations.isNotBlank(errorDescription).test("\r\r"));
    }

    @Test
    public void givenEmptyStringWhenIsNotBlankThenResultIsError() {
        assertErrorResult(validations.isNotBlank(errorDescription).test(""));
    }

    @Test
    public void givenNullStringWhenIsNotBlankThenResultIsError() {
        assertErrorResult(validations.isNotBlank(errorDescription).test(null));
    }

    @Test
    public void givenStringContainedInArrayWhenIsInListThenResultIsValid() {
        assertValidResult(validations.isInArray(() -> new String[] {"a", "b", "c"}, () -> errorDescription).test("b"));
    }

    @Test
    public void givenStringNotContainedInArrayWhenIsInListThenResultIsError() {
        assertErrorResult(validations.isInArray(() -> new String[]{"a", "b", "c"}, () -> errorDescription).test("d"));
    }

    @Test
    public void givenStringContainedInListWhenIsInListThenResultIsValid() {
        Set<String> listOfValues = new HashSet<>(Arrays.asList("a", "b", "c"));
        assertValidResult(validations.isInList(() -> listOfValues, () -> errorDescription).test("b"));
    }

    @Test
    public void givenStringNotContainedInListWhenIsInListThenResultIsError() {
        Set<String> listOfValues = new HashSet<>(Arrays.asList("a", "b", "c"));
        assertErrorResult(validations.isInList(() -> listOfValues, () -> errorDescription).test("d"));
    }

    @Test
    public void givenValidDateStringWhenIsDateThenResultIsValid() {
        assertValidResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("19900102"));
    }

    @Test
    public void givenDateStringWitDay0WhenIsDateThenResultIsError() {
        assertErrorResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("19900100"));
    }

    @Test
    public void givenDateStringWitMonth0WhenIsDateThenResultIsError() {
        assertErrorResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("19900001"));
    }

    @Test
    public void givenDateStringWitDay32WhenIsDateThenResultIsError() {
        assertErrorResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("19900132"));
    }

    @Test
    public void givenDateWithWrongFormatWhenIsDateThenResultIsError() {
        assertErrorResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("1990-10-32"));
    }

    @Test
    public void givenAlphanumericStringWhenIsDateThenResultIsError() {
        assertErrorResult(validations.isDate(() -> dateTimeFormatter, () -> errorDescription).test("invalid"));
    }

    @Test
    public void givenNumericStringWhenIsNumericThenResultIsValid() {
        assertValidResult(validations.isNumeric(errorDescription).test("12345"));
    }

    @Test
    public void givenHugeNumericStringWhenIsNumericThenResultIsValid() {
        String hugeNumericString = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        assertValidResult(validations.isNumeric(errorDescription).test(hugeNumericString));
    }

    @Test
    public void givenAlphaNumericStringWhenIsNumericThenResultIsError() {
        assertErrorResult(validations.isNumeric(errorDescription).test("a1234"));
    }

    @Test
    public void givenStringContainsNotOnlyMyWhenContainsNotOnlyThenResultIsOk() {
        assertValidResult(validations.containsNotOnly(() -> "my", () -> errorDescription).test("myWay"));
    }

    @Test
    public void givenStringContainsOnlyMyWhenContainsNotOnlyThenResultIsError() {
        assertErrorResult(validations.containsNotOnly(() -> "ma", () -> errorDescription).test("mama"));
    }

}