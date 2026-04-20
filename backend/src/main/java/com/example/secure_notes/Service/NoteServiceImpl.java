package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.note.NoteRequestDto;
import com.example.secure_notes.DTO.note.NoteResponseDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import com.example.secure_notes.Model.NoteEntity;
import com.example.secure_notes.Model.TagsEntity;
import com.example.secure_notes.Repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.hibernate.Hibernate.map;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;

    @Override
    public NoteResponseDto createNewNote(NoteRequestDto noteRequestDto) {
        return null;
    }
    private NoteEntity convertToEntity(NoteRequestDto dto){
        return NoteEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .tags(dto.getTags())
                .build();
    }
    private NoteResponseDto convertToDto (NoteEntity note){
        return NoteResponseDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .username(note.getUser().getUsername())
                .tags(note.getTags().stream().map(TagsEntity::getTagName).toList())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .favorite(note.getFavorite())
                .build();
    }


}


