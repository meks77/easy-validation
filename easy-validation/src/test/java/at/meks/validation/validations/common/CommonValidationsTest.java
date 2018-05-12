package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    public void testNotNull() {
        testHelper.testNotNull(CommonValidations::notNull);
    }

    @Test
    public void testIsEqualTo() { testHelper.testIsEqualTo(CommonValidations::isEqualTo);}
}