package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "MOVIE")
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @Column(name = "MOVIE_ID", unique = true, nullable = false, length = 10)
    @NonNull
    private String id;

    @Column(name = "ACTOR", nullable = false)
    @NonNull
    private String actor;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    @NonNull
    private String content;

    @Column(name = "DIRECTOR", nullable = false)
    @NonNull
    private String director;

    @Column(name = "DURATION", nullable = false)
    @NonNull
    private double duration;

    @Column(name = "FROM_DATE", nullable = false)
    @NonNull
    private LocalDate fromDate;

    @Column(name = "TO_DATE", nullable = false)
    @NonNull
    private LocalDate toDate;

    @Column(name = "MOVIE_PRODUCTION_COMPANY", nullable = false)
    @NonNull
    private String movieProductionCompany;

    @Column(name = "VERSION", nullable = false)
    @NonNull
    private String version;

    @Column(name = "MOVIE_NAME_ENG", nullable = false, unique = true)
    @NotBlank
    private String movieNameEng;

    @Column(name = "MOVIE_NAME_VN", nullable = false, unique = true)
    @NotBlank
    private String movieNameVn;

    @Column(name = "LARGE_IMAGE", nullable = false)
    @NonNull
    private String largeImage;

    @Column(name = "SMALL_IMAGE", nullable = false)
    @NonNull
    private String smallImage;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieType> movieTypeList;
}
