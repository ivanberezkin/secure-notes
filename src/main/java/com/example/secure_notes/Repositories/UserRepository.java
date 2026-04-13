package com.example.secure_notes.Repositories;

import com.example.secure_notes.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
