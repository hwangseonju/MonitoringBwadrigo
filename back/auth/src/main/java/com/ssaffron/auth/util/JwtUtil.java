package com.ssaffron.auth.util;

//import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.auth.entity.MemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {


    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 *4;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Access 토큰을 형성
    public String generateToken(MemberEntity memberEntity) {
        return doGenerateToken(memberEntity, TOKEN_VALIDATION_SECOND);
    }

    // Refresh 토큰을 형성
    public String generateRefreshToken(MemberEntity memberEntity) {
        return doGenerateToken(memberEntity, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    // 토큰이 유효한 토큰인지 검사한 후, 토큰에 담긴 payload 값을 가져온다
    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getMemberEmail(String token) {
        return extractAllClaims(token).get("memberEmail", String.class);
    }


    // 토큰 생성, payload에 담길 값은 memberEmail
    public String doGenerateToken(MemberEntity memberEntity, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("memberEmail", memberEntity.getMemberEmail());
        claims.put("memberRole", memberEntity.getRole());

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
}
