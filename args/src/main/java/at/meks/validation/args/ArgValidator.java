package at.meks.validation.args;

import java.util.Collection;

/**
 * That's the starting point for validations. To start with the fluent interface use the method validate.
 */
public class ArgValidator {

    /**
     * the start for the validation
     * @return a new instance of the validator
     */
    public static ArgValidator validate() {
        return new ArgValidator();
    }

    /**
     * @param argumentValue the value, which will be validated
     * @return  a verifier for strings
     */
    public StringVerifier that(String argumentValue) {
        return new StringVerifier(argumentValue);
    }

    /**
     * @param argumentValue the value, which will be validated
     * @param <T> The type of the value, which is verified
     * @return  a verifier for objects which extend {@link Comparable}
     */
    public <T extends Comparable<T>> DefaultComparableVerifier<T> that(T argumentValue) {
        return new DefaultComparableVerifier<>(argumentValue);
    }

    /**
     * @param argumentValue the value, which will be validated
     * @return  a verifier for plain objects
     */
    public ObjectVerifier that(Object argumentValue) {
        return new ObjectVerifier(argumentValue);
    }

    /**
     * @param argumentValue the value, which will be validated
     * @return  a verifier for booleans
     */
    public BooleanVerifier that(Boolean argumentValue) {
        return new BooleanVerifier(argumentValue);
    }

    /**
     * @param argumentValue the value, which will be validated
     * @param <T> The type of the value, which is verified
     * @return  a verifier for collections
     */
    public <T> CollectionVerifier<T> that(Collection<T> argumentValue) {
        return new CollectionVerifier<>(argumentValue);
    }

}
