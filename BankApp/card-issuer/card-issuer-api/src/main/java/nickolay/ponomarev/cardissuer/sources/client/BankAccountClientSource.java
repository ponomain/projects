package nickolay.ponomarev.cardissuer.sources.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * 20.03.2022
 * API для клиента по получению информации из аккаунта
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client/")
@Tag(name = "client", description = "the Bank Account Client API")
public interface BankAccountClientSource {

    @Operation(summary = "Get account from bank client", description = "Returns information", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/accounts")
    ResponseEntity<List<UUID>> getAccounts(@PathVariable("id") UUID id);

    @Operation(summary = "Find Service by Id", description = "Returns single service", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankAccountDto.class))))})
    @GetMapping(value = "/account/{id}")
    ResponseEntity<BankAccountDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Get information from account", description = "Returns information", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/account/{id}/information")
    ResponseEntity<String> getInformationFromAccount(@PathVariable("id") UUID id);


}
