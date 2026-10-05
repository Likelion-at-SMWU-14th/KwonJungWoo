package com.likelion.seminar.repository;

import com.likelion.seminar.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, String> {
}
