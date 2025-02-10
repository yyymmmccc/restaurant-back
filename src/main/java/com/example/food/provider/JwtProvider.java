package com.example.food.provider;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;

    public String createAccessToken(String userId){

        String jwt = Jwts.builder() // builder는 jwt를 만들기 위한 객체를 생성함
                .signWith(SignatureAlgorithm.HS256, secretKey) // 시크릿키를 HS256알고리즘을 사용
                .setSubject("AccessToken") // Subject를 AccessToken으로 지정
                .setIssuedAt(Date.from(Instant.now()))    // setIssuedAt은 토큰 발행일
                .setExpiration(Date.from(Instant.now().plus(14, ChronoUnit.DAYS))) // 만료일 설정
                .claim("userId", userId) // 이메일 클레임 추가
                .compact();

        return jwt;
    }

    public String extractAccessToken(HttpServletRequest request) {

        // 요청 헤더에 Authorization: Bearer EIij2i3jojo 이런식으로 토큰있는 걸 추출
        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization); // 헤더가 비어있는지 검사 -> 비어있으면 false
        if (!hasAuthorization) {
            return null;
        }

        boolean isBearer = authorization.startsWith("Bearer "); // 헤더에 시작부분이 Bearer 인지 검사
        if (!isBearer) return null;

        String token = authorization.substring(7);
        // jwt 추출
        return token;
    }

    public Claims validateAccessToken(String accessToken){

        try {
            Claims claims;
            claims = Jwts.parser()
                    .setSigningKey(secretKey) //jwt 를 secretKey로 정상적인지 검증
                    .parseClaimsJws(accessToken)
                    .getBody();

            return claims;
        } catch (ExpiredJwtException e) {
            // 만료된 토큰에 대한 예외 처리
            log.error("만료된 토큰입니다: {}", e.getMessage());
            return null;
        } catch (MalformedJwtException e) {
            // 형식이 잘못된 토큰에 대한 예외 처리
            log.error("형식이 잘못되었습니다: {}", e.getMessage());
            return null;
        } catch (SignatureException e) {
            // 서명이 잘못된 토큰에 대한 예외 처리
            log.error("서명이 잘못되었습니다: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            // 그 외의 예외 처리
            log.error("Invalid token: {}", e.getMessage());
            return null;
        }
    }
}
