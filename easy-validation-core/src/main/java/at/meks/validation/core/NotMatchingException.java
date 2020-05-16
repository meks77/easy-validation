package at.meks.validation.core;

public class NotMatchingException extends RuntimeException {

    private Exception cause;

    <T> NotMatchingException(Exception cause) {
        this.cause = cause;
    }

    @Override
    public Exception getCause() {
        return cause;
    }

}
