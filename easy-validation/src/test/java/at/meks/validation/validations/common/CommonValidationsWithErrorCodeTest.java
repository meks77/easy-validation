package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractErrorCodeValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isBetween;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isEqualTo;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isGreaterThan;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isLessThan;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isNotEqualTo;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.notNull;
import static org.fest.assertions.api.Assertions.assertThat;

public class CommonValidationsWithErrorCodeTest extends AbstractErrorCodeValidationsTest {

    @Mock
    private CoreCommonValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return CommonValidationsWithErrorCode.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    private CommonsValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new CommonsValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<CommonValidationsWithErrorCode> constructor = CommonValidationsWithErrorCode.class
                .getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testNotNull() {
        testHelper.testNotNull(() -> notNull(EXPECTED_CODE));
    }

    @Test
    public void testIsEqualTo() { testHelper.testIsEqualTo(validated -> isEqualTo(validated, EXPECTED_CODE));}

    @Test
    public void testIsNotEqualTo() { testHelper.testIsNotEqualTo(validated -> isNotEqualTo(validated, EXPECTED_CODE));}

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
}