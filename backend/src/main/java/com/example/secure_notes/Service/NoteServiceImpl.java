package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.Exceptions.NoteNotFoundException;
import com.example.secure_notes.Model.NoteEntity;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.NoteRepository;
import com.example.secure_notes.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public NoteResponseDto createNewNote(NoteRequestDto newNoteDto) {
        NoteEntity createdNote = noteRepository.save(convertToEntity(newNoteDto));
        return convertToResponseDto(createdNote);
    }

    @Override
    public List<NoteResponseDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public List<NoteResponseDto> getAllUsersNotes() {
        UserEntity user = getContextUser();
        return noteRepository.findByUser(user)
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public NoteEntity getNoteById(Long id) {
        NoteEntity detailedNote = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Noteservice: Note Id not found: " + id));

        UserEntity authenticatedUser = getContextUser();
        if(!detailedNote.getUser().getId().equals(authenticatedUser.getId())){
            throw new NoteNotFoundException("Noteservice: Note not found: " + id);
        }

        return detailedNote;
    }

    @Override
    public NoteResponseDto getDetailedNoteForUser(Long id) {
        return convertToResponseDto(getNoteById(id));
    }

    @Override
    public void deleteNoteById(Long id) {
        NoteEntity noteToDelete = getNoteById(id);
        noteRepository.delete(noteToDelete);
    }

    @Override
    public NoteResponseDto updateNote(Long id, NoteRequestDto updatedNote) {
        NoteEntity noteInDb = getNoteById(id);
        noteInDb.setTitle(updatedNote.getTitle());
        noteInDb.setContent(updatedNote.getContent());
        noteInDb.setFavorite(updatedNote.isFavorite());
        return convertToResponseDto(noteRepository.save(noteInDb));
    }


    private NoteEntity convertToEntity(NoteRequestDto dto){
        return NoteEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(getContextUser())
                .favorite(dto.isFavorite())
                .build();
    }
    private NoteResponseDto convertToResponseDto (NoteEntity note){
        return NoteResponseDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .username(note.getUser().getUsername())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .favorite(note.getFavorite())
                .build();
    }

    public UserEntity getContextUser(){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("NoteService: User not found " + username ));

    }


}


