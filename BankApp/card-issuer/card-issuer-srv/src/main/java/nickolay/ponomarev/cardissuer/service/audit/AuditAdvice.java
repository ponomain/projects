package nickolay.ponomarev.cardissuer.service.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

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
    private AuditService auditService;

    @Pointcut("@annotation(audit)")
    public void auditableMethods(Audit audit) {}

    @Around(value = "auditableMethods(audit)", argNames = "joinPoint,audit")
    public Object beforeLogger(ProceedingJoinPoint joinPoint, Audit audit)  throws  Throwable{
        Object[] args = joinPoint.getArgs();
        auditService.logAudit(audit.value(), args);
        ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
        auditService.logResponse(response);
        return joinPoint.proceed();
    }

}
