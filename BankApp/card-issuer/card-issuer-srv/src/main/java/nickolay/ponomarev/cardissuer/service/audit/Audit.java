package nickolay.ponomarev.cardissuer.service.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 27.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Audit {
    String value();
}
