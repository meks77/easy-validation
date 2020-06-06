package at.meks.validation.args;

import at.meks.validation.matcher.StringMatcher;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static at.meks.validation.args.ArgValidator.validate;

class ArgValidatorUsageTest {

    @Test
    void givenNumberWhenValidate() {
        validate()
                .that("123")
                .isNotBlank()
                .hasMinLength(2)
                .matches(StringMatcher::containsDigitsOnly)
        ;
    }

    @Test
    void givenComparableWhenValidate() {
        validate()
                .that(LocalDateTime.now())
                .isLessOrEqual(LocalDateTime.now());
    }

}