package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для работника банка по управлению сервисами банка
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/employee/service")
@Tag(name = "employee", description = "the Employee Bank Service API")
public interface BankServiceEmployeeSource {

    @Operation(summary = "Find Service by Id", description = "Returns single service", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankServiceDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankServiceDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Services", description = "Returns list of services", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);

    @Operation(summary = "Add a new service", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service created",
                    content = @Content(schema = @Schema(implementation = BankServiceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<BankServiceDto> add(@RequestBody BankServiceDto bankServiceDto);

    @Operation(summary = "Update an existing end date of service", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Service not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(value = "/{id}")
    ResponseEntity<BankServiceDto> update(@PathVariable("id") UUID id, @RequestParam("endDate") String date);

    @Operation(summary = "Deletes a service", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Service not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
