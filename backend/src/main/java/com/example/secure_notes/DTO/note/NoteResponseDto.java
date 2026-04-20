package com.example.secure_notes.DTO.note;


import com.example.secure_notes.Model.TagsEntity;
import com.example.secure_notes.Model.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NoteResponseDto {

    @NotNull
    @NotBlank
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 150)
    private String title;

    @Size(max = 10000)
    private String content;

    @NotNull
    @NotBlank
    private UserEntity user;

    private List<TagsEntity> tags = new ArrayList<>();

    @NotNull
    @NotBlank
    private LocalDateTime createdAt;

    @NotNull
    @NotBlank
    private LocalDateTime updatedAt;

    @NotNull
    @NotBlank
    private Boolean favorite = false;
}
