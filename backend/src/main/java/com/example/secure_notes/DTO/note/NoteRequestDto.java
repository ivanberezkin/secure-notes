package com.example.secure_notes.DTO.note;


import com.example.secure_notes.Model.TagsEntity;
import com.example.secure_notes.Model.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NoteRequestDto {

    @Size(max = 150)
    private String title;

    @Size(max = 10000)
    private String content;

    private List<TagsEntity> tags = new ArrayList<>();

}
