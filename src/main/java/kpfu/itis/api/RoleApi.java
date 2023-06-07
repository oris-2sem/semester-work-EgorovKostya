package kpfu.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kpfu.itis.dto.request.RoleRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.dto.response.RoleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/roles")
public interface RoleApi {


    @Operation(summary = "Создание роли")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успех, роль создана",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка в создании роли",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка в создании роли",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    RoleResponse createRole(@Valid @RequestBody RoleRequest request);



    @Operation(summary = "Удаление роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль удалена"),
            @ApiResponse(responseCode = "400", description = "Введен некорректный Id"),
            @ApiResponse(responseCode = "500", description = "Не удалось удалить роль")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{roleId}")
    void deleteRoleById(@PathVariable UUID roleId);


    @Operation(summary = "Получение всех ролей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RoleResponse.class)))
                    }),
            @ApiResponse(responseCode = "500", description = "Ошибка во время получения списка ролей"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<RoleResponse> getRoles();

    @Operation(summary = "Получение роли по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Роль получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введен некорректный Id"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Роль с таким id не найдена",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),

            @ApiResponse(responseCode = "500", description = "Ошибка во время получения роли")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{roleId}")
    RoleResponse getRoleById(@PathVariable UUID roleId);


    @Operation(summary = "Обновление роли")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные роли обновлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Введен некорректный Id"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка в обновлении роли",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{roleId}")
    RoleResponse updateRoleById(@PathVariable UUID roleId, @Valid @RequestBody RoleRequest request);
}
