package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.Service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
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

    @GetMapping("/my")
    public ResponseEntity<List<NoteResponseDto>> getAllUsersNotes(){
        List<NoteResponseDto> allUsersNotes = noteService.getAllUsersNotes();
        return ResponseEntity.ok(allUsersNotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getUserNoteById(@PathVariable Long id){
        NoteResponseDto detailedNote = noteService.getDetailedNoteForUser(id);
        return ResponseEntity.ok(detailedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(
            @PathVariable Long id,
            @RequestBody NoteRequestDto updatedNote){
        NoteResponseDto noteAfterUpdate = noteService.updateNote(id, updatedNote);
        return ResponseEntity.ok(noteAfterUpdate);
    }

}
