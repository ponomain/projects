package nickolay.ponomarev.cardissuer.feign;

import nickolay.ponomarev.cardissuer.dto.ClientInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.ws.rs.PathParam;
import java.util.UUID;

/**
 * 13.04.2022
 * card-issuer
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@FeignClient(value = "client-service",url = "http://client-service:8084/client-info/")
public interface FeignClientInfo {
    @GetMapping("/{id}")
    ResponseEntity<ClientInfoDto> getClientInfoById(@PathParam("id") UUID id);
}
