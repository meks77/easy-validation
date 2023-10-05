package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CollectionVerifierTest extends AbstractVerifierTest<Collection<String>, CollectionVerifier<String>> {

    private static final Set<String> VALIDATED_VALUE = Collections.singleton("validatedValue");
    private final CollectionVerifier<String> verifierWithOneEntry = new CollectionVerifier<>(Collections.singleton("adf"));
    private final CollectionVerifier<String> verifierWithEmptyList = new CollectionVerifier<>(Collections.emptySet());
    private final CollectionVerifier<String> verifierWithNullList = new CollectionVerifier<>(null);

    @Override
    protected CollectionVerifier<String> getVerifierFor(Collection<String> value) {
        return new CollectionVerifier<>(value);
    }

    @Override
    protected CollectionVerifier<String> getVerifierWithValidatedValue() {
        return new CollectionVerifier<>(VALIDATED_VALUE);
    }

    @Override
    protected Collection<String> getValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Collection<String> getOtherValue() {
        return Collections.singleton("otherValue");
    }

    @Test
    void isEmpty() {
        assertAll(
                verifierWithNullList::isEmpty,
                verifierWithEmptyList::isEmpty,
                () -> assertThrows(IllegalArgumentException.class, verifierWithOneEntry::isEmpty)
        );
    }

    @Test
    void isEmptyReturnsItself() {
        assertSame(verifierWithNullList, verifierWithNullList.isEmpty());
    }

    @Test
    void isNotEmpty() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, verifierWithNullList::isNotEmpty),
                () -> assertThrows(IllegalArgumentException.class, verifierWithEmptyList::isNotEmpty),
                verifierWithOneEntry::isNotEmpty
        );
    }

    @Test
    void isNotEmptyReturnsItself() {
        assertSame(verifierWithOneEntry, verifierWithOneEntry.isNotEmpty());
    }

    @Test
    void containsOnly() {
        assertAll(
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .containsOnly("a", "b", "c"),
                () -> {
                    CollectionVerifier<String> verifier = new CollectionVerifier<>(Arrays.asList("a", "b"));
                    assertThrows(IllegalArgumentException.class,
                            () -> verifier.containsOnly("a", "b", "c"));
                },
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .containsOnly("a", "b"),
                () -> {
                    CollectionVerifier<String> verifier = new CollectionVerifier<>(Arrays.asList("a", null));
                    assertThrows(IllegalArgumentException.class,
                        () -> verifier.containsOnly("a", "b", null));
                },
                () -> new CollectionVerifier<>(Arrays.asList("a", null, "c"))
                        .containsOnly("a", null)
                );
    }

    @Test
    void containsOnlyReturnsItself() {
        CollectionVerifier<String> verifier = new CollectionVerifier<>(Collections.singleton("a"));
        CollectionVerifier<String> returnValue = verifier.containsOnly("a");
        assertSame(verifier, returnValue);
    }

    @Test
    void contains() {
        assertAll(
                () -> new CollectionVerifier<>(Arrays.asList("a", "b", "c"))
                        .contains("a", "b", "c"),
                () -> {
                    CollectionVerifier<String> verifier = new CollectionVerifier<>(Arrays.asList("a", "b", "c"));
                    assertThrows(IllegalArgumentException.class,
                        () -> verifier.contains("a", "b"));
                },
                () -> new CollectionVerifier<>(Arrays.asList("a", "b"))
                        .contains("a", "b", "c"),
                () -> {
                    CollectionVerifier<String> verifier = new CollectionVerifier<>(Arrays.asList("a", "b", null));
                    assertThrows(IllegalArgumentException.class,
                        () -> verifier.contains("a", (String) null));
                },
                () -> new CollectionVerifier<>(Arrays.asList("a", null))
                        .contains("a", null, "c")
        );
    }

    @Test
    void containsReturnsItself() {
        CollectionVerifier<String> verifier = new CollectionVerifier<>(Collections.singleton("a"));
        CollectionVerifier<String> returnValue = verifier.contains("a");
        assertSame(verifier, returnValue);
    }
}