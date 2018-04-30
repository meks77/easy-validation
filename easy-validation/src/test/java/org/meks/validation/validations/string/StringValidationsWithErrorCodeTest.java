package org.meks.validation.validations.string;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.meks.validation.validations.AbstractCodeValidationsTest;
import org.meks.validation.validations.AbstractValidationsTest;
import org.mockito.Mock;

import java.time.format.DateTimeFormatter;


public class StringValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<String> {


    private static final String FIVE_LETTER_WORD = "apple";
    private static final String FOUR_LETTER_WORD = "bath";
    private static final String THREE_LETTER_WORD = "age";
    private static final String SIX_LETTER_WORD = "aspect";
    private static final String SEVEN_LETTER_WORD = "arrival";
    private static final String ERROR_IS_DATE = "must match to date format Value(YearOfEra,4,19,EXCEEDS_PAD)Value" +
            "(MonthOfYear,2)Value(DayOfMonth,2)";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Mock
    private CoreStringValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return StringValidationsWithErrorCode.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    private StringValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new StringValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testLengthIsMoreThan() {
        testHelper.testLengthIsMoreThan(size -> StringValidationsWithErrorCode.lengthIsMoreThan(size, expectedCode));
    }

    @Test
    public void testLengthIsLessThan() {
        testHelper.testLengthIsLessThan(size -> StringValidationsWithErrorCode.lengthIsLessThan(size, expectedCode));
    }

    @Test
    @Ignore("verificatino fails currently. between is a special case where the tests needs to be implemented special")
    public void testLengthIsBetween() {
        testHelper.testLengthIsBetween(range -> StringValidationsWithErrorCode.lengthIsBetween(range.getMinimum(),
                range.getMaximum(), expectedCode, expectedCode));
    }

    @Test
    public void testHasLength() {
        testHelper.testHasLength(size -> StringValidationsWithErrorCode.hasLength(size, expectedCode));
    }

    @Test
    public void testContains() {
        testHelper.testContains(s -> StringValidationsWithErrorCode.contains(s, expectedCode));
    }

    @Test
    public void testIsNotBlank() {
        testHelper.testIsNotBlank(() -> StringValidationsWithErrorCode.isNotBlank(expectedCode));
    }

    @Test
    public void testIsInArray() {
        testHelper.testIsInArray(supplier -> StringValidationsWithErrorCode.isInArray(supplier, expectedCode));
    }

    @Test
    public void testIsInList() {
        testHelper.testIsInList(supplier -> StringValidationsWithErrorCode.isInList(supplier, expectedCode));
    }

    @Test
    public void testIsDate() {
        testHelper.testIsDate(formatter -> StringValidationsWithErrorCode.isDate(formatter, expectedCode));
    }

    @Test
    public void testIsNumeric() {
        testHelper.testIsNumeric(() -> StringValidationsWithErrorCode.isNumeric(expectedCode));
    }

    @Test
    public void testContainsNot() {
        testHelper.testContainsNotOnly(s -> StringValidationsWithErrorCode.containsNotOnly(s, expectedCode));
    }

}