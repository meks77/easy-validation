package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.isEqualTo;
import static at.meks.validation.validations.common.CommonValidationsWithErrorCode.notNull;

public class CommonValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<Object> {

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
    public void testNotNull() {
        testHelper.testNotNull(() -> notNull(EXPECTED_CODE));
    }

    @Test
    public void testIsEqualTo() { testHelper.testIsEqualTo(validated -> isEqualTo(validated, EXPECTED_CODE));}
}