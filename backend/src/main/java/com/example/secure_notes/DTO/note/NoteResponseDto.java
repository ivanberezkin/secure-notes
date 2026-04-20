package com.example.secure_notes.DTO.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;


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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean favorite = false;
}
