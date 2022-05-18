package nickolay.ponomarev.clientservice.source;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.clientservice.dto.AddressClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/address/client")
@Tag(name = "client-service", description = "the Employee Address API")
public interface AddressClientSource {

    @Operation(summary = "Find Address by Id", description = "Returns single address from branch", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AddressClientDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<AddressClientDto> getFromClient(@PathVariable("id") UUID id);

    @Operation(summary = "Add a new address", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created",
                    content = @Content(schema = @Schema(implementation = AddressClientDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<AddressClientDto> add(@RequestBody AddressClientDto addressClientDto);

    @Operation(summary = "Deletes a address", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Address not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
