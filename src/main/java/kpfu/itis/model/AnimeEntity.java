package kpfu.itis.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AnimeEntity {

    private Long id;
    private String code;
    private String ruName;
    private String enName;
    private String imageUrl;
    private String fullString;
    private Integer episodes;
    private Integer duration;
    private Integer favourites;
    private String description;
    private String released;
    private String status;
    private List<String> genres;
}
