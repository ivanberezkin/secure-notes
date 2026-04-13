package com.example.secure_notes.Model;


import com.example.secure_notes.Utils.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password", "notes"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles role = Roles.USER;

    @OneToMany(mappedBy = "user")
    private List<NoteEntity> notes;

}


