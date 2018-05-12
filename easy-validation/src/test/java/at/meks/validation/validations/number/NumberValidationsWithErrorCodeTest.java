package at.meks.validation.validations.number;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isBetween;
import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isByte;
import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isGreaterThan;
import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isInt;
import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isLessThan;
import static at.meks.validation.validations.number.NumberValidationsWithErrorCode.isShort;

public class NumberValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<Number> {

    @Mock
    private CoreNumberValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return NumberValidationsWithErrorCode.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    private NumberValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new NumberValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testIsLessThan() {
        testHelper.testIsLessThan(compareTo -> isLessThan(compareTo, EXPECTED_CODE));
    }

    @Test
    public void testIsGreaterThan() {
        testHelper.testIsGreaterThan(compareTo -> isGreaterThan(compareTo, EXPECTED_CODE));
    }

    @Test
    public void testIsBetween() {
        testHelper.testIsBetween((min, max) -> isBetween(min, max, EXPECTED_CODE));
    }

    @Test
    public void testIsInt() {
        testHelper.testIsInt(() -> isInt(EXPECTED_CODE));
    }

    @Test
    public void testIsByte() {
        testHelper.testIsByte(() -> isByte(EXPECTED_CODE));
    }

    @Test
    public void testIsShort() {
        testHelper.testIsShort(() -> isShort(EXPECTED_CODE));
    }
}