package at.meks.validation.args.errormessage;

import java.util.function.Supplier;

public class PlainErrorMessage implements ErrorMessage {

    private final Supplier<String> messageSupplier;

    public PlainErrorMessage(Supplier<String> messageSupplier) {
        this.messageSupplier = messageSupplier;
    }

    @Override
    public String asText() {
        return messageSupplier.get();
    }
}
