package at.meks.validation;

import at.meks.validation.result.ValidationException;
import at.meks.validation.validations.date.DateValidations;
import at.meks.validation.validations.list.ListValidations;
import at.meks.validation.validations.string.StringValidations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An example how a validator can be implemented for a complex object.
 */
class PersonInfoValidatorNotSeparatedConfig {

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;

    private String[] getValidPostalCodes() {
        return new String[]{"1010", "1020", "1030", "1040", "1050"};
    }

    void validatePerson(DeserializedPersonInfo personInfo) throws ValidationException {
        StringValidations.isNotBlank().and(StringValidations.lengthIsMoreThan(1)).test(personInfo.getFirstName()).throwIfInvalid("firstName");
        StringValidations.isNotBlank().and(StringValidations.lengthIsMoreThan(1)).test(personInfo.getName()).throwIfInvalid("name");
        StringValidations.isNotBlank().and(StringValidations.lengthIsBetween(4, 8)).and(StringValidations.isInArray(this::getValidPostalCodes))
                .test(personInfo.getPostalCode()).throwIfInvalid("postalCode");

        StringValidations.isNotBlank().and(StringValidations.isDate(DEFAULT_DATE_FORMAT)).test(personInfo.getBirthDate()).throwIfInvalid("birthDay");
        LocalDateTime birthDayDate = LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(personInfo.getBirthDate()));
        DateValidations.isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0)).test(birthDayDate).throwIfInvalid("birthDay");

        StringValidations.isNotBlank().and(StringValidations.isNumeric()).and(StringValidations.containsNotOnly("0")).test(personInfo.getAccount()).throwIfInvalid("account");
        ListValidations.forType(Account.class, ListValidations.hasMinSize(1)).and(ListValidations.hasMaxSize(5))
                .and(ListValidations.onProperty(Account::isActive, ListValidations.containsOnly(true)))
                .and(ListValidations.onProperty(Account::isOnBadList, ListValidations.containsOnly(false)))
                .test(getAccountsOfPersonOfSlowService()).throwIfInvalid("account");

        StringValidations.isNotBlank().and(StringValidations.isNumeric()).and(StringValidations.hasLength(6)).test(personInfo.getBankCode());
        ListValidations.onProperty(Bank::isActive, ListValidations.isNotEmpty().and(ListValidations.containsOnly(true)))
                .test(getBankCodeOfPersonOfSlowService()).throwIfInvalid("bankCode");
    }

    private List<Bank> getBankCodeOfPersonOfSlowService() {
        return Collections.emptyList();
    }

    private List<Account> getAccountsOfPersonOfSlowService() {
        ArrayList<Account> accounts = new ArrayList<>();
        Collections.addAll(accounts, Account.AccountBuilder.anAccount().withActive(true).build());
        return accounts;
    }
}
