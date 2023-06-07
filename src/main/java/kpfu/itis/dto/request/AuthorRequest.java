package kpfu.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    @NotBlank
    @Schema(description = "Имя автора", example = "Makoto Sinkai")
    private String name;

    @Min(value = 0, message = "Can't be negative")
    @Max(value = 160, message = "A person doesn't live that long")
    private Integer age;
}
