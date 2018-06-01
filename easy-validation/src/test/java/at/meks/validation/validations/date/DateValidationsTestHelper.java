package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractValidationsTest;
import at.meks.validation.validations.VerifierWithErrorDescSupplierCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.when;

/**
 * There was a strange behavior when mocking some methods. in the line of up to 4 different Mockito.when statements a
 * Mockito error was reported that the verify call is missing or not working. I spent tons of hours on it. Finally it
 * was solved when I was using BddMockito.willReturn instead.
 */
class DateValidationsTestHelper {

    private final CoreDateValidations coreValidations;
    private final AbstractValidationsTest<LocalDateTime> test;

    DateValidationsTestHelper(CoreDateValidations coreValidationsMock, AbstractValidationsTest<LocalDateTime> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testIsLocalDateFirstDayOfYear(Supplier<Validation<LocalDate>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateFirstDayOfYear(anySupplier());
        testIsDateTimeFirstDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isLocalDateFirstDayOfYear(errorDescCaptor.capture()));
    }

    void testIsLocalDateTimeFirstDayOfYear(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeFirstDayOfYear(anySupplier());
        testIsDateTimeFirstDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isLocalDateTimeFirstDayOfYear(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeFirstDayOfYear(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        when(coreValidations.isZonedDateTimeFirstDayOfYear(anySupplier())).thenReturn(test.getExpectedValidation());
        testIsDateTimeFirstDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isZonedDateTimeFirstDayOfYear(errorDescCaptor.capture()));
    }

    private <T> void testIsDateTimeFirstDayOfYear(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsDateFirstDayOfYearMessage()).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

    void testIsLocalDateLastDayOfYear(Supplier<Validation<LocalDate>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateLastDayOfYear(anySupplier());
        testIsDateTimeLastDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isLocalDateLastDayOfYear(errorDescCaptor.capture()));
    }

    void testIsLocalDateTimeLastDayOfYear(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeLastDayOfYear(anySupplier());
        testIsDateTimeLastDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isLocalDateTimeLastDayOfYear(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeLastDayOfYear(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        when(coreValidations.isZonedDateTimeLastDayOfYear(anySupplier())).thenReturn(test.getExpectedValidation());
        testIsDateTimeLastDayOfYear(methodInvoker,
                errorDescCaptor ->  verify(coreValidations).isZonedDateTimeLastDayOfYear(errorDescCaptor.capture()));
    }

    private <T> void testIsDateTimeLastDayOfYear(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsDateLastDayOfYearMessage()).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

    void testIsLocalDateFirstDayOfMonth(Supplier<Validation<LocalDate>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateFirstDayOfMonth(anySupplier());
        testIsDateFirstDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateFirstDayOfMonth(errorDescCaptor.capture()));
    }

    void testIsLocalDateTimeFirstDayOfMonth(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeFirstDayOfMonth(anySupplier());
        testIsDateFirstDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateTimeFirstDayOfMonth(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeFirstDayOfMonth(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isZonedDateTimeFirstDayOfMonth(anySupplier());
        testIsDateFirstDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isZonedDateTimeFirstDayOfMonth(errorDescCaptor.capture()));
    }

    private <T> void testIsDateFirstDayOfMonth(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsDateFirstDayOfMonthMessage()).thenReturn(test.getExpectedMessage());

        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

    void testIsLocalDateTimeStartOfDay(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeStartOfDay(anySupplier());
        testIsDateTimeStartOfDay(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateTimeStartOfDay(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeStartOfDay(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isZonedDateTimeStartOfDay(anySupplier());
        testIsDateTimeStartOfDay(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isZonedDateTimeStartOfDay(errorDescCaptor.capture()));
    }

    private <T> void testIsDateTimeStartOfDay(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsTimeStartOfDayMessage()).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

    void testIsLocalDateLastDayOfMonth(Supplier<Validation<LocalDate>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateLastDayOfMonth(anySupplier());
        testIsDateLastDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateLastDayOfMonth(errorDescCaptor.capture()));
    }

    void testIsLocalDateTimeLastDayOfMonth(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeLastDayOfMonth(anySupplier());
        testIsDateLastDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateTimeLastDayOfMonth(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeLastDayOfMonth(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isZonedDateTimeLastDayOfMonth(anySupplier());
        testIsDateLastDayOfMonth(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isZonedDateTimeLastDayOfMonth(errorDescCaptor.capture()));
    }

    private <T> void testIsDateLastDayOfMonth(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsLastDayOfMonthMessage()).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }
}
