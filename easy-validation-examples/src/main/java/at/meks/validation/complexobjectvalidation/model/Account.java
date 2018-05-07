package at.meks.validation.complexobjectvalidation.model;

public class Account {

    private boolean active;

    private boolean onBadList;

    public boolean isActive() {
        return active;
    }

    public boolean isOnBadList() {
        return onBadList;
    }

    public static final class AccountBuilder {
        private boolean active;
        private boolean onBadList;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public AccountBuilder withOnBadList(boolean onBadList) {
            this.onBadList = onBadList;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.active = this.active;
            account.onBadList = this.onBadList;
            return account;
        }
    }
}
