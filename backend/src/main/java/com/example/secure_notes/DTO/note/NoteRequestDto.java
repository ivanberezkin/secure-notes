package com.example.secure_notes.DTO.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NoteRequestDto {

    @Size(max = 150)
    @NotBlank
    private String title;

    @Size(max = 10000)
    private String content;

    private boolean favorite;
}
