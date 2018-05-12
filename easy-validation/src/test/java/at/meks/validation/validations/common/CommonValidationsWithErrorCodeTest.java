package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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

    private ObjectValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new ObjectValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testNotNull() {
        testHelper.testNotNull(() -> CommonValidationsWithErrorCode.notNull(EXPECTED_CODE));
    }
}