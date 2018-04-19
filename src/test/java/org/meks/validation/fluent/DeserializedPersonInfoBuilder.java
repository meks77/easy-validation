package org.meks.validation.fluent;

public final class DeserializedPersonInfoBuilder {
    private String firstName;
    private String name;
    private String postalCode;
    private String birthDate;
    private String account;
    private String bankCode;
    private String country;
    private String personalNumber;

    private DeserializedPersonInfoBuilder() {
    }

    public static DeserializedPersonInfoBuilder aDeserializedPersonInfo() {
        return new DeserializedPersonInfoBuilder();
    }

    public DeserializedPersonInfoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DeserializedPersonInfoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DeserializedPersonInfoBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public DeserializedPersonInfoBuilder withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public DeserializedPersonInfoBuilder withAccount(String account) {
        this.account = account;
        return this;
    }

    public DeserializedPersonInfoBuilder withBankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public DeserializedPersonInfoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public DeserializedPersonInfoBuilder withPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }

    public DeserializedPersonInfo build() {
        DeserializedPersonInfo deserializedPersonInfo = new DeserializedPersonInfo();
        deserializedPersonInfo.setFirstName(firstName);
        deserializedPersonInfo.setName(name);
        deserializedPersonInfo.setPostalCode(postalCode);
        deserializedPersonInfo.setBirthDate(birthDate);
        deserializedPersonInfo.setAccount(account);
        deserializedPersonInfo.setBankCode(bankCode);
        deserializedPersonInfo.setCountry(country);
        deserializedPersonInfo.setPersonalNumber(personalNumber);
        return deserializedPersonInfo;
    }
}
