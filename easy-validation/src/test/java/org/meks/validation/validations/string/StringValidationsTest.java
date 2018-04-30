package org.meks.validation.validations.string;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.meks.validation.validations.AbstractValidationsTest;
import org.mockito.Mock;

import java.time.format.DateTimeFormatter;


public class StringValidationsTest extends AbstractValidationsTest<String> {


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
        return StringValidations.class;
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
        testHelper.testLengthIsMoreThan(StringValidations::lengthIsMoreThan);
    }

    @Test
    public void testLengthIsLessThan() {
        testHelper.testLengthIsLessThan(StringValidations::lengthIsLessThan);
    }

    @Test
    @Ignore("verificatino fails currently. between is a special case where the tests needs to be implemented special")
    public void testLengthIsBetween() {
        testHelper.testLengthIsBetween(range -> StringValidations.lengthIsBetween(range.getMinimum(), range.getMaximum()));
    }

    @Test
    public void testHasLength() {
        testHelper.testHasLength(StringValidations::hasLength);
    }

    @Test
    public void testContains() {
        testHelper.testContains(StringValidations::contains);
    }

    @Test
    public void testIsNotBlank() {
        testHelper.testIsNotBlank(StringValidations::isNotBlank);
    }

    @Test
    public void testIsInArray() {
        testHelper.testIsInArray(StringValidations::isInArray);
    }

    @Test
    public void testIsInList() {
        testHelper.testIsInList(StringValidations::isInList);
    }

    @Test
    public void testIsDate() {
        testHelper.testIsDate(StringValidations::isDate);
    }

    @Test
    public void testIsNumeric() {
        testHelper.testIsNumeric(StringValidations::isNumeric);
    }

    @Test
    public void testContainsNot() {
        testHelper.testContainsNotOnly(StringValidations::containsNotOnly);
    }

}