package nickolay.ponomarev.clientservice.source;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.clientservice.dto.IdentityDocumentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/client-info/document")
@Tag(name = "client-service", description = "the  Identity Document API")
public interface IdentityDocumentSource {

    @Operation(summary = "Find Document by Id", description = "Returns single document", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IdentityDocumentDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<IdentityDocumentDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Documents", description = "Returns list of documents", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);

    @Operation(summary = "Add a new documents", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Document created",
                    content = @Content(schema = @Schema(implementation = IdentityDocumentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    ResponseEntity<IdentityDocumentDto> add(@RequestBody IdentityDocumentDto identityDocumentDto);

    @Operation(summary = "Get 3 last digits of number of document", description = "Returns information", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/{id}/number")
    ResponseEntity<String> getNumber(@PathVariable("id") UUID id);

    @Operation(summary = "Update an existing document", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Document not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(value = "/{id}")
    ResponseEntity<IdentityDocumentDto> update(@PathVariable("id") UUID id, @RequestBody IdentityDocumentDto identityDocumentDto);

    @Operation(summary = "Deletes a document", description = "", tags = {"client-service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Document not found")})
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
