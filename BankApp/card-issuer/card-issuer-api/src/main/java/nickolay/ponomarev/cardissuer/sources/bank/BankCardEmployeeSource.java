package nickolay.ponomarev.cardissuer.sources.bank;

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
import java.util.Set;
import java.util.UUID;

/**
 * 24.02.2022
 * API для работника банка по упралению банковскими картами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/employee/card")
@Tag(name = "employee", description = "the Employee Bank Card API")
public interface BankCardEmployeeSource {

    @Operation(summary = "Find Card by Id", description = "Returns single card", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankCardDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankCardDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Get last 4 digits of number of bank card", description = "Returns information", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/number")
    ResponseEntity<String> getNumberOfCard(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Cards", description = "Returns list of cards", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/account/{id}")
    ResponseEntity<List<UUID>> getAllFromAccount(@PathVariable("id") UUID id);

    @Operation(summary = "Add a new Card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card created",
                    content = @Content(schema = @Schema(implementation = BankCardDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<BankCardDto> add(@RequestBody BankCardDto cardDto);

    @Operation(summary = "Update an existing services in card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Card not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(value = "/{id}")
    ResponseEntity<BankCardDto> updateServices(@PathVariable("id") UUID id, @RequestBody Set<UUID> services);

    @Operation(summary = "Deletes a card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Card not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);

    @Operation(summary = "Temporary blocks a card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Card not found")})
    @PostMapping(path = "/{id}/tempblock")
    ResponseEntity<Void> temporaryBlock(@PathVariable("id") UUID id);

    @Operation(summary = "Permanent blocks a card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Card not found")})
    @PostMapping(path = "/{id}/permblock")
    ResponseEntity<Void> permanentBlock(@PathVariable("id") UUID id);

    @Operation(summary = "Unblocks a card", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Card not found")})
    @PostMapping(path = "/{id}/unblock")
    ResponseEntity<Void> unblock(@PathVariable("id") UUID id);
}
