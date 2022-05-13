package nickolay.ponomarev.cardissuer.controller.bank;

import nickolay.ponomarev.cardissuer.dto.ClientInfoDto;
import nickolay.ponomarev.cardissuer.feign.FeignClientInfo;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.ClientInfoSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 13.04.2022
 * card-issuer
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
public class ClientInfoController implements ClientInfoSource {

    @Autowired
    private FeignClientInfo feignClient;

    @Override
    @Audit("Получение информации по клиенту")
    public ResponseEntity<ClientInfoDto> getClient(@PathVariable("id") UUID id) {
        return feignClient.getClientInfoById(id);
    }
}
