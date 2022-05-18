package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для работника банка по управлению банковскими аккаунтами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/employee/account")
@Tag(name = "employee", description = "the Employee Bank Account API")
public interface BankAccountEmployeeSource {

    @Operation(summary = "Find Account by Id", description = "Returns single account", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankAccountDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankAccountDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Get last 4 digits of account number", description = "Returns information", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/number")
    ResponseEntity<String> getAccountNumber(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Accounts", description = "Returns list of accounts", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/accounts")
    ResponseEntity<List<UUID>> getAllFromClient(@PathVariable("id") UUID id);

    @Operation(summary = "Deletes a account", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Account not found")})
    @DeleteMapping(path = "{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);

    @Operation(summary = "Block or unblocks a account", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Account not found")})
    @PostMapping(path = "/{id}/block")
    ResponseEntity<Void> blockAccountOperation(@PathVariable("id") UUID id, @RequestParam Boolean blocked);
}
