/**
 * In the sub-package you'll find the validations.
 *
 * There is allways a secone class where you can provide an additional error-code for each validation. That's often
 * needen in legacy systems.
 *
 * Each validation method exists twice if the method has an argument(except the error code), where you can define a
 * supplier for the case if  getting the argument value is expensive and should be done when the validation is invoked,
 * and not before.
 *
 */
package at.meks.validation.validations;
