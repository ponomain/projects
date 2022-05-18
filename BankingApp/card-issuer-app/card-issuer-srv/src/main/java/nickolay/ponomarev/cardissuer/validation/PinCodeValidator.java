package nickolay.ponomarev.cardissuer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 01.03.2022
 * Pin code validatir
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class PinCodeValidator implements ConstraintValidator<ValidPinCode, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() == 4 && s.matches("^[0-9]*$");
    }
}
