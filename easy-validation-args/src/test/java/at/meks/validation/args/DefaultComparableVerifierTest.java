package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultComparableVerifierTest extends AbstractVerifierTest<ChronoLocalDate, DefaultComparableVerifier<ChronoLocalDate>> {

    private static final LocalDate VALIDATED_VALUE = LocalDate.now().minusDays(1);
    private final DefaultComparableVerifier<ChronoLocalDate> verifierWithValue = getVerifierWithValidatedValue();

    @Override
    protected DefaultComparableVerifier<ChronoLocalDate> getVerifierWithValidatedValue() {
        return new DefaultComparableVerifier<>(VALIDATED_VALUE);
    }

    @Override
    protected DefaultComparableVerifier<ChronoLocalDate> getVerifierWithNullValue() {
        return new DefaultComparableVerifier<>(null);
    }

    @Override
    protected LocalDate getValidatedValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected LocalDate getOtherValue() {
        return LocalDate.now();
    }

    @Test
    void isGreater() {
        assertAll(
                () -> verifierWithValue.isGreater(VALIDATED_VALUE.minusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> verifierWithValue.isGreater(VALIDATED_VALUE)),
                () -> {
                    LocalDate otherValue = VALIDATED_VALUE.plusDays(1L);
                    assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isGreater(otherValue));
                }
        );
    }

    @Test
    void isGreaterOrEqual() {
        assertAll(
                () -> verifierWithValue.isGreaterOrEqual(VALIDATED_VALUE.minusDays(1L)),
                () -> verifierWithValue.isGreaterOrEqual(VALIDATED_VALUE),
                () -> {
                    LocalDate otherValue = VALIDATED_VALUE.plusDays(1L);
                    assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isGreaterOrEqual(otherValue));
                }
        );
    }

    @Test
    void isLess() {
        assertAll(
                () -> verifierWithValue.isLess(VALIDATED_VALUE.plusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> verifierWithValue.isLess(VALIDATED_VALUE)),
                () -> {
                    LocalDate otherValue = VALIDATED_VALUE.minusDays(1L);
                    assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isLess(otherValue));
                }
        );
    }

    @Test
    void isLessOrEqual() {
        assertAll(
                () -> verifierWithValue.isLessOrEqual(VALIDATED_VALUE.plusDays(1L)),
                () -> verifierWithValue.isLessOrEqual(VALIDATED_VALUE),
                () -> {
                    LocalDate otherValue = VALIDATED_VALUE.minusDays(1L);
                    assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isLessOrEqual(otherValue));
                }
        );
    }

    @Test
    void isBetween() {
        LocalDate min = VALIDATED_VALUE.minusDays(1L);
        LocalDate max = VALIDATED_VALUE.plusDays(1L);
        assertAll(
                () -> verifierWithValue.isBetween(VALIDATED_VALUE, VALIDATED_VALUE),
                () -> verifierWithValue.isBetween(min, max),
                () -> assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isBetween(max, max)),
                () -> assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isBetween(min, min))
        );
    }

    @Test
    void isNotBetween() {
        LocalDate greater = VALIDATED_VALUE.plusDays(1L);
        LocalDate smaller = VALIDATED_VALUE.minusDays(1L);
        assertAll(
                () -> verifierWithValue.isNotBetween(greater, greater),
                () -> verifierWithValue.isNotBetween(smaller, smaller),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> verifierWithValue.isNotBetween(VALIDATED_VALUE, VALIDATED_VALUE)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> verifierWithValue.isNotBetween(smaller, greater))
        );
    }
}