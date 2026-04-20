package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.Model.NoteEntity;
//import com.example.secure_notes.Model.TagsEntity;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.NoteRepository;
import com.example.secure_notes.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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


    private NoteEntity convertToEntity(NoteRequestDto dto){
        return NoteEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(getContextUser())
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


