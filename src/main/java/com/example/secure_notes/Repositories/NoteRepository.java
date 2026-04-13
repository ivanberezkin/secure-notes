package com.example.secure_notes.Repositories;

import com.example.secure_notes.Model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
