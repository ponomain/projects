package nickolay.ponomarev.cardissuer.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 01.03.2022
 * Valid pin code
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Constraint(validatedBy = PinCodeValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPinCode {
    String message() default "Pin code can be only 4 numbers and cannot contains other symbols";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
