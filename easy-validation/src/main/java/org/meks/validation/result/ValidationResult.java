package org.meks.validation.result;

public class ValidationResult {

    private static final ValidationResult OK_RESULT = new ValidationResult(true, null);

    private final boolean valid;
    private final ErrorDescription errorDescription;

    private ValidationResult(boolean valid, ErrorDescription errorDescription) {
        this.valid = valid;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        if (errorDescription instanceof ErrorDescriptionWithCode) {
            return ((ErrorDescriptionWithCode) errorDescription).getErrorCode();
        }
        return null;
    }

    public String getErrorMessage() {
        return errorDescription.getErrorMessage();
    }

    public boolean isValid() {
        return valid;
    }

    public static ValidationResult ok() {
        return OK_RESULT;
    }

    public static ValidationResult fail(ErrorDescription onErrorMessage) {
        return new ValidationResult(false, onErrorMessage);
    }

    public void throwIfInvalid(String myTestedParam) throws ValidationException {
        if (!isValid()) {
            throw new ValidationException(myTestedParam, errorDescription);
        }
    }
}
