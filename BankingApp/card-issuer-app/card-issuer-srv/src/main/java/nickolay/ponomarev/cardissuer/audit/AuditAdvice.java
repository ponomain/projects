package nickolay.ponomarev.cardissuer.audit;

import nickolay.ponomarev.notification.dto.NotificationDto;
import nickolay.ponomarev.notification.source.NotificationProducer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 27.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Aspect
@Component
public class AuditAdvice {

    @Autowired
    private NotificationProducer notificationProducer;

    @Pointcut("@annotation(audit)")
    public void auditableMethods(Audit audit) {}

    @Around(value = "auditableMethods(audit)", argNames = "joinPoint,audit")
    public Object beforeLogger(ProceedingJoinPoint joinPoint, Audit audit)  throws  Throwable{
        NotificationDto dto = new NotificationDto();
        Object[] args = joinPoint.getArgs();
        String theme = audit.value() + " with args: " + Arrays.toString(args);
        dto.setTheme(theme);
        ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
        dto.setMessage(String.valueOf(response));
        notificationProducer.send(dto);
        return joinPoint.proceed();
    }

}
