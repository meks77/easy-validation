package org.meks.validation.validations.object;

import org.junit.Before;
import org.junit.Test;
import org.meks.validation.validations.AbstractCodeValidationsTest;
import org.mockito.Mock;

public class ObjectValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<Object> {

    @Mock
    private CoreObjectValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return ObjectValidationsWithErrorCode.class;
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
        testHelper.testNotNull(() -> ObjectValidationsWithErrorCode.notNull(EXPECTED_CODE));
    }
}