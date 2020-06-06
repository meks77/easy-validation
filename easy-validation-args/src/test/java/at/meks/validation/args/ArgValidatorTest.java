package at.meks.validation.args;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static at.meks.validation.args.ArgValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;
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
}