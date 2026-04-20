package com.example.secure_notes.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(max = 150)
    private String title;

    @Size(max = 10000)
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    private List<TagsEntity> tags = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "boolean default false")
    private Boolean favorite = false;
}
