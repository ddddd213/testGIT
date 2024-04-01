package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTypePK implements Serializable {
  @Column(name = "TYPE_ID", nullable = false)
  private Integer typeId;

  @Column(name = "MOVIE_ID", length = 10, nullable = false)
  private String movieId;
}
