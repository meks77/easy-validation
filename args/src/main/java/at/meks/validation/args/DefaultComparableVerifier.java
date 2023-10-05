package at.meks.validation.args;

public class DefaultComparableVerifier<T extends Comparable<T>> extends ComparableVerifier<T, DefaultComparableVerifier<T>> {

    DefaultComparableVerifier(T argumentValue) {
        super(argumentValue);
    }

}
