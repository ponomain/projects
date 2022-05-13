package nickolay.ponomarev.cardissuer.sources.bank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.AddressBranchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 24.02.2022
 * API для работника банка по управлению адресами клиентов и отделений банка
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/address/branch")
@Tag(name = "employee", description = "the Employee Address API")
public interface AddressEmployeeSource {

    @Operation(summary = "Find Address by Id", description = "Returns single address from branch", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AddressBranchDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<AddressBranchDto> getFromBranch(@PathVariable("id") UUID id);

    @Operation(summary = "Add a new address", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created",
                    content = @Content(schema = @Schema(implementation = AddressBranchDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<AddressBranchDto> add(@RequestBody AddressBranchDto addressBranchDto);

    @Operation(summary = "Deletes a address", description = "", tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Address not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);

}
