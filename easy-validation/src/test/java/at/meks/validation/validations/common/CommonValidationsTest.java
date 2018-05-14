package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.fest.assertions.api.Assertions.assertThat;

public class CommonValidationsTest extends AbstractValidationsTest<Object> {

    @Mock
    private CoreCommonValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return CommonValidations.class;
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
        Constructor<CommonValidations> constructor = CommonValidations.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testNotNull() {
        testHelper.testNotNull(CommonValidations::notNull);
    }

    @Test
    public void testIsEqualTo() { testHelper.testIsEqualTo(CommonValidations::isEqualTo);}
}