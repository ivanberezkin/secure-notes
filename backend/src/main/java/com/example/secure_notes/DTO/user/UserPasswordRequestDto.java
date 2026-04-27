package com.example.secure_notes.DTO.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserPasswordRequestDto {
    @NotBlank
    @Size(min = 8, max = 50)
    private String currentPassword;


    @NotBlank
    @Size(min = 8, max = 50)
    private String newPassword;
}

