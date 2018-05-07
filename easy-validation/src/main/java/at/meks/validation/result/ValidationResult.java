package at.meks.validation.result;

/**
 * This is the result of a validation.
 */
public class ValidationResult {

    private static final ValidationResult OK_RESULT = new ValidationResult(true, null);

    private final boolean valid;
    private final ErrorDescription errorDescription;

    private ValidationResult(boolean valid, ErrorDescription errorDescription) {
        this.valid = valid;
        this.errorDescription = errorDescription;
    }

    /**
     * @return error code which was set when the validation was created
     */
    public String getErrorCode() {
        return errorDescription.getErrorCode();
    }

    /**
     * @return the error message why the validation failed
     */
    public String getErrorMessage() {
        return errorDescription.getErrorMessage();
    }

    /**
     * @return false if the validation failed
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * this method return a {@link ValidationResult} which is valid. It is normaly used by the Validation
     * implementations which return the {@link ValidationResult} at the end of the validation of an value.
     *
     * @return the valid singleton {@link ValidationResult}
     */
    public static ValidationResult ok() {
        return OK_RESULT;
    }

    /**
     *
     * @param onErrorMessage is set in the returned {@link ValidationResult}
     * @return new instance of an error-{@link ValidationResult} containting the error message
     */
    public static ValidationResult fail(ErrorDescription onErrorMessage) {
        return new ValidationResult(false, onErrorMessage);
    }

    /**
     * This method is very handy if you'd like to catch and violation without the need of using if statements.
     * Example:
     * old school<br>
     * <code>
     * ValidationResult result = validation.test(testedValue);
     * if (!result.isValid()) {
     *     //do something
     * }
     * result = nextValidation.test(testedValue);
     * if (!result.isValid() {
     *     // do something
     * }
     * </code>
     * instead you can use this method.
     * <code>
     * validation.test(testedValue).throwIfInvalid("testedValue");
     * nextValidation.test(otherValue).throwIfInvalid("otherValue");
     * </code>
     * Somewhere for sure you have to catch it and do something.
     * @param myTestedParam the description what was tested. It is used in the exception message
     * @throws ValidationException only if the method {@link #isValid()} returns false
     */
    public void throwIfInvalid(String myTestedParam) throws ValidationException {
        if (!isValid()) {
            throw new ValidationException(myTestedParam, errorDescription);
        }
    }
}
