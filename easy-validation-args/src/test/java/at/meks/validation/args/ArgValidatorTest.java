package at.meks.validation.args;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static at.meks.validation.args.ArgValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ArgValidatorTest {

    @Test
    void testString() {
        assertThat(validate().that("asdf"))
                .isInstanceOf(StringVerifier.class);
    }

    @Test
    void testComparable() {
        assertThat(validate().that(15))
                .isInstanceOf(ComparableVerifier.class);
    }

    @Test
    void testBoolean() {
        assertThat(validate().that(true))
                .isInstanceOf(BooleanVerifier.class);
    }

    @Test
    void testCollection() {
        assertThat(validate().that(new ArrayList<String>()))
                .isInstanceOf(CollectionVerifier.class);
    }

    @Test
    void testSingleMessage() {
        assertThatThrownBy(
                () -> validate().that("")
                        .withMessage(() -> "My custom error message")
                        .isNotBlank()
        ).hasMessage("My custom error message")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testMultipleMessagesWhenFirstFails() {
        assertThatThrownBy(
                () -> validate().that("")
                        .withMessage(() -> "notBlank").isNotBlank()
                        .withMessage(() -> "size").hasMinLength(3)
        ).hasMessage("notBlank")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testMultipleMessagesWhenSecondFails() {
        assertThatThrownBy(
                () -> validate().that("a")
                        .withMessage(() -> "notBlank").isNotBlank()
                        .withMessage(() -> "size").hasMinLength(3)
        ).hasMessage("size")
                .isInstanceOf(IllegalArgumentException.class);
    }
}