// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.boundary.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator
    implements ConstraintValidator<LastName, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    final var isNullValid = true;
    return edu.san.authentication.control.LastName.isValid(value, isNullValid);
  }

}