package at.meks.validation.matcher;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ComparableMatcherTest {

    @Test
    void isGreater() {
        assertAll(
                () -> assertTrue(ComparableMatcher.isGreater(5.0, 4.9)),
                () -> assertFalse(ComparableMatcher.isGreater(5.0, 5.0)),
                () -> assertFalse(ComparableMatcher.isGreater(5.0, 5.1))
        );
    }

    @Test
    void isGreaterOrEqualTo() {
        assertAll(
                () -> assertTrue(ComparableMatcher.isGreaterOrEqual(5.0, 4.9)),
                () -> assertTrue(ComparableMatcher.isGreaterOrEqual(5.0, 5.0)),
                () -> assertFalse(ComparableMatcher.isGreaterOrEqual(5.0, 5.1))
        );
    }

    @Test
    void isLess() {
        assertAll(
                () -> assertTrue(ComparableMatcher.isLess(4.9, 5.0)),
                () -> assertFalse(ComparableMatcher.isLess(5.0, 5.0)),
                () -> assertFalse(ComparableMatcher.isLess(5.1, 5.0))
        );
    }

    @Test
    void givenIntWhenIsLessOrEqualTo() {
        assertAll(
                () -> assertTrue(ComparableMatcher.isLessOrEqual(4.9, 5.0)),
                () -> assertTrue(ComparableMatcher.isLessOrEqual(5.0, 5.0)),
                () -> assertFalse(ComparableMatcher.isLessOrEqual(5.1, 5.0))
        );
    }

    @Test
    void giveLocalDatTimeWhenIsLessOrEqualTo() {
        assertTrue(ComparableMatcher.isLessOrEqual(LocalDateTime.now().minusNanos(1), LocalDateTime.now()));
    }

    @Test
    void isBetween() {
        assertAll(
                () -> assertFalse(ComparableMatcher.isBetween(8, 9, 11)),
                () -> assertTrue(ComparableMatcher.isBetween(9, 9, 11)),
                () -> assertTrue(ComparableMatcher.isBetween(10, 9, 11)),
                () -> assertTrue(ComparableMatcher.isBetween(11, 9, 11)),
                () -> assertFalse(ComparableMatcher.isBetween(12, 9, 11))
        );
    }

    @Test
    void isNotBetween() {
        assertAll(
                () -> assertTrue(ComparableMatcher.isNotBetween(8, 9, 11)),
                () -> assertFalse(ComparableMatcher.isNotBetween(9, 9, 11)),
                () -> assertFalse(ComparableMatcher.isNotBetween(10, 9, 11)),
                () -> assertFalse(ComparableMatcher.isNotBetween(11, 9, 11)),
                () -> assertTrue(ComparableMatcher.isNotBetween(12, 9, 11))
        );
    }

}