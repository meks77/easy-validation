package at.meks.validation.usage;

import at.meks.validation.args.ArgValidator;
import org.junit.jupiter.api.Test;

class UsageTest {

    @Test
    void test () {
        ArgValidator.validate().that("asdf").isNotNull();
    }
}
