package org.meks.validation.fluent;

class Account {

    private boolean active;

    private boolean onBadList;

    boolean isActive() {
        return active;
    }

    boolean isOnBadList() {
        return onBadList;
    }

    public static final class AccountBuilder {
        private boolean active;
        private boolean onBadList;

        private AccountBuilder() {
        }

        static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        AccountBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public AccountBuilder withOnBadList(boolean onBadList) {
            this.onBadList = onBadList;
            return this;
        }

        Account build() {
            Account account = new Account();
            account.active = this.active;
            account.onBadList = this.onBadList;
            return account;
        }
    }
}
