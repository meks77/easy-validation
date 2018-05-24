package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.common.CommonValidations;

import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.function.Supplier;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidations {

//    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
//    private static final CoreDateValidations VALIDATIONS = new CoreDateValidations();

    private DateValidations() {
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateAfter(ChronoLocalDate minDate) {
        return CommonValidations.isGreaterThan(minDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateAfter(Supplier<ChronoLocalDate> supplierMinDate) {
        return CommonValidations.isGreaterThan(supplierMinDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeAfter(ChronoLocalDateTime minDate) {
        return CommonValidations.isGreaterThan(minDate);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeAfter(Supplier<ChronoLocalDateTime> supplierMinDate) {
        return CommonValidations.isGreaterThan(supplierMinDate);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param minDate   the validated date must be after this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeAfter(ZonedDateTime minDate) {
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
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBefore(ChronoLocalDate maxDate) {
        return CommonValidations.isLessThan(maxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBefore(Supplier<ChronoLocalDate> supplierMaxDate) {
        return CommonValidations.isLessThan(supplierMaxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBefore(ChronoLocalDateTime maxDate) {
        return CommonValidations.isLessThan(maxDate);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBefore(Supplier<ChronoLocalDateTime> supplierMaxDate) {
        return CommonValidations.isLessThan(supplierMaxDate);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param maxDate   the validated date must be before this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBefore(ZonedDateTime maxDate) {
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
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBetween(ChronoLocalDate minDate, ChronoLocalDate maxDate) {
        return CommonValidations.isBetween(minDate, maxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBetween(Supplier<ChronoLocalDate> supplierMinDate,
                                                               Supplier<ChronoLocalDate> supplierMaxDate) {
        return CommonValidations.isBetween(supplierMinDate, supplierMaxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBetween(ChronoLocalDateTime minDate,
                                                                       ChronoLocalDateTime maxDate) {
        return CommonValidations.isBetween(minDate, maxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBetween(Supplier<ChronoLocalDateTime> supplierMinDate,
                                                                       Supplier<ChronoLocalDateTime> supplierMaxDate) {
        return CommonValidations.isBetween(supplierMinDate, supplierMaxDate);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBetween(ZonedDateTime minDate, ZonedDateTime maxDate) {
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

}
