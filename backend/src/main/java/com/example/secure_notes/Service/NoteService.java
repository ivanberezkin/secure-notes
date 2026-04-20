package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;

import java.util.List;

public interface NoteService {

    NoteResponseDto createNewNote(NoteRequestDto noteRequestDto);

    List<NoteResponseDto> getAllNotes();
}
