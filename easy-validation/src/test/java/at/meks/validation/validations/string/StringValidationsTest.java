package at.meks.validation.validations.string;

import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.format.DateTimeFormatter;

import static org.fest.assertions.api.Assertions.assertThat;


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
    public void testConstructorIsPrivate() throws Exception {
        Constructor<StringValidations> constructor = StringValidations.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
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
    public void testLengthIsBetween() {
        testHelper.testLengthIsBetween(StringValidations::lengthIsBetween);
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