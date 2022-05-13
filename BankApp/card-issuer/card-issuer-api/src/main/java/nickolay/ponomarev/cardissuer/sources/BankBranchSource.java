package nickolay.ponomarev.cardissuer.sources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nickolay.ponomarev.cardissuer.dto.BankBranchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * API для пользователя по получанию информации отделений банка
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RequestMapping("/branch")
@Tag(name = "branch", description = "the Bank Branch API")
public interface BankBranchSource {

    @Operation(summary = "Find Branch by Id", description = "Returns single branch", tags = {"branch"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BankBranchDto.class))))})
    @GetMapping(value = "/{id}")
    ResponseEntity<BankBranchDto> get(@PathVariable("id") UUID id);

    @Operation(summary = "Find All Branches", description = "Returns list of branches", tags = {"branch"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping(value = "/")
    ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size);

}
