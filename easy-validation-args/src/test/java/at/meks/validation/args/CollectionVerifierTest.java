package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CollectionVerifierTest extends AbstractVerifierTest<Collection<String>, CollectionVerifier<String>> {

    private static final Set<String> VALIDATED_VALUE = Collections.singleton("validatedValue");

    @Override
    protected CollectionVerifier<String> getVerifierWithValidatedValue() {
        return new CollectionVerifier<>(VALIDATED_VALUE);
    }

    @Override
    protected CollectionVerifier<String> getVerifierWithNullValue() {
        return new CollectionVerifier<>(null);
    }

    @Override
    protected Collection<String> getValidatedValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Collection<String> getOtherValue() {
        return Collections.singleton("otherValue");
    }

    @Test
    void isEmpty() {
        assertAll(
                () -> new CollectionVerifier<String>(null).isEmpty(),
                () -> new CollectionVerifier<String>(Collections.emptySet()).isEmpty(),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<>(Collections.singleton("adf")).isEmpty())
        );
    }

    @Test
    void isNotEmpty() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<String>(null).isNotEmpty()),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<String>(Collections.emptySet()).isNotEmpty()),
                () -> new CollectionVerifier<>(Collections.singleton("adf")).isNotEmpty()
        );
    }

    @Test
    void containsOnly() {
        assertAll(
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .containsOnly("a", "b", "c"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<>(Arrays.asList("a", "b"))
                                .containsOnly("a", "b", "c")),
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .containsOnly("a", "b"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<>(Arrays.asList("a", null))
                                .containsOnly("a", "b", null)),
                () -> new CollectionVerifier<>(Arrays.asList("a", null, "c"))
                        .containsOnly("a", null)
                );
    }

    @Test
    void contains() {
        assertAll(
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .contains("a", "b", "c"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                                .contains("a", "b")),
                () -> new CollectionVerifier<>(Arrays.asList("a", "b"))
                        .contains("a", "b", "c"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new CollectionVerifier<>(Arrays.asList("a", "b", null))
                                .contains("a", (String) null)),
                () -> new CollectionVerifier<>(Arrays.asList("a", null))
                        .contains("a", null, "c")
        );}
}