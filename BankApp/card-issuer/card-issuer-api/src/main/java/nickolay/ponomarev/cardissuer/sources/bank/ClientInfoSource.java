package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import nickolay.ponomarev.cardissuer.dto.ClientInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * 13.04.2022
 * card-issuer
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client-info/")
@Tag(name = "client-info", description = "the Client Info API")
public interface ClientInfoSource {

    @Operation(summary = "Find Client by Id", description = "Returns single client", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientInfoDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<ClientInfoDto> getClient(@PathVariable("id") UUID id);
}
