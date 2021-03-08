package at.meks.validation.args;

public class ObjectVerifierTest extends AbstractVerifierTest<Object, ObjectVerifier> {

    private static final PlainObject VALIDATED_VALUE = new PlainObject();

    @Override
    protected ObjectVerifier getVerifierWithValidatedValue() {
        return new ObjectVerifier(VALIDATED_VALUE);
    }

    @Override
    protected ObjectVerifier getVerifierWithNullValue() {
        return new ObjectVerifier(null);
    }

    @Override
    protected Object getValidatedValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Object getOtherValue() {
        return new PlainObject();
    }

}