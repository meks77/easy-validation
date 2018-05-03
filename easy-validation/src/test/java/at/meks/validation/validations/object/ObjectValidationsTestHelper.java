package at.meks.validation.validations.object;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ObjectValidationsTestHelper {

    private final CoreObjectValidations coreValidations;
    private final AbstractValidationsTest<Object> test;

    ObjectValidationsTestHelper(CoreObjectValidations coreValidationsMock, AbstractValidationsTest<Object> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testNotNull(Supplier<Validation<Object>> methodInvoker) {
        when(test.getMessageResolver().getNotNullMessage()).thenReturn(test.getExpectedMessage());
        when(coreValidations.notNull(Mockito.any(ErrorDescription.class))).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).notNull(errorDescCaptor.capture()));
    }
}
