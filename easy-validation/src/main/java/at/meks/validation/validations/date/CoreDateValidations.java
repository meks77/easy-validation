package at.meks.validation.validations.date;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
class CoreDateValidations {


    Validation<LocalDate> isLocalDateFirstDayOfYear(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1 && date.getMonth() == Month.JANUARY,
                errorDescription);
    }

    Validation<LocalDateTime> isLocalDateTimeFirstDayOfYear(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1 && date.getMonth() == Month.JANUARY,
                errorDescription);
    }

    Validation<ZonedDateTime> isZonedDateTimeFirstDayOfYear(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1 && date.getMonth() == Month.JANUARY,
                errorDescription);
    }

    Validation<LocalDate> isLocalDateFirstDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1, errorDescription);
    }

    Validation<LocalDateTime> isLocalDateTimeFirstDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1, errorDescription);
    }

    Validation<ZonedDateTime> isZonedDateTimeFirstDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == 1, errorDescription);
    }

    Validation<LocalDateTime> isLocalDateTimeStartOfDay(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getHour() == 0 && date.getMinute() == 0 && date.getSecond() == 0,
                errorDescription);
    }

    Validation<ZonedDateTime> isZonedDateTimeStartOfDay(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getHour() == 0 && date.getMinute() == 0 && date.getSecond() == 0,
                errorDescription);
    }

    Validation<LocalDate> isLocalDateLastDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == date.lengthOfMonth(), errorDescription);
    }

    Validation<LocalDateTime> isLocalDateTimeLastDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == getLenghtOfMonth(date), errorDescription);
    }

    private int getLenghtOfMonth(LocalDateTime date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()).lengthOfMonth();
    }

    Validation<ZonedDateTime> isZonedDateTimeLastDayOfMonth(Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> date.getDayOfMonth() == getLenghtOfMonth(date), errorDescription);
    }

    private int getLenghtOfMonth(ZonedDateTime date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()).lengthOfMonth();
    }

    //TODO lastDayOfYear
    //TODO startOfHour
    //TODO isWeekDay
    //TODO isFirstDayOfWeek
    //TODO isLastDayOfWeek
    //TODO ???
}
