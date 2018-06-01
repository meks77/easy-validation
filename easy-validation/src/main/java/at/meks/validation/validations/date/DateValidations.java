package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.validations.common.CommonValidations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidations {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreDateValidations VALIDATIONS = new CoreDateValidations();

    private DateValidations() {
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateAfter(LocalDate minDate) {
        return CommonValidations.isGreaterThan(minDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateAfter(Supplier<LocalDate> supplierMinDate) {
        return CommonValidations.isGreaterThan(supplierMinDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeAfter(LocalDateTime minDate) {
        return CommonValidations.isGreaterThan(minDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeAfter(Supplier<LocalDateTime> supplierMinDate) {
        return CommonValidations.isGreaterThan(supplierMinDate);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeAfter(ZonedDateTime minDate) {
        return CommonValidations.isGreaterThan(minDate);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeAfter(Supplier<ZonedDateTime> supplierMinDate) {
        return CommonValidations.isGreaterThan(supplierMinDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateBefore(LocalDate maxDate) {
        return CommonValidations.isLessThan(maxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateBefore(Supplier<LocalDate> supplierMaxDate) {
        return CommonValidations.isLessThan(supplierMaxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeBefore(LocalDateTime maxDate) {
        return CommonValidations.isLessThan(maxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeBefore(Supplier<LocalDateTime> supplierMaxDate) {
        return CommonValidations.isLessThan(supplierMaxDate);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param maxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeBefore(ZonedDateTime maxDate) {
        return CommonValidations.isLessThan(maxDate);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBefore(Supplier<ZonedDateTime> supplierMaxDate) {
        return CommonValidations.isLessThan(supplierMaxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateBetween(LocalDate minDate, LocalDate maxDate) {
        return CommonValidations.isBetween(minDate, maxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDate>> isLocalDateBetween(Supplier<LocalDate> supplierMinDate,
                                                               Supplier<LocalDate> supplierMaxDate) {
        return CommonValidations.isBetween(supplierMinDate, supplierMaxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeBetween(LocalDateTime minDate,
                                                                       LocalDateTime maxDate) {
        return CommonValidations.isBetween(minDate, maxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<LocalDateTime>> isLocalDateTimeBetween(Supplier<LocalDateTime> supplierMinDate,
                                                                       Supplier<LocalDateTime> supplierMaxDate) {
        return CommonValidations.isBetween(supplierMinDate, supplierMaxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeBetween(ZonedDateTime minDate, ZonedDateTime maxDate) {
        return CommonValidations.isBetween(minDate, maxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBetween(Supplier<ZonedDateTime> supplierMinDate,
                                                                 Supplier<ZonedDateTime> supplierMaxDate) {
        return CommonValidations.isBetween(supplierMinDate, supplierMaxDate);
    }

    /**
     * Validates that the validated date is the first day of the year
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateFirstDayOfYear() {
        return VALIDATIONS.isLocalDateFirstDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the first day of the year
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeFirstDayOfYear() {
        return VALIDATIONS.isLocalDateTimeFirstDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the first day of the year
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeFirstDayOfYear() {
        return VALIDATIONS.isZonedDateTimeFirstDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the last day of the year
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateLastDayOfYear() {
        return VALIDATIONS.isLocalDateLastDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateLastDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the last day of the year
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeLastDayOfYear() {
        return VALIDATIONS.isLocalDateTimeLastDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateLastDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the last day of the year
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeLastDayOfYear() {
        return VALIDATIONS.isZonedDateTimeLastDayOfYear(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateLastDayOfYearMessage()));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateFirstDayOfMonth() {
        return VALIDATIONS.isLocalDateFirstDayOfMonth(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage()));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeFirstDayOfMonth() {
        return VALIDATIONS.isLocalDateTimeFirstDayOfMonth(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage()));
    }

    /**
     * Validates that the validated date is the first date of the month
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeFirstDayOfMonth() {
        return VALIDATIONS.isZonedDateTimeFirstDayOfMonth(
                () -> withMessage(MESSAGE_RESOLVER.getIsDateFirstDayOfMonthMessage()));
    }

    /**
     * Validates that the validated time is the start of the day. Only hour, minutes and seconds are considered.
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeStartOfDay() {
        return VALIDATIONS.isLocalDateTimeStartOfDay(() -> withMessage(MESSAGE_RESOLVER.getIsTimeStartOfDayMessage()));
    }

    /**
     * Validates that the validated time is the start of the day. Only hour, minutes and seconds are considered.
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeStartOfDay() {
        return VALIDATIONS.isZonedDateTimeStartOfDay(() -> withMessage(MESSAGE_RESOLVER.getIsTimeStartOfDayMessage()));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @return  new instance of validation
     */
    public static Validation<LocalDate> isLocalDateLastDayOfMonth() {
        return VALIDATIONS.isLocalDateLastDayOfMonth(() -> withMessage(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage()));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @return  new instance of validation
     */
    public static Validation<LocalDateTime> isLocalDateTimeLastDayOfMonth() {
        return VALIDATIONS.isLocalDateTimeLastDayOfMonth(
                () -> withMessage(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage()));
    }

    /**
     * Validates that the validated date is the last date of the month.
     * @return  new instance of validation
     */
    public static Validation<ZonedDateTime> isZonedDateTimeLastDayOfMonth() {
        return VALIDATIONS.isZonedDateTimeLastDayOfMonth(
                () -> withMessage(MESSAGE_RESOLVER.getIsLastDayOfMonthMessage()));
    }
}
