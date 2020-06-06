package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultComparableVerifierTest extends AbstractVerifierTest<ChronoLocalDate, DefaultComparableVerifier<ChronoLocalDate>> {

    private static final LocalDate VALIDATED_VALUE = LocalDate.now().minusDays(1);

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
                () -> getVerifierWithValidatedValue().isGreater(VALIDATED_VALUE.minusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isGreater(VALIDATED_VALUE)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isGreater(VALIDATED_VALUE.plusDays(1L)))
        );
    }

    @Test
    void isGreaterOrEqual() {
        assertAll(
                () -> getVerifierWithValidatedValue().isGreaterOrEqual(VALIDATED_VALUE.minusDays(1L)),
                () -> getVerifierWithValidatedValue().isGreaterOrEqual(VALIDATED_VALUE),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isGreaterOrEqual(VALIDATED_VALUE.plusDays(1L)))
        );
    }

    @Test
    void isLess() {
        assertAll(
                () -> getVerifierWithValidatedValue().isLess(VALIDATED_VALUE.plusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isLess(VALIDATED_VALUE)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isLess(VALIDATED_VALUE.minusDays(1L)))
        );
    }

    @Test
    void isLessOrEqual() {
        assertAll(
                () -> getVerifierWithValidatedValue().isLessOrEqual(VALIDATED_VALUE.plusDays(1L)),
                () -> getVerifierWithValidatedValue().isLessOrEqual(VALIDATED_VALUE),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isLessOrEqual(VALIDATED_VALUE.minusDays(1L)))
        );
    }

    @Test
    void isBetween() {
        assertAll(
                () -> getVerifierWithValidatedValue().isBetween(VALIDATED_VALUE, VALIDATED_VALUE),
                () -> getVerifierWithValidatedValue().isBetween(VALIDATED_VALUE.minusDays(1L), VALIDATED_VALUE.plusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isBetween(VALIDATED_VALUE.plusDays(1L), VALIDATED_VALUE.plusDays(1L))),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isBetween(VALIDATED_VALUE.minusDays(1L), VALIDATED_VALUE.minusDays(1L)))
        );
    }

    @Test
    void isNotBetween() {
        assertAll(
                () -> getVerifierWithValidatedValue().isNotBetween(VALIDATED_VALUE.plusDays(1L), VALIDATED_VALUE.plusDays(1L)),
                () -> getVerifierWithValidatedValue().isNotBetween(VALIDATED_VALUE.minusDays(1L), VALIDATED_VALUE.minusDays(1L)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isNotBetween(VALIDATED_VALUE, VALIDATED_VALUE)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> getVerifierWithValidatedValue().isNotBetween(VALIDATED_VALUE.minusDays(1L), VALIDATED_VALUE.plusDays(1L)))
        );
    }
}