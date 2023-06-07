package kpfu.itis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    @Schema(example = "af593c1b-d061-4bfc-a0d2-3b9a86844a72",
            name = "id", type = "UUID")
    private UUID id;

    @Schema(example = "ROLE_ADMIN", name = "name", type = "String")
    private String name;
}
