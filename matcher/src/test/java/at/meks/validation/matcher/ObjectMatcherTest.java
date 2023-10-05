package at.meks.validation.matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMatcherTest {

    @Test
    void isNull() {
        assertAll(
                () -> assertTrue(ObjectMatcher.isNull(null)),
                () -> assertFalse(ObjectMatcher.isNull("")),
                () -> assertTrue(ObjectMatcher.isNull(null, null)),
                () -> assertFalse(ObjectMatcher.isNull(null, "")),
                () -> assertFalse(ObjectMatcher.isNull("", ""))

        );
    }

    @Test
    void isNotNull() {
        assertAll(
                () -> assertFalse(ObjectMatcher.isNotNull(null)),
                () -> assertTrue(ObjectMatcher.isNotNull("")),
                () -> assertFalse(ObjectMatcher.isNotNull(null, null)),
                () -> assertFalse(ObjectMatcher.isNotNull("", null)),
                () -> assertTrue(ObjectMatcher.isNotNull("", ""))
        );
    }

    @Test
    void isEqual() {
        assertAll(
                () -> assertTrue(ObjectMatcher.isEqual(null, null)),
                () -> assertTrue(ObjectMatcher.isEqual("a", "a")),
                () -> assertFalse(ObjectMatcher.isEqual(null, "a")),
                () -> assertFalse(ObjectMatcher.isEqual("a", null)),
                () -> assertFalse(ObjectMatcher.isEqual("a", "b"))
        );
    }

    @Test
    void isNotEqual() {
        assertAll(
                () -> assertFalse(ObjectMatcher.isNotEqual(null, null)),
                () -> assertFalse(ObjectMatcher.isNotEqual("a", "a")),
                () -> assertTrue(ObjectMatcher.isNotEqual(null, "a")),
                () -> assertTrue(ObjectMatcher.isNotEqual("a", null)),
                () -> assertTrue(ObjectMatcher.isNotEqual("a", "b"))
        );
    }
}