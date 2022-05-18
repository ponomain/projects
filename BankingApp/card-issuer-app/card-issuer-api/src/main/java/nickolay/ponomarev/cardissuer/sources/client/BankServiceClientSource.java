package nickolay.ponomarev.cardissuer.sources.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для пользователя по получению информации банковских сервисов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client/service")
@Tag(name = "client", description = "the Client Bank Service API")
public interface BankServiceClientSource {

    @Operation(summary = "Find Service by Id", description = "Returns single service", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankServiceDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankServiceDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Services", description = "Returns list of services from card", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);
}
