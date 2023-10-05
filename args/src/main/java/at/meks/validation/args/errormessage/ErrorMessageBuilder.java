package at.meks.validation.args.errormessage;

import java.util.Optional;
import java.util.function.Supplier;

public class ErrorMessageBuilder {

    private String argumentName;
    private String messageBundleKey;

    private Supplier<String> messageSupplier;
    private Object argumentValue;
    private Object furtherMessageArg;

    public void withArgumentName(String argumentName) {
        this.argumentName = argumentName;
    }

    public void withArgumentValue(Object value) {
        argumentValue = value;
    }

    public void withMessageBundleKey(String messageBundleKey) {
        this.messageBundleKey = messageBundleKey;
    }

    public void withMessageSupplier(Supplier<String> messageSupplier) {
        this.messageSupplier = messageSupplier;
    }

    public Optional<ErrorMessage> build() {
        if (messageSupplier != null ) {
            return Optional.of(forPlainMessage(messageSupplier));
        } else if (argumentName != null && messageBundleKey != null) {
            return Optional.of(forResourceBundleMessage(messageBundleKey));
        }
        return Optional.empty();
    }

    private ErrorMessage forResourceBundleMessage(String messageKey) {
        return new ResourceBundleErrorMessage(messageKey, argumentName, argumentValue, furtherMessageArg);
    }

    private ErrorMessage forPlainMessage(Supplier<String> messageSupplier) {
        return new PlainErrorMessage(messageSupplier);
    }

    public void withFurtherMessageFormatArg(Object furtherMessageArg) {
        this.furtherMessageArg = furtherMessageArg;
    }
}
