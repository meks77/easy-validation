package at.meks.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorMessageResolverTest {

    private final ErrorMessageResolver resolver = new ErrorMessageResolver();

    @BeforeClass
    public static void initializeLocale() {
        ValidationConfiguration.setLocale(Locale.ENGLISH);
    }

    @Test
    public void testThatTheDefaultLocaleIsUsed() {
        ValidationConfiguration.setLocale(null);
        Locale defaulLocale = Locale.getDefault();
        String isShortMessage = resolver.getIsShortMessage();
        if (defaulLocale.getLanguage().equals("de")) {
            assertThat(isShortMessage).isEqualTo(resolver.getIsShortMessage());
        } else {
            assertThat(isShortMessage).isEqualTo(resolver.getIsShortMessage());
        }
    }

    @Test
    public void givenItalianFileIsUsedWhenGetIsShortMessage() {
        ValidationConfiguration.setLocale(Locale.ITALIAN);
        assertThat(new ErrorMessageResolver().getIsShortMessage()).isEqualTo("il valore deve essere di tipo breve");
    }

    @Test
    public void getLengthIsMoreThanMessage() {
        assertMessages(() -> resolver.getLengthIsMoreThanMessage(4), "must have more than 4 chars",
                "muss mehr als 4 Zeichen enthalten");
    }

    private void assertMessages(Supplier<String> validationInvocation, String english, String german) {
        ValidationConfiguration.setLocale(Locale.ENGLISH);
        assertThat(validationInvocation.get()).isEqualTo(english);
        ValidationConfiguration.setLocale(Locale.GERMAN);
        assertThat(validationInvocation.get()).isEqualTo(german);
    }

    @Test
    public void getLengthIsLessThanMessage() {
        assertMessages(() -> resolver.getLengthIsLessThanMessage(4), "must have less than 4 chars",
                "muss weniger als 4 Zeichen enthalten");
    }

    @Test
    public void getHasLengthMessage() {
        assertMessages(() -> resolver.getHasLengthMessage(4), "length must be 4 chars", "Länge muss 4 Zeichen sein");
    }

    @Test
    public void getContainsMessage() {
        assertMessages(() -> resolver.getContainsMessage("x"), "must contain x", "Muss x enthalten");
    }

    @Test
    public void getIsNotBlankMessage() {
        assertMessages(resolver::getIsNotBlankMessage, "mustn't be blank", "Muss befüllt sein");
    }

    @Test
    public void getIsInListMessage() {
        assertMessages(() -> resolver.getIsInListMessage(Arrays.asList("a", "b", "c")), "must be in list: [a, b, c]",
                "Erlaubte Werte: [a, b, c]");
    }

    @Test
    public void getIsDateMessage() {
        assertMessages(() -> resolver.getIsDateMessage(DateTimeFormatter.BASIC_ISO_DATE),
                "must match to date format ParseCaseSensitive(false)Value(Year,4)Value(MonthOfYear,2)Value" +
                        "(DayOfMonth,2)[Offset(+HHMMss,'Z')]",
                "Datumsformat muss ParseCaseSensitive(false)Value(Year,4)Value(MonthOfYear,2)Value(DayOfMonth,2)" +
                        "[Offset(+HHMMss,'Z')] entsprechen");
    }

    @Test
    public void getIsNumericMessage() {
        assertMessages(resolver::getIsNumericMessage, "value must be numeric", "Wert muss numerisch sein");
    }

    @Test
    public void getContainsNotOnlyMessage() {
        assertMessages(() -> resolver.getContainsNotOnlyMessage("a"), "value mustn't contain only a",
                "Wert darf nicht nur a enthalten");
    }

    @Test
    public void getNotNullMessage() {
        assertMessages(resolver::getNotNullMessage, "must not be null", "Darf nicht null sein");
    }

    @Test
    public void getListContainsOnlyMessage() {
        assertMessages(() -> resolver.getListContainsOnlyMessage("x"), "list must contain only x",
                "Liste darf nur x enthalten");
    }

    @Test
    public void getListContainsMessage() {
        assertMessages(() -> resolver.getListContainsMessage("x"), "list must contain x", "Liste muss x enthalten");
    }

    @Test
    public void getListDoesNotContainMessage() {
        assertMessages(() -> resolver.getListDoesNotContainMessage("x"), "list mustn't contain x",
                "Liste darf nicht x enthalten");
    }

    @Test
    public void getListIsNotEmptyMessage() {
        assertMessages(resolver::getListIsNotEmptyMessage, "list mustn't be empty", "Liste darf nicht leer sein");
    }

    @Test
    public void getListIsEmptyMessage() {
        assertMessages(resolver::getListIsEmptyMessage, "list must be empty", "Liste muss leer sein");
    }

    @Test
    public void getListHasSizeMessage() {
        assertMessages(() -> resolver.getListHasSizeMessage(6), "size of list must be 6", "Listengröße muss 6 sein");
    }

    @Test
    public void getListHasMinSizeMessage() {
        assertMessages(() -> resolver.getListHasMinSizeMessage(6), "size of list must be at least 6",
                "Listengröße muss mindestens 6 sein");
    }

    @Test
    public void getListHasMaxSizeMessage() {
        assertMessages(() -> resolver.getListHasMaxSizeMessage(6), "size of list mustn't be greater than 6",
                "Listengröße darf 6 nicht überschreiten");
    }

    @Test
    public void getIsLessThanMessage() {
        assertMessages(() -> resolver.getIsLessThanMessage(6), "value must be less than 6",
                "Wert muss kleiner als 6 sein");
    }

    @Test
    public void getIsGreaterThanMessage() {
        assertMessages(() -> resolver.getIsGreaterThanMessage(6), "value must be greater than 6",
                "Wert muss größer als 6 sein");
    }

    @Test
    public void getIsBetweenMessage() {
        assertMessages(() -> resolver.getIsBetweenMessage(4, 6), "value must be in the range of 4 to 6",
                "Wert muss 4 bis 6 sein");
    }

    @Test
    public void getIsIntMessage() {
        assertMessages(resolver::getIsIntMessage, "value must be an integer", "Wert muss vom Typ Integer sein");
    }

    @Test
    public void getIsByteMessage() {
        assertMessages(resolver::getIsByteMessage, "value must be a byte", "Wert muss vom Typ Byte sein");
    }

    @Test
    public void getIsShortMessage() {
        assertMessages(resolver::getIsShortMessage, "value must be a short", "Wert muss vom Typ Short sein");
    }

    @Test
    public void getIsEqualToMessage()  {
        assertMessages(() -> resolver.getIsEqualToMessage("other"), "value must be equal to other",
                "Wert muss ident zu \"other\" sein");
    }

    @Test
    public void getIsNotEqualToMessage()  {
        assertMessages(() -> resolver.getIsNotEqualToMessage("other"), "value must not be equal to other",
                "Wert darf nicht ident zu \"other\" sein");
    }

    @Test
    public void getIsNullMessage() {
        assertMessages(resolver::getIsNullMessage, "value must be null", "Wert muss null sein");
    }

    @Test
    public void getIsDateFirstDayOfYearMessage() {
        assertMessages(resolver::getIsDateFirstDayOfYearMessage, "Date must be the first day of the year",
                "Datum muss der erste Tag des Jahres sein");
    }

    @Test
    public void getIsDateFirstDayOfMonthMessage() {
        assertMessages(resolver::getIsDateFirstDayOfMonthMessage, "Date must be the first day of the month",
                "Datum muss der erste Tag des Monats sein");
    }

    @Test
    public void getIsTimeStartOfDayMessage() {
        assertMessages(resolver::getIsTimeStartOfDayMessage, "Time must be 00:00:00", "Zeit muss 00:00:00 sein");
    }

    @Test
    public void getIsLastDayOfMonthMessage() {
        assertMessages(resolver::getIsLastDayOfMonthMessage, "Date must be the last day of the month",
                "Datum muss der letzte Tag des Monats sein");
    }

    @Test
    public void getIsDateLastDayOfYearMessage() {
        assertMessages(resolver::getIsDateLastDayOfYearMessage, "Date must be the last day of the year",
                "Datum muss der letzte Tag des Jahres sein");
    }

    @Test
    public void getIsDateTimeStartOfHourMessage() {
        assertMessages(resolver::getIsDateTimeStartOfHourMessage, "Date must have minutes and seconds set to 0",
                "Zeit muss auf 0 Minuten und 0 Sekunden gesetzt sein");
    }

    @Test
    public void givenMondayWhenGetIsDateDayOfWeekMessageReturnsExpected() {
        assertMessages(() -> resolver.getIsDateDayOfWeekMessage(DayOfWeek.MONDAY), "Date must be weekday MONDAY",
                "Datum muss der Wochentag MONDAY sein");
    }

    @Test
    public void givenTuesdayWhenGetIsDateDayOfWeekMessageReturnsExpected() {
        assertMessages(() -> resolver.getIsDateDayOfWeekMessage(DayOfWeek.TUESDAY), "Date must be weekday TUESDAY",
                "Datum muss der Wochentag TUESDAY sein");
    }

    @Test
    public void getLengthIsBetweenMessage() {
        assertMessages(() -> resolver.getLengthIsBetweenMessage(6, 15), "length must be between 6 and 15",
                "Länge muss zwischen 6 und 15 sein");
    }

    @Test
    public void getIsTrueMessage() {
        assertMessages(resolver::getIsTrueMessage, "value must be true", "Wert muss wahr sein");
    }

    @Test
    public void getIsFalseMessage() {
        assertMessages(resolver::getIsFalseMessage, "value must be false", "Wert muss unwahr sein");
    }
}