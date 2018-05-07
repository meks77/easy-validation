package at.meks.validation.complexobjectvalidation.model;


public class DeserializedPersonInfo {

    private String firstName;
    private String name;
    private String postalCode;
    private String birthDate;
    private String account;
    private String bankCode;
    private String country;
    private String personalNumber;

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccount() {
        return account;
    }

    void setAccount(String account) {
        this.account = account;
    }

    public String getBankCode() {
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
