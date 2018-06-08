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

The main concept of using lambda combined with a fluent interface is from Joel Planes, what can be found [here](https://medium.com/@jplanes/lambda-validations-with-java-8-86aa8143bd9f).

## Compare validation using this library to commonly implemented validation

Using this library the code is much smarter, shorter and easier to read:
````
isNumeric().and(Integer::parseInteger, isBetween(18, 25)).test(ageString).throwIfInvalid("age");
````

Commonly when you implement a validation the code would look like this:

````
if (StringUtils.isNumber(ageString)) {
    int age = Integer.parseInt(ageString);
    if (age < 18 || age > 25) {
        throw new ValidationException("age");
    }
}
````
Just image you have 20+ different input values you have to validate. The code is growing very fast and it will get hard
to maintain is it grows.

###Some more examples from the Quickstart Wiki section
#### combine more validations
#####with and
````
isNotBlank().and(contains("e")).and(isInList(this::getValidCities)).test(cityName);
````
#####with or
isBlank().or(lengthIsMoreThan(12));
#####with or and and
````
isBlank().or(lengthIsMoreThan(12).and(contains("abc")));
````
#### String validation for a number
````
isNumeric().test(ageString).throwIfInvalid("age"); 
````
#### Combine Validations for different Types
````
isNumeric().and(Long::parseLong, isGreaterThan(18L));
````

For more examples and more information please visit the wiki.