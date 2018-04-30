package org.meks.validation.validations.object;

import org.junit.Test;
import org.meks.validation.result.ValidationResult;
import org.meks.validation.validations.AbstractCoreValidationsTest;


public class CoreObjectValidationsTest extends AbstractCoreValidationsTest {

    private CoreObjectValidations validations = new CoreObjectValidations();

    @Test
    public void givenNotNullWhenNotNullReturnsValidResult() {
        assertValidResult(validations.notNull(errorDescription).test("a"));
    }

    @Test
    public void givenNullWhenNotNullReturnsErrorResult() {
        ValidationResult result = validations.notNull(errorDescription).test(null);
        assertErrorResult(result);
    }

}