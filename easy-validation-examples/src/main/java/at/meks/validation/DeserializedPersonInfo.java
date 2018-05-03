package at.meks.validation;


public class DeserializedPersonInfo {

    private String firstName;
    private String name;
    private String postalCode;
    private String birthDate;
    private String account;
    private String bankCode;
    private String country;
    private String personalNumber;

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    String getBirthDate() {
        return birthDate;
    }

    void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    String getAccount() {
        return account;
    }

    void setAccount(String account) {
        this.account = account;
    }

    String getBankCode() {
        return bankCode;
    }

    void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }


}
