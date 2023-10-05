package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static at.meks.validation.args.ArgValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void testObject() {
        assertThat(validate().that(new PlainObject()))
                .isInstanceOf(ObjectVerifier.class);
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
        StringVerifier stringVerifier = validate().that("")
                .withMessage(() -> "My custom error message");

        assertThatThrownBy(stringVerifier::isNotBlank)
                .hasMessage("My custom error message")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testMultipleMessagesWhenSecondFails() {
        StringVerifier verifier = validate().that("a")
                .withMessage(() -> "notBlank").isNotBlank()
                .withMessage(() -> "size");
        assertThatThrownBy(() -> verifier.hasMinLength(3))
                .hasMessage("size")
                .isInstanceOf(IllegalArgumentException.class);
    }
}