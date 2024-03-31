package org.example.entities;

import jakarta.persistence.*;
import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TYPE")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TYPE_ID", unique = true, nullable = false)
  private Integer id;

  @Column(name = "TYPE_NAME", unique = true, nullable = false)
  @NotBlank(message = "Type name cannot be blank")
  private String typeName;

  @Column(name = "TYPE_DESCRIPTION", nullable = false)
  @NotBlank(message = "Description is required")
  @Size(max = 255, message = "Description must be at most 255 characters")
  private String description;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private List<MovieType> movieTypeList;
}
