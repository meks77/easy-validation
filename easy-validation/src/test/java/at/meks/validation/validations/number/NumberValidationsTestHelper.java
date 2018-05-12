package at.meks.validation.validations.number;

import at.meks.validation.TestUtils;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class NumberValidationsTestHelper {

    private final CoreNumberValidations coreValidations;
    private final AbstractValidationsTest<Number> test;

    NumberValidationsTestHelper(CoreNumberValidations coreValidationsMock, AbstractValidationsTest<Number> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testIsLessThan(Function<Number, Validation<Number>> methodeInvoker) {
        Number compareTo = 10.1;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsNumberLessThanMessage(compareTo);
        doReturn(test.getExpectedValidation()).when(coreValidations).isLessThan(anySupplier(), anySupplier());

        Validation<Number> validation = methodeInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Number>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isLessThan(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsGreaterThan(Function<Number, Validation<Number>> methodeInvoker) {
        Number compareTo = 10.1;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsNumberGreaterThanMessage(compareTo);
        doReturn(test.getExpectedValidation()).when(coreValidations).isGreaterThan(anySupplier(), anySupplier());

        Validation<Number> validation = methodeInvoker.apply(compareTo);
        ArgumentCaptor<Supplier<Number>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isGreaterThan(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(compareTo, valueCaptor);
    }

    void testIsBetween(IsBetween methodeInvoker) {
        Number min = 10.1;
        Number max = 12.0;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getNumberIsBetweenMessage(min, max);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .isBetween(anySupplier(), anySupplier(), anySupplier());

        Validation<Number> validation = methodeInvoker.isBetween(min, max);
        ArgumentCaptor<Supplier<Number>> minCaptor = getSupplierCaptor();
        ArgumentCaptor<Supplier<Number>> maxCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isBetween(minCaptor.capture(), maxCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(min, minCaptor);
        assertSupplierValue(max, maxCaptor);
    }


    void testIsInt(Supplier<Validation<Number>> methodeInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsIntMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isInt(any(ErrorDescription.class));

        Validation<Number> validation = methodeInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isInt(errorDescCaptor.capture()));
    }


    void testIsByte(Supplier<Validation<Number>> methodeInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsByteMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isByte(any(ErrorDescription.class));

        Validation<Number> validation = methodeInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isByte(errorDescCaptor.capture()));
    }

    void testIsShort(Supplier<Validation<Number>> methodeInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsShortMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isShort(any(ErrorDescription.class));

        Validation<Number> validation = methodeInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isShort(errorDescCaptor.capture()));
    }
}
