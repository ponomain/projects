package nickolay.ponomarev.cardissuer.sources.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для пользователя по получению информации по банковским картам
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client/card")
@Tag(name = "client", description = "the Client Bank Card API")
public interface BankCardClientSource {

    @Operation(summary = "Find Card by Id", description = "Returns single card", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankCardDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankCardDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Get requisites from bank card", description = "Returns information", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/requisites")
    ResponseEntity<String> getRequisites(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Cards", description = "Returns list of cards from account", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/accounts/{id}/cards")
    ResponseEntity<List<UUID>> getAll(@PathVariable("id") UUID id);

    @Operation(summary = "Add bank service to card", description = "", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service added"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping(value = "/{id}/addService/{service-id}")
    ResponseEntity<BankCardDto> addServiceToCard(@PathVariable("id") UUID id, @PathVariable("service-id") UUID serviceId);

    @Operation(summary = "Blocks a card", description = "", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Card not found")})
    @PostMapping(path = "/{id}")
    ResponseEntity<Void> block(@PathVariable("id") UUID id);

    @Operation(summary = "Change tariff in the card", description = "", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tariff changed"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping(value = "/{id}/changeTariff/{tariff-id}")
    ResponseEntity<BankCardDto> changeTariff(@PathVariable("id") UUID id, @PathVariable("tariff-id") UUID tariffId);

    @Operation(summary = "Change pin code in the card", description = "", tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pin code changed"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping(value = "/{id}/changePinCode")
    ResponseEntity<BankCardDto> changePinCode(@PathVariable("id") UUID id, @RequestParam String pinCode);
}
