package at.meks.validation.usage;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static at.meks.validation.args.ArgValidator.validate;

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
