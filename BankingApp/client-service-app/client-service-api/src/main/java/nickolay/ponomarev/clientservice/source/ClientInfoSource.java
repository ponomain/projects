package nickolay.ponomarev.clientservice.source;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.clientservice.dto.ClientInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 13.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client-info")
@Tag(name = "client-service", description = "the client-service API")
public interface ClientInfoSource {

    @Operation(summary = "Find Client by Id", description = "Returns single client", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientInfoDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<ClientInfoDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Clients", description = "Returns list of clients", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);

    @Operation(summary = "Get email from client", description = "Returns information", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/email")
    ResponseEntity<String> getEmail(@PathVariable("id") UUID id);

    @Operation(summary = "Get 4 last digits of phone number from client", description = "Returns information", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/phone")
    ResponseEntity<String> getPhoneNumber(@PathVariable("id") UUID id);

    @Operation(summary = "Get 4 last digits of inn from client", description = "Returns information", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/inn")
    ResponseEntity<String> getInn(@PathVariable("id") UUID id);

    @Operation(summary = "Add a new client", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created",
                    content = @Content(schema = @Schema(implementation = ClientInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<ClientInfoDto> add(@RequestBody ClientInfoDto clientDto);

    @Operation(summary = "Update an existing client", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(value = "/{id}")
    ResponseEntity<ClientInfoDto> update(@PathVariable("id") UUID id, @RequestBody ClientInfoDto clientDto);

    @Operation(summary = "Deletes a client", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Client not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
