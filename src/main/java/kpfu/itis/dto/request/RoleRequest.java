package kpfu.itis.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {


    @NotBlank
    @Pattern(regexp = "^ROLE_[A-Z]+$")
    @Schema(description = "Название роли", example = "ROLE_MANAGER")
    private String name;
}
