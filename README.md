[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=bugs)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=code_smells)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=coverage)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=sqale_index)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=security_rating)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
# easy-validation 

Very easy way to validate simple and complex objects by using an fluent interface.

The main concept of using lambda combinded with a fluent interface is from Joel Planes, what can be found [here](https://medium.com/@jplanes/lambda-validations-with-java-8-86aa8143bd9f).

## Goals
- simple reusable API for validation (use of the fluent interface)
- extensible by custom validations without the need of forking this repo
- prefer java control structure against complex configuration, what gives quite more flexibility e.g. for caching of objects which needs to be fetched from somewhere
- validators should be usable in dependency-injection environments
- for legacy systems it should be possible to use error codes for each single validation
- setup validation once, execute it many times for different objects
- possibility to fail fast on error

## Why not using
### javax Bean validation
Because I need different validation for the same object type, depending on the business workflow
### [dOOv](https://github.com/lesfurets/dOOv)
I thought its too complex to use. I would like to use it easier without that someone is overwhelmed by how to use it.
### [neormind fluent validator](https://github.com/neoremind/fluent-validator)
* I was afraid of the chinese documentation of the api.
* I wanted to have more control 
  * at which step a validation is executed  
  * if a validation is executed
  
I did not like to have a fluent interface for the setting up the validation, while I can't use it because I have a restriction if a validation should be executed.

## State
Currently this project is under development. I try to finalize the first release.

Features for future releases:
The next steps will be:
* add the possibility to use custom error messages for the validations
* add the possibility to add translated messages for other languages beside english
* adding common validation steps
  * Date validations(Date, LocalDate, LocalDateTime,...)
  * more List validations(if something comes to my mind)
  * other
  
The first stable release will be 1.0.0.

## Quickstart
### Maven Dependency
````
<dependency>
    <groupId>at.meks</groupId>
    <artifactId>easy-validation</artifactId>
    <version>x.x.x</version>
</dependency>
````

### Simple Examples
Here are just some simple Examples listed. You will also find them in the code of the example module.

#### Validation getting a result
````
ValidationResult result = ObjectValidations.notNull().test(userInput);
if (!result.isValid()) {
    System.out.println(result.getErrorMessage());
}
````

#### Validation throwing an error
This is usefull to stop further validation and action if an validation error occurs.
````
isNotBlank().test(file.getName()).throwIfInvalid("fileName");
isGreaterThan(10L).test(file.length()).throwIfInvalid("fileSize");
````
#### combine more validations
````
ObjectValidations.<String>notNull().and(isNotBlank()).and(contains("e")).and(isInList(this::getValidCities)).test(cityName);
````
#### String validation for a number
````
StringValidations.isNumeric().test(ageString).throwIfInvalid("age"); 
````
#### Combine Validations for different Types
````
isNumeric().and(Long::parseLong, isGreaterThan(18L));
````
#### Validation of a complex ojbect
First here you can see the setup of the configuration. That's usefull if 
* you plan to validate more objects of the same type with the same rules
* you want to separate the code a little bit to get it more readable

Setup of the validation rules:
````
private void setupValidationConfig() {
    nameValidation = isNotBlank().and(lengthIsMoreThan(1));
    postalCodeQuickValidation = isNotBlank().and(lengthIsBetween(4, 8));
    postalCodeSlowValidation = isInArray(this::getValidPostalCodes);
    birthDayStringValidation = isNotBlank().and(isDate(DEFAULT_DATE_FORMAT));
    birthDayDateValidation = isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0));
    accountNrStringValidation = isNotBlank().and(isNumeric()).and(containsNotOnly("0"));
    accountSlowValidation = forType(Account.class, hasMinSize(1)).and(hasMaxSize(5))
            .and(onProperty(Account::isActive, containsOnly(true)))
            .and(onProperty(Account::isOnBadList, containsOnly(false)));

    bankCodeValidation = isNotBlank().and(isNumeric()).and(hasLength(6));
    bankCodeSlowValidation = onProperty(Bank::isActive, ListValidations.isNotEmpty().and(containsOnly(true)));
}
````
execution of the validation rules:
````
void validatePerson(DeserializedPersonInfo personInfo) throws ValidationException {
        nameValidation.test(personInfo.getFirstName()).throwIfInvalid("firstName");
        nameValidation.test(personInfo.getName()).throwIfInvalid("name");
        postalCodeQuickValidation.test(personInfo.getPostalCode()).throwIfInvalid("postalCode");
        birthDayStringValidation.test(personInfo.getBirthDate()).throwIfInvalid("birthDay");
        LocalDateTime birthDayDate = LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(personInfo.getBirthDate()));
        birthDayDateValidation.test(birthDayDate).throwIfInvalid("birthDay");
        accountNrStringValidation.test(personInfo.getAccount()).throwIfInvalid("account");
        bankCodeValidation.test(personInfo.getBankCode());

        postalCodeSlowValidation.test(personInfo.getPostalCode()).throwIfInvalid("postalCode");
        accountSlowValidation.test(getAccountsOfPersonOfSlowService()).throwIfInvalid("account");
        bankCodeSlowValidation.test(getBankCodeOfPersonOfSlowService()).throwIfInvalid("bankCode");
    }
```` 
## Releases
### 1.0.0-M1
First release. The API still can change massivly if it comes to the mind of the users that it is easy to use. Furthermore more validations will be added if necessary for the first release.


