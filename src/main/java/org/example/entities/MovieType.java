package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "MOVIE_TYPE")
public class MovieType {

  @EmbeddedId @NonNull private MovieTypePK id;

  @Column(name = "MT_DESCRIPTION", nullable = false)
  @NonNull
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("MOVIE_ID")
  @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
  private Movie movie;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("TYPE_ID")
  @JoinColumn(name = "TYPE_ID", referencedColumnName = "TYPE_ID")
  private Type type;
}
