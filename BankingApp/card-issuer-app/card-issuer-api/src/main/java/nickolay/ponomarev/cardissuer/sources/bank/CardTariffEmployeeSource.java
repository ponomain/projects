package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для работника банка по управлению тарифами карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/employee/tariff")
@Tag(name = "employee", description = "the Employee Tariff API")
public interface CardTariffEmployeeSource {

    @Operation(summary = "Find Tariff by Id", description = "Returns single tariff", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CardTariffDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<CardTariffDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Tariffs", description = "Returns list of tariffs", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);

    @Operation(summary = "Add a new tariff", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tariff created",
                    content = @Content(schema = @Schema(implementation = CardTariffDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<CardTariffDto> add(@RequestBody CardTariffDto cardTariffDto);

    @Operation(summary = "Deletes a tariff", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Tariff not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
