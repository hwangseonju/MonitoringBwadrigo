package com.ssaffron.business.api.config;

import com.ssaffron.business.api.entity.MemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰이 유효한 토큰인지 검사한 후, 토큰에 담긴 payload 값을 가져온다
    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 추출한 payload로 부터 memberEmail을 가져온다
    public String getUsername(String token) {
        return extractAllClaims(token).get("memberEmail", String.class);
    }

    // 토큰이 만료되었는지 확인
    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // Access 토큰을 형성
    public String generateToken(MemberEntity memberEntity) {
        return doGenerateToken(memberEntity.getMemberEmail(), TOKEN_VALIDATION_SECOND);
    }

    // Refresh 토큰을 형성
    public String generateRefreshToken(MemberEntity memberEntity) {
        return doGenerateToken(memberEntity.getMemberEmail(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    // 토큰 생성, payload에 담길 값은 memberEmail
    public String doGenerateToken(String memberEmail, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("memberEmail", memberEmail);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
