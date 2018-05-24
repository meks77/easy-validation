package at.meks.validation.simple;

import at.meks.validation.result.ValidationException;
import at.meks.validation.result.ValidationResult;
import at.meks.validation.validations.common.CommonValidations;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import static at.meks.validation.validations.common.CommonValidations.isBetween;
import static at.meks.validation.validations.common.CommonValidations.isEqualTo;
import static at.meks.validation.validations.common.CommonValidations.isGreaterThan;
import static at.meks.validation.validations.common.CommonValidations.isLessThan;
import static at.meks.validation.validations.string.StringValidations.contains;
import static at.meks.validation.validations.string.StringValidations.isInList;
import static at.meks.validation.validations.string.StringValidations.isNotBlank;
import static at.meks.validation.validations.string.StringValidations.isNumeric;

class SimpleValidationExamples {

    private final Logger logger = Logger.getLogger(getClass().getName());

    void validateForNotBlankNumericAndThrow(String ageString) throws ValidationException {
        isNumeric().test(ageString).throwIfInvalid("age");
    }

    void validateForNumberInRange(int age) throws ValidationException {
        isBetween(18, 65).test(age).throwIfInvalid("age");
    }

    void validateForValueInListOfValidValues(String value) throws ValidationException {
        isInList(this::getValidCities).test(value).throwIfInvalid("city");
    }

    private Collection<String> getValidCities() {
        return Arrays.asList("New York", "London", "Berlin", "Vienna", "Paris", "Madrid", "Moscow", "Rom");
    }

    ValidationResult validateUsingResult() {
        String userInput = "";
        ValidationResult result = CommonValidations.notNull().test(userInput);
        if (!result.isValid()) {
            logger.fine(() -> String.format("error message of validation: %s", result.getErrorMessage()));
        }
        return result;
    }

    void validateThrowingAnErrorIfViolates() throws ValidationException, IOException {
        File file = getFile();
        isNotBlank().and(contains("valEx")).test(file.getName()).throwIfInvalid("fileName");
        isGreaterThan(10L).test(file.length()).throwIfInvalid("fileSize");
    }

    private File getFile() throws IOException {
        return File.createTempFile("valEx", "tmp");
    }

    void combineMoreValidationsInASingleLine() throws ValidationException {
        String cityName = "Vienna";
        CommonValidations.<String>notNull().and(isNotBlank()).and(contains("e")).and(isInList(this::getValidCities))
                .test(cityName).throwIfInvalid("cityName");
    }

    void combineValidationsForDifferentTypes() {
        isNumeric().and(Integer::parseInt, isGreaterThan(17).and(isLessThan(28)));
        // or better use between :)
        isNumeric().and(Integer::parseInt, isBetween(18, 27));
    }

    void combineEqualAndIsGreaterThan() throws ValidationException {
        Long minValue = 10L;
        isGreaterThan(minValue).or(isEqualTo(minValue)).test(10L).throwIfInvalid("10");
    }

    void validateLocalDate() throws ValidationException {
        isGreaterThan((ChronoLocalDate)LocalDate.of(2010, 1,1)).test(LocalDate.now()).throwIfInvalid("localDate");
        isBetween((ChronoLocalDate) LocalDate.of(2011, 1, 1), LocalDate.of(2012, 1, 1)).test(LocalDate.of(2011, 12, 1));
    }

}
