package nickolay.ponomarev.cardissuer.service.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * 27.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Slf4j
@Service
public class AuditService {

    private static final String TEST_USER = "Nick";

    public void logAudit(String screenName, Object[] args ) {
        String userName = getCurrentUser();
        log.info("Audit: {} - {}, args : {}", userName, screenName, Arrays.toString(args));
    }

    public void logResponse(ResponseEntity<Object> response) {
        log.info("Response - {}", response);
    }

    private String getCurrentUser() {
        return TEST_USER; // в будущем будет браться юзер из текущей сессии
    }
}
