package at.meks.validation.usage;

import at.meks.validation.args.StringVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static at.meks.validation.args.ArgValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsageTest {

    @Test
    void test() {
        validate().that("asdf").isNotNull();
    }

    @Test
    void testCollectionArgValidation() {
        validate().that(Collections.singletonList("exampleEntry"))
                .isNotNull()
                .isNotEmpty()
                .contains("exampleEntry")
                .contains("exampleEntry")
                .matches(collectionValue -> !collectionValue.isEmpty())
                .isNotEmpty();
    }


}
