package com.example.secure_notes.Model;


import jakarta.persistence.*;
import lombok.*;

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

    private String noteText;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
