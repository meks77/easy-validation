package at.meks.validation.matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMatcherTest {

    @Test
    void isNotBlank() {
        assertAll(
                () -> assertTrue(StringMatcher.isNotBlank("a")),
                () -> assertTrue(StringMatcher.isNotBlank(" a ")),
                () -> assertFalse(StringMatcher.isNotBlank("")),
                () -> assertFalse(StringMatcher.isNotBlank(" ")),
                () -> assertFalse(StringMatcher.isNotBlank(null))
        );
    }

    @Test
    void isNotEmpty() {
        assertAll(
                () -> assertTrue(StringMatcher.isNotEmpty(" ")),
                () -> assertTrue(StringMatcher.isNotEmpty("a")),
                () -> assertTrue(StringMatcher.isNotEmpty(" a ")),
                () -> assertFalse(StringMatcher.isNotEmpty("")),
                () -> assertFalse(StringMatcher.isNotEmpty(null))
        );
    }

    @Test
    void contains() {
        assertAll(
                () -> assertTrue(StringMatcher.contains("abc", "b")),
                () -> assertTrue(StringMatcher.contains(null, null)),
                () -> assertFalse(StringMatcher.contains("abc", "d")),
                () -> assertFalse(StringMatcher.contains("", "d")),
                () -> assertFalse(StringMatcher.contains(null, "d")),
                () -> assertFalse(StringMatcher.contains("abc", null)),
                () -> assertFalse(StringMatcher.contains("null", null)),
                () -> assertFalse(StringMatcher.contains(null, "null"))
        );
    }

    @Test
    void containsNot() {
        assertAll(
                () -> assertFalse(StringMatcher.containsNot("abc", "b")),
                () -> assertFalse(StringMatcher.containsNot(null, null)),
                () -> assertTrue(StringMatcher.containsNot("abc", "d")),
                () -> assertTrue(StringMatcher.containsNot("", "d")),
                () -> assertTrue(StringMatcher.containsNot(null, "d")),
                () -> assertTrue(StringMatcher.containsNot("abc", null)),
                () -> assertTrue(StringMatcher.containsNot("null", null)),
                () -> assertTrue(StringMatcher.containsNot(null, "null"))
        );
    }

    @Test
    void containsDigitsOnly() {
        assertAll(
                () -> assertTrue(StringMatcher.containsDigitsOnly("1234567890")),
                () -> assertFalse(StringMatcher.containsDigitsOnly("12345a67890")),
                () -> assertFalse(StringMatcher.containsDigitsOnly("12345$67890")),
                () -> assertFalse(StringMatcher.containsDigitsOnly("adsf")),
                () -> assertFalse(StringMatcher.containsDigitsOnly("123456.7890")),
                () -> assertFalse(StringMatcher.containsDigitsOnly("")),
                () -> assertFalse(StringMatcher.containsDigitsOnly(null))
        );
    }

    @Test
    void hasMinLength() {
        assertAll(
                () -> assertTrue(StringMatcher.hasMinLength("asdf", 3)),
                () -> assertTrue(StringMatcher.hasMinLength("asdf", 4)),
                () -> assertFalse(StringMatcher.hasMinLength("asdf", 5)),
                () -> assertFalse(StringMatcher.hasMinLength(null, 1))
        );
    }
}