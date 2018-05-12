package at.meks.validation.validations.string;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

public class StringValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<String> {

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
        testHelper.testLengthIsMoreThan(size -> StringValidationsWithErrorCode.lengthIsMoreThan(size, EXPECTED_CODE));
    }

    @Test
    public void testLengthIsLessThan() {
        testHelper.testLengthIsLessThan(size -> StringValidationsWithErrorCode.lengthIsLessThan(size, EXPECTED_CODE));
    }

    @Test
    public void testLengthIsBetween() {
        testHelper.testLengthIsBetween((min, max) -> StringValidationsWithErrorCode.lengthIsBetween(min, max,
                EXPECTED_CODE, EXPECTED_CODE));
    }

    @Test
    public void testHasLength() {
        testHelper.testHasLength(size -> StringValidationsWithErrorCode.hasLength(size, EXPECTED_CODE));
    }

    @Test
    public void testContains() {
        testHelper.testContains(s -> StringValidationsWithErrorCode.contains(s, EXPECTED_CODE));
    }

    @Test
    public void testIsNotBlank() {
        testHelper.testIsNotBlank(() -> StringValidationsWithErrorCode.isNotBlank(EXPECTED_CODE));
    }

    @Test
    public void testIsInArray() {
        testHelper.testIsInArray(supplier -> StringValidationsWithErrorCode.isInArray(supplier, EXPECTED_CODE));
    }

    @Test
    public void testIsInList() {
        testHelper.testIsInList(supplier -> StringValidationsWithErrorCode.isInList(supplier, EXPECTED_CODE));
    }

    @Test
    public void testIsDate() {
        testHelper.testIsDate(formatter -> StringValidationsWithErrorCode.isDate(formatter, EXPECTED_CODE));
    }

    @Test
    public void testIsNumeric() {
        testHelper.testIsNumeric(() -> StringValidationsWithErrorCode.isNumeric(EXPECTED_CODE));
    }

    @Test
    public void testContainsNot() {
        testHelper.testContainsNotOnly(s -> StringValidationsWithErrorCode.containsNotOnly(s, EXPECTED_CODE));
    }

}