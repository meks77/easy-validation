package at.meks.validation;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.text.MessageFormat.format;

/**
 * This class provides all messages for validation errors. It was just centralized to be able to extend the message
 * creation later.
 *
 * The ideas are:
 * * extend by resource bundle to provide more languages
 * * extend by factory to be able to provide another message resolver
 */
public class ErrorMessageResolver {


    private ResourceBundle bundle;

    public ErrorMessageResolver() {
        initializeBundle();
    }

    private void initializeBundle() {
        bundle = ResourceBundle.getBundle("at/meks/validation/errors", ValidationConfiguration.getLocale(), new ResourceBundle.Control(){

            @Override
            public Locale getFallbackLocale(String baseName, Locale locale) {
                return Locale.ROOT;
            }
        });
    }

    public String getLengthIsMoreThanMessage(int size) {
        return format(getBundleString("string.lengthIsMoreThan"), size);
    }

    private String getBundleString(String lengthIsMoreThan) {
        if (!bundle.getLocale().equals(ValidationConfiguration.getLocale())) {
            initializeBundle();
        }
        return bundle.getString(lengthIsMoreThan);
    }

    public String getLengthIsLessThanMessage(int size) {
        return format(getBundleString("string.lengthIsLessThan"), size);
    }

    public String getHasLengthMessage(int length) {
        return format(getBundleString("string.hasLength"), length);
    }

    public String getContainsMessage(String contained) {
        return format(getBundleString("string.contains"), contained);
    }

    public String getIsNotBlankMessage() {
        return getBundleString("string.isNotBlank");
    }

    public String getIsInListMessage(Collection<String> validValueSupplier) {
        return format(getBundleString("string.isInList"), String.join(", ", validValueSupplier));
    }

    public String getIsDateMessage(DateTimeFormatter formatter) {
        return format(getBundleString("string.isDate"), formatter);
    }

    public String getIsNumericMessage() {
        return getBundleString("string.isNumeric");
    }

    public String getContainsNotOnlyMessage(String containedValue) {
        return format(getBundleString("string.containsNotOnly"), containedValue);
    }

    public String getLengthIsBetweenMessage(Integer minSize, Integer maxSize) {
        return format(getBundleString("string.lengthIsBetween"), minSize, maxSize);
    }

    public String getNotNullMessage() {
        return getBundleString("mustNotBeNull");
    }

    public String getListContainsOnlyMessage(Object containedValue) {
        return format(getBundleString("list.containsOnly"), containedValue);
    }

    public String getListContainsMessage(Object containedValue) {
        return format(getBundleString("list.contains"), containedValue);
    }

    public String getListDoesNotContainMessage(Object excludedValue) {
        return format(getBundleString("list.doesNotContain"), excludedValue);
    }

    public String getListIsNotEmptyMessage() {
        return getBundleString("list.isNotEmpty");
    }

    public String getListIsEmptyMessage() {
        return getBundleString("list.isEmpty");
    }

    public String getListHasSizeMessage(int size) {
        return format(getBundleString("list.hasSize"), size);
    }

    public String getListHasMinSizeMessage(int size) {
        return format(getBundleString("list.hasMinSize"), size);
    }

    public String getListHasMaxSizeMessage(int size) {
        return format(getBundleString("list.hasMaxSize"), size);
    }

    public String getIsLessThanMessage(Object compareTo) {
        return format(getBundleString("isLessThan"), compareTo);
    }

    public String getIsGreaterThanMessage(Object compareTo) {
        return format(getBundleString("isGreaterThan"), compareTo);
    }

    public String getIsBetweenMessage(Object min, Object max) {
        return format(getBundleString("isBetween"), min, max);
    }

    public String getIsIntMessage() {
        return getBundleString("isInt");
    }

    public String getIsByteMessage() {
        return getBundleString("isByte");
    }

    public String getIsShortMessage() {
        return getBundleString("isShort");
    }

    public String getIsEqualToMessage(Object comparedTo) {
        return format(getBundleString("isEqualTo"), comparedTo);
    }

    public String getIsNotEqualToMessage(Object compareTo) {
        return format(getBundleString("isNotEqualTo"), compareTo);
    }

    public String getIsNullMessage() {
        return getBundleString("isNull");
    }

    public String getIsDateFirstDayOfYearMessage() {
        return getBundleString("date.isDateFirstDayOfYear");
    }

    public String getIsDateFirstDayOfMonthMessage() {
        return getBundleString("date.isDateFirstDayOfMonth");
    }

    public String getIsTimeStartOfDayMessage() {
        return getBundleString("date.isTimeStartOfDay");
    }

    public String getIsLastDayOfMonthMessage() {
        return getBundleString("date.isLastDayOfMonth");
    }

    public String getIsDateLastDayOfYearMessage() {
        return getBundleString("date.isDateLastDayOfYear");
    }

    public String getIsDateTimeStartOfHourMessage() {
        return getBundleString("date.isDateTimeStartOfHour");
    }

    public String getIsDateDayOfWeekMessage(DayOfWeek dayOfWeek) {
        return format(getBundleString("isDateDayOfWeek"), dayOfWeek);
    }
}