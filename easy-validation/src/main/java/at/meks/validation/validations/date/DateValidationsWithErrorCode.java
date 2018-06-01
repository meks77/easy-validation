package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.validations.common.CommonValidationsWithErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

/**
 * This class contains validations for dates with the support for an error code.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidationsWithErrorCode {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreDateValidations VALIDATIONS = new CoreDateValidations();

    private DateValidationsWithErrorCode() {
    }


    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateAfter(LocalDate minDate, String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(minDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateAfter(Supplier<LocalDate> supplierMinDate,
                                                                           String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(supplierMinDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeAfter(LocalDateTime minDate, String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(minDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeAfter(Supplier<LocalDateTime> supplierMinDate,
            String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(supplierMinDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeAfter(ZonedDateTime minDate, String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(minDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeAfter(Supplier<ZonedDateTime> supplierMinDate,
                                                                             String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(supplierMinDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateBefore(LocalDate maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(maxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateBefore(Supplier<LocalDate> supplierMaxDate,
                                                                            String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeBefore(LocalDateTime maxDate,
                                                                                    String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(maxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeBefore(
            Supplier<LocalDateTime> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param maxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeBefore(ZonedDateTime maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(maxDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBefore(Supplier<ZonedDateTime> supplierMaxDate,
            String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateBetween(LocalDate minDate, LocalDate maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(minDate, maxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateBetween(Supplier<LocalDate> supplierMinDate,
            Supplier<LocalDate> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(supplierMinDate, supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeBetween(LocalDateTime minDate, LocalDateTime maxDate,
            String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(minDate, maxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeBetween(Supplier<LocalDateTime> supplierMinDate,
            Supplier<LocalDateTime> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(supplierMinDate, supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeBetween(ZonedDateTime minDate, ZonedDateTime maxDate,
            String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(minDate, maxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBetween(Supplier<ZonedDateTime> supplierMinDate,
            Supplier<ZonedDateTime> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(supplierMinDate, supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is the first date of the year
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateFirstDayOfYear(String errorCode) {
        return VALIDATIONS.isLocalDateFirstDayOfYear(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the first date of the year
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeFirstDayOfYear(String errorCode) {
        return VALIDATIONS.isLocalDateTimeFirstDayOfYear(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the first date of the year
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeFirstDayOfYear(String errorCode) {
        return VALIDATIONS.isZonedDateTimeFirstDayOfYear(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateFirstDayOfMonth(String errorCode) {
        return VALIDATIONS.isLocalDateFirstDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeFirstDayOfMonth(String errorCode) {
        return VALIDATIONS.isLocalDateTimeFirstDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeFirstDayOfMonth(String errorCode) {
        return VALIDATIONS.isZonedDateTimeFirstDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage(), errorCode));
    }

    /**
     * Validates that the validated time is the start of the day. Only hour, minutes and seconds are considered.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeStartOfDay(String errorCode) {
        return VALIDATIONS.isLocalDateTimeStartOfDay(
                () -> withCode(MESSAGE_RESOLVER.getIsTimeStartOfDayMessage(), errorCode));
    }

    /**
     * Validates that the validated time is the start of the day. Only hour, minutes and seconds are considered.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeStartOfDay(String errorCode) {
        return VALIDATIONS.isZonedDateTimeStartOfDay(
                () -> withCode(MESSAGE_RESOLVER.getIsTimeStartOfDayMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateLastDayOfMonth(String errorCode) {
        return VALIDATIONS.isLocalDateLastDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeLastDayOfMonth(String errorCode) {
        return VALIDATIONS.isLocalDateTimeLastDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage(), errorCode));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeLastDayOfMonth(String errorCode) {
        return VALIDATIONS.isZonedDateTimeLastDayOfMonth(
                () -> withCode(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage(), errorCode));
    }
}
