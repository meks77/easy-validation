package at.meks.validation.validations.number;

import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.fest.assertions.api.Assertions.assertThat;

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
    public void testConstructorIsPrivate() throws Exception {
        Constructor<NumberValidations> constructor = NumberValidations.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
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
    public void testIsBetween() {
        testHelper.testIsBetween(NumberValidations::isBetween);
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