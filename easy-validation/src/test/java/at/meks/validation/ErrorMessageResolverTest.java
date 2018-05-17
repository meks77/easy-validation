package at.meks.validation;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorMessageResolverTest {

    private ErrorMessageResolver resolver = new ErrorMessageResolver();

    @Test
    public void getLengthIsMoreThanMessage() {
        assertThat(resolver.getLengthIsMoreThanMessage(4)).isEqualTo("must have more than 4 chars");
    }

    @Test
    public void getLengthIsLessThanMessage() {
        assertThat(resolver.getLengthIsLessThanMessage(4)).isEqualTo("must have less than 4 chars");
    }

    @Test
    public void getHasLenghtMessage() {
        assertThat(resolver.getHasLenghtMessage(4)).isEqualTo("length must be 4 chars");
    }

    @Test
    public void getContainsMessage() {
        assertThat(resolver.getContainsMessage("x")).isEqualTo("must contain x");
    }

    @Test
    public void getIsNotBlankMessage() {
        assertThat(resolver.getIsNotBlankMessage()).isEqualTo("mustn't be blank");
    }

    @Test
    public void getIsInListMessage() {
        assertThat(resolver.getIsInListMessage(Arrays.asList("a", "b", "c"))).isEqualTo("must be in list: [a, b, c]");
    }

    @Test
    public void getIsDateMessage() {
        assertThat(resolver.getIsDateMessage(DateTimeFormatter.BASIC_ISO_DATE))
                .isEqualTo("must match to date format ParseCaseSensitive(false)Value(Year,4)Value(MonthOfYear,2)" +
                        "Value(DayOfMonth,2)[Offset(+HHMMss,'Z')]");
    }

    @Test
    public void getIsNumericMessage() {
        assertThat(resolver.getIsNumericMessage()).isEqualTo("value must be numeric");
    }

    @Test
    public void getContainsNotOnlyMessage() {
        assertThat(resolver.getContainsNotOnlyMessage("a")).isEqualTo("value mustn't contain only a");
    }

    @Test
    public void getNotNullMessage() {
        assertThat(resolver.getNotNullMessage()).isEqualTo("must not be null");
    }

    @Test
    public void getIsDateAfterMessage() {
        assertThat(resolver.getIsDateAfterMessage(LocalDateTime.of(2012, 8, 24, 4, 2, 16)))
                .isEqualTo("date must be after 2012-08-24T04:02:16");
    }

    @Test
    public void getListContainsOnlyMessage() {
        assertThat(resolver.getListContainsOnlyMessage("x")).isEqualTo("list must contain only x");
    }

    @Test
    public void getListContainsMessage() {
        assertThat(resolver.getListContainsMessage("x")).isEqualTo("list must contain x");
    }

    @Test
    public void getListDoesNotContainMessage() {
        assertThat(resolver.getListDoesNotContainMessage("x")).isEqualTo("list mustn't contain x");
    }

    @Test
    public void getListIsNotEmptyMessage() {
        assertThat(resolver.getListIsNotEmptyMessage()).isEqualTo("list mustn't be empty");
    }

    @Test
    public void getListIsEmptyMessage() {
        assertThat(resolver.getListIsEmptyMessage()).isEqualTo("list must be empty");
    }

    @Test
    public void getListHasSizeMessage() {
        assertThat(resolver.getListHasSizeMessage(6)).isEqualTo("size of list must be 6");
    }

    @Test
    public void getListHasMinSizeMessage() {
        assertThat(resolver.getListHasMinSizeMessage(6)).isEqualTo("size of list must be at least 6");
    }

    @Test
    public void getListHasMaxSizeMessage() {
        assertThat(resolver.getListHasMaxSizeMessage(6)).isEqualTo("size of list mustn't be greater than 6");
    }

    @Test
    public void getIsLessThanMessage() {
        assertThat(resolver.getIsLessThanMessage(6)).isEqualTo("value must be less than 6");
    }

    @Test
    public void getIsGreaterThanMessage() {
        assertThat(resolver.getIsGreaterThanMessage(6)).isEqualTo("value must be greater than 6");
    }

    @Test
    public void getIsBetweenMessage() {
        assertThat(resolver.getIsBetweenMessage(4, 6)).isEqualTo("value must be between 4 and 6");
    }

    @Test
    public void getIsIntMessage() {
        assertThat(resolver.getIsIntMessage()).isEqualTo("value must be an integer");
    }

    @Test
    public void getIsByteMessage() {
        assertThat(resolver.getIsByteMessage()).isEqualTo("value must be a byte");
    }

    @Test
    public void getIsShortMessage() {
        assertThat(resolver.getIsShortMessage()).isEqualTo("value must be a short");
    }

    @Test
    public void getIsEqualToMessage()  {
        assertThat(resolver.getIsEqualToMessage("other")).isEqualTo("value must be equal to other");
    }

    @Test
    public void getIsNotEqualToMessage()  {
        assertThat(resolver.getIsNotEqualToMessage("other")).isEqualTo("value must not be equal to other");
    }
}