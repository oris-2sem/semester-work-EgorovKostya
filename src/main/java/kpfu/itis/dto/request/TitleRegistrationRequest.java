package kpfu.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleRegistrationRequest {
    private MultipartFile file;
    @NotBlank
    private String name;
    @NotEmpty
    private String description;
    @Min(0)
    private Integer pages;
    private String url;
    @Pattern(regexp = "^(Манхва|Манга|Маньхуа)$")
    private String type;
    private String released;
    @Pattern(regexp = "^(Онгоинг|Завершен|Заморожен)$")
    private String status;
    @NotBlank
    private String alternativeName;
    @NotBlank
    private String fullDescription;
    private String link;
    private UUID[] authorIds;
}
