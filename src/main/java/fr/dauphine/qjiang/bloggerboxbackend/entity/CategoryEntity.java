package fr.dauphine.qjiang.bloggerboxbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
