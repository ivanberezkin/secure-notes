package com.example.secure_notes.DTO.note;


//import com.example.secure_notes.Model.TagsEntity;
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
@Builder
public class NoteResponseDto {


    private Long id;

    @Size(max = 150)
    @NotBlank
    private String title;

    private String content;

    private String username;

//    private List<String> tags = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean favorite = false;
}
