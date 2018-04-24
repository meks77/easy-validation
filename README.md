# easy-validation
Very easy way to validate simple and complex objects by using an fluent interface.


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
Currently this project is under development in alpha phase. 
So many things are open. First I just developed the interface usage. 
Till now the api was to heavy change while I was fighting with problems with generics when using the fluent interface.

The next steps will be:
* cleanup of the code
* implement tests
* setting up SonarQube for code statistics
* add the possibility to either use error codes or not
* add the possibility to user custom error messages for the validations
* maybe add an api to setup if the validation should't stop at the first error. That's very complex because of
  * dependencies between validation steps(eg. if validation x fails validation y and z must not be executed)
  * and I am sure because of the things I have currently not in my mind 
* adding common validation steps
  * Date validations(Date, LocalDate, LocalDateTime,...)
  * more List validations(if something comes to my mind)
  * other
  
The first stable release will be 1.0.0.

## User Guide
Todo! 
Currently there is a Unit test class which shows the usage.
