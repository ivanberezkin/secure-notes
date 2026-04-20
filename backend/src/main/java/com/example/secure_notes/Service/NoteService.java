package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.Model.NoteEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteService {

    NoteResponseDto createNewNote(NoteRequestDto noteRequestDto);

    List<NoteResponseDto> getAllNotes();

    List<NoteResponseDto> getAllUsersNotes();

    NoteEntity getNoteById(Long id);

    NoteResponseDto getDetailedNoteForUser(Long id);

    void deleteNoteById(Long id);
}
