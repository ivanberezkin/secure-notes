package com.example.secure_notes.Repositories;

import com.example.secure_notes.Model.NoteEntity;
import com.example.secure_notes.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findByUser(UserEntity user);

}
