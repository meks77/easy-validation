package at.meks.validation.matcher;

public class BooleanMatcher {

    private BooleanMatcher() {

    }

    public static boolean isTrue(Boolean value) {
        return ObjectMatcher.isNotNull(value) && Boolean.TRUE.equals(value);
    }

    public static boolean isFalse(Boolean value) {
        return ObjectMatcher.isNotNull(value) && Boolean.FALSE.equals(value);
    }

}
