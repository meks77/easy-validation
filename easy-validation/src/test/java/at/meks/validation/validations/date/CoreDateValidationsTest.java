package at.meks.validation.validations.date;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;

import static java.time.LocalDateTime.of;

public class CoreDateValidationsTest extends AbstractCoreValidationsTest {

    private final CoreDateValidations validations = new CoreDateValidations();

    @Test
    public void givenFirstDayOfYearWhenIsLocalDateStartOfYearReturnsOk() {
        assertValidResult(validations.isLocalDateFirstDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 1, 1)));
    }

    @Test
    public void given2ndJanuaryWhenIsLocalDateFirstDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateFirstDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 1, 2)));
    }

    @Test
    public void given1stFebruaryWhenIsLocalDateFirstDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateFirstDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 2, 1)));
    }

    @Test
    public void given2ndFebruaryWhenIsLocalDateFirstDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateFirstDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 2, 2)));
    }

    @Test
    public void givenFirstDayOfYearWhenIsLocalDateTimeIsStartOfYearReturnsOk() {
        assertValidResult(validations.isLocalDateTimeFirstDayOfYear(() -> errorDescription)
                .test(of(2010, 1, 1, 5 ,1)));
    }

    @Test
    public void given2ndJanuaryWhenIsLocalDateTimeStartOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeFirstDayOfYear(() -> errorDescription)
                .test(of(2010, 1, 2, 5, 1)));
    }

    @Test
    public void given2ndFebruaryWhenIsLocalDateTimeStartOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeFirstDayOfYear(() -> errorDescription)
                .test(of(2010, 2, 2, 5, 1)));
    }

    @Test
    public void given1stFebruaryWhenIsLocalDateTimeStartOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeFirstDayOfYear(() -> errorDescription)
                .test(of(2010, 2, 1, 5, 1)));
    }

    @Test
    public void givenFirstDayOfYearWhenIsZonedDateTimeStartOfYearReturnsOk() {
        assertValidResult(validations.isZonedDateTimeFirstDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 1, 1, 5, 2), ZoneId.systemDefault())));
    }

    @Test
    public void given2ndJanuaryWhenIsZonedDateTimeIsStartOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeFirstDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 1, 2, 5, 1), ZoneId.systemDefault())));
    }

    @Test
    public void given1stFebruaryWhenIsZonedDateTimeIsStartOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeFirstDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 1, 5, 1), ZoneId.systemDefault())));
    }

    @Test
    public void given2ndFebruaryWhenIsZonedDateTimeIsStartOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeFirstDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 5, 1), ZoneId.systemDefault())));
    }

    @Test
    public void given31DecemberWhenIsLocalDateLastDayOfYearReturnsOk() {
        assertValidResult(validations.isLocalDateLastDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 12, 31)));
    }

    @Test
    public void given30DecemberWhenIsLocalDateLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateLastDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 12, 30)));
    }

    @Test
    public void given31OctoberWhenIsLocalDateLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateLastDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 10, 31)));
    }

    @Test
    public void given30NovemberWhenIsLocalDateLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateLastDayOfYear(() -> errorDescription).test(LocalDate.of(2010, 11, 30)));
    }

    @Test
    public void given31DecemberWhenIsLocalDateTimeLastDayOfYearReturnsOk() {
        assertValidResult(validations.isLocalDateTimeLastDayOfYear(() -> errorDescription)
                .test(of(2010, 12, 31, 1, 2)));
    }

    @Test
    public void given30DecemberWhenIsLocalDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeLastDayOfYear(() -> errorDescription)
                .test(of(2010, 12, 30, 1, 2)));
    }

    @Test
    public void given31OctoberWhenIsLocalDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeLastDayOfYear(() -> errorDescription)
                .test(of(2010, 10, 31, 1, 2)));
    }

    @Test
    public void given30NovemberWhenIsLocalDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isLocalDateTimeLastDayOfYear(() -> errorDescription)
                .test(of(2010, 11, 30, 1, 2)));
    }

    @Test
    public void given31DecemberWhenIsZonedDateTimeLastDayOfYearReturnsOk() {
        assertValidResult(validations.isZonedDateTimeLastDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 12, 31, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void given30DecemberWhenIsZonedDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeLastDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 12, 30, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void given31OctoberWhenIsZonedDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeLastDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 10, 31, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void given30NovemberWhenIsZonedDateTimeLastDayOfYearReturnsError() {
        assertErrorResult(validations.isZonedDateTimeLastDayOfYear(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 11, 30, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void givenFirstDayOfMonthWhenIsLocalDateFirstDayOfMonthReturnsOk() {
        assertValidResult(validations.isLocalDateFirstDayOfMonth(() -> errorDescription).test(LocalDate.of(2010, 2, 1)));
    }

    @Test
    public void givenSecondDayOfMonthWhenIsLocalDateFirstDayOfMonthReturnsOk() {
        assertErrorResult(validations.isLocalDateFirstDayOfMonth(() -> errorDescription).test(LocalDate.of(2010, 2, 2)));
    }

    @Test
    public void givenFirstDayOfMonthWhenIsLocalDateTimeFirstDayOfMonthReturnsOk() {
        assertValidResult(validations.isLocalDateTimeFirstDayOfMonth(() -> errorDescription)
                .test(of(2010, 2, 1, 5, 4)));
    }

    @Test
    public void givenSecondDayOfMonthWhenIsLocalDateTimeFirstDayOfMonthReturnsOk() {
        assertErrorResult(validations.isLocalDateTimeFirstDayOfMonth(() -> errorDescription)
                .test(of(2010, 2, 2, 5, 4)));
    }

    @Test
    public void givenFirstDayOfMonthWhenIsZonedDateTimeFirstDayOfMonthReturnsOk() {
        assertValidResult(validations.isZonedDateTimeFirstDayOfMonth(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 1, 5, 4), ZoneId.systemDefault())));
    }

    @Test
    public void givenSecondDayOfMonthWhenIsZonedDateTimeFirstDayOfMonthReturnsOk() {
        assertErrorResult(validations.isZonedDateTimeFirstDayOfMonth(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 5, 4), ZoneId.systemDefault())));
    }

    @Test
    public void givenStartOfDayWhenIsLocalDateTimeStartOfDayReturnsOk() {
        assertValidResult(validations.isLocalDateTimeStartOfDay(() -> errorDescription)
                .test(of(2010, 2, 2, 0, 0)));
    }

    @Test
    public void givenHourOneWhenIsLocalDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isLocalDateTimeStartOfDay(() -> errorDescription)
                .test(of(2010, 2, 2, 1, 0, 0)));
    }

    @Test
    public void givenMinuteOneWhenIsLocalDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isLocalDateTimeStartOfDay(() -> errorDescription)
                .test(of(2010, 2, 2, 0, 1, 0)));
    }

    @Test
    public void givenSecondOneWhenIsLocalDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isLocalDateTimeStartOfDay(() -> errorDescription)
                .test(of(2010, 2, 2, 0, 0, 1)));
    }

    @Test
    public void givenNanoSecond1WhenIsLocalDateTimeStartOfDayReturnsOk() {
        assertValidResult(validations.isLocalDateTimeStartOfDay(() -> errorDescription)
                .test(of(2010, 2, 2, 0, 0, 0, 1)));
    }

    @Test
    public void givenStartOfDayWhenIsZonedDateTimeStartOfDayReturnsOk() {
        assertValidResult(validations.isZonedDateTimeStartOfDay(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 0, 0), ZoneId.systemDefault())));
    }

    @Test
    public void givenSecondOneWhenIsZonedDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isZonedDateTimeStartOfDay(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 0, 0, 1), ZoneId.systemDefault())));
    }

    @Test
    public void givenHourOneWhenIsZonedDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isZonedDateTimeStartOfDay(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 1, 0, 0), ZoneId.systemDefault())));
    }

    @Test
    public void givenMinuteOneWhenIsZonedDateTimeStartOfDayReturnsOk() {
        assertErrorResult(validations.isZonedDateTimeStartOfDay(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 0, 1, 0), ZoneId.systemDefault())));
    }

    @Test
    public void givenNanoSecond1WhenIsZonedDateTimeStartOfDayReturnsOk() {
        assertValidResult(validations.isZonedDateTimeStartOfDay(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 2, 2, 0, 0, 0, 1), ZoneId.systemDefault())));
    }

    @Test
    public void givenLastDayOfJanuaryWhenIsLocalDateLastDayOfMonthReturnsOk() {
        assertValidResult(validations.isLocalDateLastDayOfMonth(() -> errorDescription).test(LocalDate.of(2010, 1, 31)));
    }

    @Test
    public void givenLastDayOfFebruaryWhenIsLocalDateLastDayOfMonthReturnsOk() {
        assertValidResult(validations.isLocalDateLastDayOfMonth(() -> errorDescription).test(LocalDate.of(2000, 2, 29)));
    }

    @Test
    public void given30thOfJanuaryWhenIsLocalDateLastDayOfMonthReturnsError() {
        assertErrorResult(validations.isLocalDateLastDayOfMonth(() -> errorDescription).test(LocalDate.of(2010, 1, 30)));
    }

    @Test
    public void givenLastDayOfJanuaryWhenIsLocalDateTimeLastDayOfMonthReturnsOk() {
        assertValidResult(validations.isLocalDateTimeLastDayOfMonth(() -> errorDescription)
                .test(of(2010, 1, 31, 5, 2)));
    }

    @Test
    public void given30thOfJanuaryWhenIsLocalDateTimeLastDayOfMonthReturnsError() {
        assertErrorResult(validations.isLocalDateTimeLastDayOfMonth(() -> errorDescription)
                .test(of(2010, 1, 30, 5, 2)));
    }

    @Test
    public void givenLastDayOfJanuaryWhenIsZonedDateTimeLastDayOfMonthReturnsOk() {
        assertValidResult(validations.isZonedDateTimeLastDayOfMonth(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 1, 31, 5, 2), ZoneId.systemDefault())));
    }

    @Test
    public void given30thOfJanuaryWhenIsZonedDateTimeLastDayOfMonthReturnsError() {
        assertErrorResult(validations.isZonedDateTimeLastDayOfMonth(() -> errorDescription)
                .test(ZonedDateTime.of(of(2010, 1, 30, 5, 2), ZoneId.systemDefault())));
    }

    @Test
    public void givenMinute0Second0WhenIsLocalDateStartOfHourReturnsOk() {
        assertValidResult(validations.isLocalDateTimeStartOfHour(() -> errorDescription).test(of(2018, 1, 5, 0, 0, 0)));
    }

    @Test
    public void givenMinute0Second1WhenIsLocalDateStartOfHourReturnsError() {
        assertErrorResult(validations.isLocalDateTimeStartOfHour(() -> errorDescription).test(of(2018, 1, 5, 0, 0, 1)));
    }

    @Test
    public void givenMinute1Second0WhenIsLocalDateStartOfHourReturnsError() {
        assertErrorResult(validations.isLocalDateTimeStartOfHour(() -> errorDescription).test(of(2018, 1, 5, 0, 1, 0)));
    }

    @Test
    public void givenMinute1Second1WhenIsLocalDateStartOfHourReturnsError() {
        assertErrorResult(validations.isLocalDateTimeStartOfHour(() -> errorDescription).test(of(2018, 1, 5, 0, 1, 1)));
    }

    @Test
    public void givenMinute0Second0WhenIsZonedDateStartOfHourReturnsOk() {
        assertValidResult(validations.isZonedDateTimeStartOfHour(() -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 1, 5, 0, 0, 0), ZoneId.systemDefault())));
    }

    @Test
    public void givenMinute0Second1WhenIsZonedDateStartOfHourReturnsError() {
        assertErrorResult(validations.isZonedDateTimeStartOfHour(() -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 1, 5, 0, 0, 1), ZoneId.systemDefault())));
    }

    @Test
    public void givenMinute1Second0WhenIsZonedDateStartOfHourReturnsError() {
        assertErrorResult(validations.isZonedDateTimeStartOfHour(() -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 1, 5, 0, 1, 0), ZoneId.systemDefault())));
    }

    @Test
    public void givenMinute1Second1WhenIsZonedDateStartOfHourReturnsError() {
        assertErrorResult(validations.isZonedDateTimeStartOfHour(() -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 1, 5, 0, 1, 1), ZoneId.systemDefault())));
    }

    @Test
    public void givenMondayWhenIsLocalDateDayOfWeekMondayReturnsOk() {
        assertValidResult(validations.isLocalDateDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(LocalDate.of(2018, 6, 4)));
    }

    @Test
    public void givenTuesdayWhenIsLocalDateDayOfWeekMondayReturnsError() {
        assertErrorResult(validations.isLocalDateDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(LocalDate.of(2018, 6, 5)));
    }

    @Test
    public void givenTuesdayWhenIsLocalDateDayOfWeekTuesdayReturnsOk() {
        assertValidResult(validations.isLocalDateDayOfWeek(DayOfWeek.TUESDAY, () -> errorDescription)
                .test(LocalDate.of(2018, 6, 5)));
    }

    @Test
    public void givenMondayWhenIsLocalDateTimeDayOfWeekMondayReturnsOk() {
        assertValidResult(validations.isLocalDateTimeDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(of(2018, 6, 4, 1, 2)));
    }

    @Test
    public void givenTuesdayWhenIsLocalDateTimeDayOfWeekMondayReturnsError() {
        assertErrorResult(validations.isLocalDateTimeDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(of(2018, 6, 5, 1, 2)));
    }

    @Test
    public void givenTuesdayWhenIsLocalDateTimeDayOfWeekTuesdayReturnsOk() {
        assertValidResult(validations.isLocalDateTimeDayOfWeek(DayOfWeek.TUESDAY, () -> errorDescription)
                .test(of(2018, 6, 5, 1, 2)));
    }

    @Test
    public void givenMondayWhenIsZonedDateTimeDayOfWeekMondayReturnsOk() {
        assertValidResult(validations.isZonedDateTimeDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 6, 4, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void givenTuesdayWhenIsZonedDateTimeDayOfWeekMondayReturnsError() {
        assertErrorResult(validations.isZonedDateTimeDayOfWeek(DayOfWeek.MONDAY, () -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 6, 5, 1, 2), ZoneId.systemDefault())));
    }

    @Test
    public void givenTuesdayWhenIsZonedDateTimeDayOfWeekTuesdayReturnsOk() {
        assertValidResult(validations.isZonedDateTimeDayOfWeek(DayOfWeek.TUESDAY, () -> errorDescription)
                .test(ZonedDateTime.of(of(2018, 6, 5, 1, 2), ZoneId.systemDefault())));
    }

}