package at.meks.validation.validations.number;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

public class NumberValidationsTest extends AbstractValidationsTest<Number> {

    @Mock
    private CoreNumberValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return NumberValidations.class;
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
        testHelper.testIsLessThan(NumberValidations::isLessThan);
    }

    @Test
    public void testIsGreaterThan() {
        testHelper.testIsGreaterThan(NumberValidations::isGreaterThan);
    }

    @Test
    @Ignore
    public void testIsBetween() {
    }

    @Test
    public void testIsInt() {
        testHelper.testIsInt(NumberValidations::isInt);
    }

    @Test
    public void testIsByte() {
        testHelper.testIsByte(NumberValidations::isByte);
    }

    @Test
    public void testIsShort() {
        testHelper.testIsShort(NumberValidations::isShort);
    }
}