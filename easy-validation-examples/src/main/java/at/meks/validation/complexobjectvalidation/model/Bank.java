package at.meks.validation.complexobjectvalidation.model;

public class Bank {

    private String code;

    private boolean active;

    String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public static final class BankBuilder {
        private String code;
        private boolean active;

        private BankBuilder() {
        }

        public static BankBuilder aBank() {
            return new BankBuilder();
        }

        public BankBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public BankBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public Bank build() {
            Bank bank = new Bank();
            bank.code = code;
            bank.active = active;
            return bank;
        }
    }
}
