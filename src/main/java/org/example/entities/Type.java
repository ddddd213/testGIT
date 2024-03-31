package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "TYPE")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID", unique = true, nullable = false)
    private Integer id;
    @Column(name = "TYPE_NAME", unique = true, nullable = false)
    @NonNull
    private String typeName;
    @Column(name = "TYPE_DESCRIPTION", nullable = false)
    @NonNull
    private String description;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<MovieType> movieTypeList;
}
