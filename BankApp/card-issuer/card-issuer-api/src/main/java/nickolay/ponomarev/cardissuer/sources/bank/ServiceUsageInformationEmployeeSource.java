package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.ServiceUsageInformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 16.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/information")
@Tag(name = "employee", description = "the Employee Service usage information API")
public interface ServiceUsageInformationEmployeeSource {

    @Operation(summary = "Find Information from Client", description = "Returns service usage information", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ServiceUsageInformationDto.class))))})
    @GetMapping(value = "/client/{id}")
    ResponseEntity<ServiceUsageInformationDto> getFromClient(@PathVariable("id") UUID id);

    @Operation(summary = "Find Information from Service", description = "Returns service usage information", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ServiceUsageInformationDto.class))))})
    @GetMapping(value = "/service/{id}")
    ResponseEntity<ServiceUsageInformationDto> getFromService(@PathVariable("id") UUID id);

    @Operation(summary = "Set validity of usage information", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Service information not found")})
    @PutMapping(path = "/{id}")
    ResponseEntity<ServiceUsageInformationDto> setValidity(@PathVariable("id") UUID id, @RequestParam Boolean valid);

    @Operation(summary = "Deletes a information", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Service information not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
