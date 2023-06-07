package kpfu.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kpfu.itis.dto.request.AuthorRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.exception.database.AuthorAlreadyExistsException;
import kpfu.itis.exception.database.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Tag(name = "Author API", description = "Api for author entity")
@RequestMapping("/authors")
public interface AuthorApi {

    @Operation(summary = "Получение всех авторов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AuthorResponse.class)))
                    }),
            @ApiResponse(responseCode = "500", description = "Ошибка во время получения списка авторов"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<AuthorResponse> getAuthors();


    @Operation(summary = "Получение автора по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автор получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Введен некорректный Id"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автор с таким id не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),

            @ApiResponse(responseCode = "500", description = "Ошибка во время получения автора")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{authorId}")
    AuthorResponse getAuthorById(@Parameter(name = "authorId"
            , description = "UUID: version 4"
            , example = "8b8f7c58-db93-4709-b02e-f3da44d76e35")
                                 @PathVariable UUID authorId);

    @Operation(summary = "Регистрация автора")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успех, автор создан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка в создании автора",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AuthorResponse createAuthor(@Valid @RequestBody AuthorRequest request);

    @Operation(summary = "Удаление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автор удален"),
            @ApiResponse(responseCode = "400", description = "Введен некорректный Id"),
            @ApiResponse(responseCode = "500", description = "Не удалось удалить автора")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{authorId}")
    void deleteAuthorById(@Parameter(name = "authorId"
            , description = "UUID: version 4"
            , example = "8b8f7c58-db93-4709-b02e-f3da44d76e35")
                          @PathVariable UUID authorId);


    @Operation(summary = "Обновление автора")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные автора обновлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Введен некорректный Id"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка в обновлении автора",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{authorId}")
    AuthorResponse updateAuthorById(@Parameter(name = "authorId"
            , description = "UUID: version 4"
            , example = "8b8f7c58-db93-4709-b02e-f3da44d76e35")
                                    @PathVariable UUID authorId,
                                    @Valid @RequestBody AuthorRequest request);
}
