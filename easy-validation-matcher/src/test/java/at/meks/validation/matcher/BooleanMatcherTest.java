package at.meks.validation.matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanMatcherTest {

    @Test
    void isTrue() {
        assertAll(
                () -> assertFalse(BooleanMatcher.isTrue(null)),
                () -> assertFalse(BooleanMatcher.isTrue(false)),
                () -> assertTrue(BooleanMatcher.isTrue(true))
        );
    }

    @Test
    void isFalse() {
        assertAll(
                () -> assertFalse(BooleanMatcher.isFalse(null)),
                () -> assertFalse(BooleanMatcher.isFalse(true)),
                () -> assertTrue(BooleanMatcher.isFalse(false))
        );
    }

}