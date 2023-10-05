package at.meks.validation.core;

public interface Matcher<T> {

    boolean verify(T value);
}
