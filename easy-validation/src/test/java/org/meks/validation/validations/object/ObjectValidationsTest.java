package org.meks.validation.validations.object;

import org.junit.Before;
import org.junit.Test;
import org.meks.validation.validations.AbstractValidationsTest;
import org.mockito.Mock;

public class ObjectValidationsTest extends AbstractValidationsTest<Object> {

    @Mock
    private CoreObjectValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return ObjectValidations.class;
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
        testHelper.testNotNull(ObjectValidations::notNull);
    }
}