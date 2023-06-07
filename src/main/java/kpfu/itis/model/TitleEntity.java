package kpfu.itis.model;

import kpfu.itis.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "title")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TitleEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "released")
    private Date released;

    @Column(name = "status")
    private String status;

    @Column(name = "alternative_name")
    private String alternativeName;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "link")
    private String link;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany()
    @JoinTable(name = "author_title",
            joinColumns = @JoinColumn(name = "title_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<AuthorEntity> authors = new HashSet<>();


    @OneToMany()
    @JoinTable(name = "title_comment",
            joinColumns = @JoinColumn(name = "title_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<CommentEntity> comments = new ArrayList<>();

}
