package org.meks.validation.fluent;

public class Bank {

    private String code;

    private boolean active;

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    boolean isActive() {
        return active;
    }

    private void setActive(boolean active) {
        this.active = active;
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
            bank.setCode(code);
            bank.setActive(active);
            return bank;
        }
    }
}
