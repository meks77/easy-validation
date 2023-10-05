package at.meks.validation.core;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleValidationUsageTest {

    @Test
    void givenValidValueWhenStopOnFirst() {
        String validatedValue = "abc";
        Validator.stopOnFirstError()
                .throwing(() -> new NullPointerException("my custom message"))
                .verify(validatedValue)
                .matches(StringUtils::isNotBlank)
                .and(StringUtils::doesNotContainWhitespace);
    }

    @Test
    void givenInvalidValueWhenStopOnFirst() {
        String validatedValue = " ";
        assertThatThrownBy(() ->
                Validator.stopOnFirstError()
                        .throwing(() -> new NullPointerException("my custom message"))
                        .verify(validatedValue)
                        .matches(StringUtils::isNotBlank)
                        .and(StringUtils::doesNotContainWhitespace))
                .hasRootCauseExactlyInstanceOf(NullPointerException.class)
                .hasRootCauseMessage("my custom message");
    }

    @Test
    void testReportingErrors() {
        List<String> occuredErrorKeys = new ArrayList<>();
        String validatedValue = " ";
        Validator.reportTo(occuredErrorKeys::add)
                .verify(validatedValue)
                .usingKey("not_blank").matches(StringUtils::isNotBlank)
                .and().usingKey("whitespaces").matches(StringUtils::doesNotContainWhitespace)
                .and().usingKey("minLength").matches(value -> ofNullable(value).map(String::length).orElse(0) > 10);
        assertThat(occuredErrorKeys).containsExactlyInAnyOrder("not_blank", "whitespaces", "minLength");
    }
}
