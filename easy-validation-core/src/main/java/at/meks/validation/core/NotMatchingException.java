package at.meks.validation.core;

public class NotMatchingException extends RuntimeException {

    NotMatchingException(Exception cause) {
        super(cause);
    }

}
