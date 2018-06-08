package at.meks.validation.validations.common;

import at.meks.validation.TestUtils;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import at.meks.validation.validations.number.IsBetween;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommonsValidationsTestHelper {

    private final CoreCommonValidations coreValidations;
    private final AbstractValidationsTest test;

    CommonsValidationsTestHelper(CoreCommonValidations coreValidationsMock, AbstractValidationsTest testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testNotNull(Supplier<Validation<Object>> methodInvoker) {
        when(test.getMessageResolver().getNotNullMessage()).thenReturn(test.getExpectedMessage());
        //noinspection unchecked
        when(coreValidations.notNull(Mockito.any(ErrorDescription.class))).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.get();
        //noinspection unchecked
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).notNull(errorDescCaptor.capture()));
    }

    void testIsNull(Supplier<Validation<Object>> methodInvoker) {
        when(test.getMessageResolver().getIsNullMessage()).thenReturn(test.getExpectedMessage());
        //noinspection unchecked
        when(coreValidations.isNull(Mockito.any(ErrorDescription.class))).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.get();
        //noinspection unchecked
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).isNull(errorDescCaptor.capture()));
    }

    void testIsEqualTo(Function<Object, Validation<Object>> methodInvoker) {
        Object compareTo = "whatever";
        when(test.getMessageResolver().getIsEqualToMessage(compareTo)).thenReturn(test.getExpectedMessage());
        //noinspection unchecked
        when(coreValidations.isEqualTo(TestUtils.anySupplier(), TestUtils.anySupplier())).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Object>> valueCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).isEqualTo(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsNotEqualTo(Function<Object, Validation<Object>> methodInvoker) {
        Object compareTo = "whatever";
        when(test.getMessageResolver().getIsNotEqualToMessage(compareTo)).thenReturn(test.getExpectedMessage());
        //noinspection unchecked
        when(coreValidations.isNotEqualTo(TestUtils.anySupplier(), TestUtils.anySupplier())).thenReturn(test.getExpectedValidation());
        Validation<Object> validation = methodInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Object>> valueCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).isNotEqualTo(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsLessThan(Function<Number, Validation<Comparable<Number>>> methodInvoker) {
        Number compareTo = 10.1;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsLessThanMessage(compareTo);
        doReturn(test.getExpectedValidation()).when(coreValidations).isLessThan(anySupplier(), anySupplier());

        Validation<Comparable<Number>> validation = methodInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Number>> valueCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isLessThan(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsGreaterThan(Function<Number, Validation<Comparable<Number>>> methodInvoker) {
        Number compareTo = 10.1;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsGreaterThanMessage(compareTo);
        doReturn(test.getExpectedValidation()).when(coreValidations).isGreaterThan(anySupplier(), anySupplier());

        Validation<Comparable<Number>> validation = methodInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Number>> valueCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isGreaterThan(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsBetween(IsBetween methodInvoker) {
        Number min = 10.1;
        Number max = 12.0;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsBetweenMessage(min, max);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .isBetween(anySupplier(), anySupplier(), anySupplier());

        Validation<Comparable<Number>> validation = methodInvoker.isBetween(min, max);
        ArgumentCaptor<Supplier<Number>> minCaptor = getSupplierCaptor();
        ArgumentCaptor<Supplier<Number>> maxCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isBetween(minCaptor.capture(), maxCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(min, minCaptor);
        assertSupplierValue(max, maxCaptor);
    }

    void testIsTrue(Supplier<Validation<Boolean>> methodInvoker) {
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsTrueMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isTrue(anySupplier());

        Validation<Boolean> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, errorDescCaptor -> verify(coreValidations)
                .isTrue(errorDescCaptor.capture()));
    }

    void testIsFalse(Supplier<Validation<Boolean>> methodInvoker) {
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsFalseMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isFalse(anySupplier());

        Validation<Boolean> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, errorDescCaptor -> verify(coreValidations)
                .isFalse(errorDescCaptor.capture()));
    }
}
