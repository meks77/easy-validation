package at.meks.validation;

import at.meks.validation.args.AbstractVerifier;

import java.util.function.Consumer;

public class BehaviorDrivenTesting {

    public static class When {

        private IllegalArgumentException occuredException;

        public When(IllegalArgumentException occuredException) {
            this.occuredException = occuredException;
        }

        public When() {
        }

        public void then(Consumer<IllegalArgumentException> exceptionConsumer) {
            exceptionConsumer.accept(occuredException);
        }

    }

    public static class WhenAndAnd<T, X extends AbstractVerifier<T, X>> {

        private X verifier;

        public WhenAndAnd(X verifier) {
            this.verifier = verifier;
        }

        public When when(Consumer<X> invocationOnConsumer) {
            try {
                invocationOnConsumer.accept(verifier);
                return new When();
            } catch (IllegalArgumentException e) {
                return new When(e);
            }
        }

        public WhenAndAnd<T, X> and(Consumer<X> invocationOnConsumer) {
            invocationOnConsumer.accept(verifier);
            return this;
        }

        public WhenAndAnd<T, X> and(Runnable invocationOnConsumer) {
            invocationOnConsumer.run();
            return this;
        }

    }

    public static <T, X extends AbstractVerifier<T, X>> WhenAndAnd<T, X> given(X verifier) {
        return new WhenAndAnd(verifier);
    }

}
