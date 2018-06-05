package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractValidationsTest;
import at.meks.validation.validations.VerifierWithErrorDescSupplierCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static org.mockito.ArgumentMatchers.same;
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

    void testIsLocalDateTimeStartOfHour(Supplier<Validation<LocalDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isLocalDateTimeStartOfHour(anySupplier());
        testIsDateStartOfHour(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isLocalDateTimeStartOfHour(errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeStartOfHour(Supplier<Validation<ZonedDateTime>> methodInvoker) {
        willReturn(test.getExpectedValidation()).given(coreValidations).isZonedDateTimeStartOfHour(anySupplier());
        testIsDateStartOfHour(methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations).isZonedDateTimeStartOfHour(errorDescCaptor.capture()));
    }

    private <T> void testIsDateStartOfHour(Supplier<Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsDateTimeStartOfHourMessage()).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.get();
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

    void testIsLocalDateDayOfWeek(Function<DayOfWeek, Validation<LocalDate>> methodInvoker) {
        DayOfWeek dayOfWeek = DayOfWeek.THURSDAY;
        willReturn(test.getExpectedValidation()).given(coreValidations)
                .isLocalDateDayOfWeek(same(dayOfWeek), anySupplier());
        testIsDateDayOfWeek(dayOfWeek, methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations)
                        .isLocalDateDayOfWeek(same(dayOfWeek), errorDescCaptor.capture()));
    }

    void testIsLocalDateTimeDayOfWeek(Function<DayOfWeek, Validation<LocalDateTime>> methodInvoker) {
        DayOfWeek dayOfWeek = DayOfWeek.THURSDAY;
        willReturn(test.getExpectedValidation()).given(coreValidations)
                .isLocalDateTimeDayOfWeek(same(dayOfWeek), anySupplier());
        testIsDateDayOfWeek(dayOfWeek, methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations)
                        .isLocalDateTimeDayOfWeek(same(dayOfWeek), errorDescCaptor.capture()));
    }

    void testIsZonedDateTimeDayOfWeek(Function<DayOfWeek, Validation<ZonedDateTime>> methodInvoker) {
        DayOfWeek dayOfWeek = DayOfWeek.THURSDAY;
        willReturn(test.getExpectedValidation()).given(coreValidations)
                .isZonedDateTimeDayOfWeek(same(dayOfWeek), anySupplier());
        testIsDateDayOfWeek(dayOfWeek, methodInvoker,
                errorDescCaptor -> BDDMockito.verify(coreValidations)
                        .isZonedDateTimeDayOfWeek(same(dayOfWeek), errorDescCaptor.capture()));
    }

    private <T> void testIsDateDayOfWeek(DayOfWeek dayOfWeek, Function<DayOfWeek, Validation<T>> methodInvoker,
            VerifierWithErrorDescSupplierCaptor verifier) {
        when(test.getMessageResolver().getIsDateDayOfWeekMessage(dayOfWeek)).thenReturn(test.getExpectedMessage());
        Validation<T> validation = methodInvoker.apply(dayOfWeek);
        test.doAssertionsAndVerificationsWithSupplier(validation, verifier);
    }

}
