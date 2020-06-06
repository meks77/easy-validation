package at.meks.validation.matcher;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CollectionMatcherTest {

    @Test
    void isEmpty() {
        assertAll(
                () -> assertTrue(CollectionMatcher.isEmpty(null)),
                () -> assertTrue(CollectionMatcher.isEmpty(Collections.emptyList())),
                () -> assertFalse(CollectionMatcher.isEmpty(Collections.singleton("one")))
        );
    }

    @Test
    void isNotEmpty() {
        assertAll(
                () -> assertFalse(CollectionMatcher.isNotEmpty(null)),
                () -> assertFalse(CollectionMatcher.isNotEmpty(Collections.emptyList())),
                () -> assertTrue(CollectionMatcher.isNotEmpty(Collections.singletonList("one")))
        );
    }

    @Test
    void containsOnly() {
        assertAll(
                () -> assertTrue(CollectionMatcher.containsOnly(Arrays.asList("a", "a", "a"), "a")),
                () -> assertTrue(CollectionMatcher.containsOnly(Arrays.asList("a", "b", "a"), "a", "b")),
                () -> assertTrue(CollectionMatcher.containsOnly(Arrays.asList("a", "b", "a"), "a")),
                () -> assertFalse(CollectionMatcher.containsOnly(Arrays.asList("b", "c", "d"), "a")),
                () -> assertFalse(CollectionMatcher.containsOnly(Collections.singleton(null), "a")),
                () -> assertTrue(CollectionMatcher.containsOnly(Collections.singleton(null), null)),
                () -> assertTrue(CollectionMatcher.containsOnly(Arrays.asList("a", null), null, "a")),
                () -> assertFalse(CollectionMatcher.containsOnly(Collections.singleton("a"), null)),
                () -> assertFalse(CollectionMatcher.containsOnly(Collections.singleton("a"), "a", "b", "c"))
        );
    }

    @Test
    void contains() {
        assertAll(
                () -> assertTrue(CollectionMatcher.contains(Arrays.asList("a", "a", "a"), "a")),
                () -> assertFalse(CollectionMatcher.contains(Arrays.asList("a", "b", "c"), "a", "b")),
                () -> assertFalse(CollectionMatcher.contains(Arrays.asList("a", "b", "c"), "a", "d")),
                () -> assertFalse(CollectionMatcher.contains(Arrays.asList("b", "c", "d"), "a")),
                () -> assertFalse(CollectionMatcher.contains(Collections.singleton(null), "a")),
                () -> assertTrue(CollectionMatcher.contains(Collections.singleton(null), null)),
                () -> assertTrue(CollectionMatcher.contains(Arrays.asList("a", null), null, "a")),
                () -> assertFalse(CollectionMatcher.contains(Collections.singleton("a"), null)),
                () -> assertTrue(CollectionMatcher.contains(Collections.singleton("a"), "a", "b", "c")),
                () -> assertTrue(CollectionMatcher.contains(Arrays.asList("a", "b"), "a", "b", "c"))
        );
    }

}