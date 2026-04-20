package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.Service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteResponseDto> createNewNote(@Valid @RequestBody NoteRequestDto noteRequestDto){
        NoteResponseDto createdNote = noteService.createNewNote(noteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @GetMapping("/allnotes")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<NoteResponseDto>> getAllNotes(){
        List<NoteResponseDto> allNotes = noteService.getAllNotes();
        return ResponseEntity.ok(allNotes);
    }



}
