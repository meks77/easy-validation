package at.meks.validation.args;

public class ObjectVerifierTest extends AbstractVerifierTest<Object, ObjectVerifier> {

    private static final PlainObject VALIDATED_VALUE = new PlainObject();
    private static final PlainObject OTHER_VALUE = new PlainObject();

    @Override
    protected ObjectVerifier getVerifierFor(Object value) {
        return new ObjectVerifier(value);
    }

    @Override
    protected ObjectVerifier getVerifierWithValidatedValue() {
        return new ObjectVerifier(VALIDATED_VALUE);
    }

    @Override
    protected Object getValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Object getOtherValue() {
        return OTHER_VALUE;
    }

}