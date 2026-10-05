package com.likelion.seminar.service;

import com.likelion.seminar.dto.LoginRequest;
import com.likelion.seminar.dto.RefreshRequest;
import com.likelion.seminar.dto.SignupRequest;
import com.likelion.seminar.dto.TokenResponse;
import com.likelion.seminar.entity.Member;
import com.likelion.seminar.entity.RefreshToken;
import com.likelion.seminar.jwt.JwtTokenProvider;
import com.likelion.seminar.repository.MemberRepository;
import com.likelion.seminar.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void signup(SignupRequest request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = new Member(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );

        memberRepository.save(member);
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        String email = authentication.getName();

        String accessToken =
                jwtTokenProvider.createAccessToken(email);

        String refreshToken =
                jwtTokenProvider.createRefreshToken(email);

        RefreshToken savedRefreshToken = refreshTokenRepository
                .findById(email)
                .map(existingToken -> {
                    existingToken.updateToken(refreshToken);
                    return existingToken;
                })
                .orElseGet(() ->
                        new RefreshToken(email, refreshToken)
                );

        refreshTokenRepository.save(savedRefreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponse refresh(RefreshRequest request) {

        String oldRefreshToken = request.refreshToken();

        if (!jwtTokenProvider.validateToken(oldRefreshToken)
                || !jwtTokenProvider.isRefreshToken(oldRefreshToken)) {
            throw new IllegalArgumentException(
                    "유효하지 않은 Refresh Token입니다."
            );
        }

        String email = jwtTokenProvider.getEmail(oldRefreshToken);

        RefreshToken savedRefreshToken = refreshTokenRepository
                .findById(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "저장된 Refresh Token이 없습니다."
                        )
                );

        if (!savedRefreshToken.getToken().equals(oldRefreshToken)) {
            throw new IllegalArgumentException(
                    "일치하지 않는 Refresh Token입니다."
            );
        }

        String newAccessToken =
                jwtTokenProvider.createAccessToken(email);

        String newRefreshToken =
                jwtTokenProvider.createRefreshToken(email);

        savedRefreshToken.updateToken(newRefreshToken);

        return new TokenResponse(
                newAccessToken,
                newRefreshToken
        );
    }
}