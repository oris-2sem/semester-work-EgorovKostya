package kpfu.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimeSearchWithFilterRequest {

    private String name;
    private Integer minYear;
    private Integer maxYear;
    private Integer minChapters;
    private Integer maxChapters;
    private String[] genres;
    private String status;
}
