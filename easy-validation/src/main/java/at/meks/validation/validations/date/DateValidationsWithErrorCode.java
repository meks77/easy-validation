package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.validations.common.CommonValidationsWithErrorCode;

import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.function.Supplier;

/**
 * This class contains validations for dates with the support for an error code.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidationsWithErrorCode {

//    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
//    private static final CoreDateValidations VALIDATIONS = new CoreDateValidations();

    private DateValidationsWithErrorCode() {
    }


    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateAfter(ChronoLocalDate minDate, String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(minDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateAfter(Supplier<ChronoLocalDate> supplierMinDate,
                                                                           String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(supplierMinDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeAfter(ChronoLocalDateTime minDate,
                                                                                   String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(minDate, errorCode);
    }

    /**
     * Validates that the validated date is after another one.
     * @param supplierMinDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeAfter(
            Supplier<ChronoLocalDateTime> supplierMinDate, String errorCode) {
        return CommonValidationsWithErrorCode.isGreaterThan(supplierMinDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is after another one.
     * @param minDate   the validated date must be after this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeAfter(ZonedDateTime minDate, String errorCode) {
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
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBefore(ChronoLocalDate maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(maxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBefore(Supplier<ChronoLocalDate> supplierMaxDate,
                                                                            String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param maxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBefore(ChronoLocalDateTime maxDate,
                                                                                    String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(maxDate, errorCode);
    }

    /**
     * Validates that the validated date is before another one.
     * @param supplierMaxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBefore(
            Supplier<ChronoLocalDateTime> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isLessThan(supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated zoned date is before another one.
     * @param maxDate   the validated date must be before this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBefore(ZonedDateTime maxDate, String errorCode) {
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
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBetween(ChronoLocalDate minDate,
            ChronoLocalDate maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(minDate, maxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDate>> isLocalDateBetween(Supplier<ChronoLocalDate> supplierMinDate,
            Supplier<ChronoLocalDate> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(supplierMinDate, supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBetween(ChronoLocalDateTime minDate,
            ChronoLocalDateTime maxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(minDate, maxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param supplierMinDate   the validated date must be after or equal to this
     * @param supplierMaxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ChronoLocalDateTime>> isLocalDateTimeBetween(Supplier<ChronoLocalDateTime> supplierMinDate,
            Supplier<ChronoLocalDateTime> supplierMaxDate, String errorCode) {
        return CommonValidationsWithErrorCode.isBetween(supplierMinDate, supplierMaxDate, errorCode);
    }

    /**
     * Validates that the validated date is within a range of another dates.
     * @param minDate   the validated date must be after or equal to this
     * @param maxDate   the validated date must be before or equal to this
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new instance of validation
     */
    public static Validation<Comparable<ZonedDateTime>> isZonedDateTimeBetween(ZonedDateTime minDate,
            ZonedDateTime maxDate, String errorCode) {
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

}
