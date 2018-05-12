package at.meks.validation.validations.common;

import at.meks.validation.TestUtils;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommonsValidationsTestHelper {

    private final CoreCommonValidations coreValidations;
    private final AbstractValidationsTest<Object> test;

    CommonsValidationsTestHelper(CoreCommonValidations coreValidationsMock, AbstractValidationsTest<Object> testInstance) {
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

    void testIsEqualTo(Function<Object, Validation<Object>> methodInvoker) {
        Object compareTo = "whatever";
        when(test.getMessageResolver().getIsEqualToMessage(compareTo)).thenReturn(test.getExpectedMessage());
        when(coreValidations.isEqualTo(TestUtils.anySupplier(), TestUtils.anySupplier())).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Object>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).isEqualTo(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }
}
