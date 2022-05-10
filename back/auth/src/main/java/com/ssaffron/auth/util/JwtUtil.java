package com.ssaffron.auth.util;

//import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.auth.dto.MemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {

//    @Value("${spring.jwt.token-validity-in-seconds}")
//    public static long TOKEN_VALIDATION_SECOND;
    public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 10;

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
    public String generateToken(MemberDto memberDto) {
        return doGenerateToken(memberDto, TOKEN_VALIDATION_SECOND);
    }

    // Refresh 토큰을 형성
    public String generateRefreshToken(MemberDto memberDto) {
        return doGenerateToken(memberDto, REFRESH_TOKEN_VALIDATION_SECOND);
    }


    // 토큰 생성, payload에 담길 값은 memberEmail
    public String doGenerateToken(MemberDto memberDto, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("memberEmail", memberDto.getMemberEmail());
        claims.put("memberRole", memberDto.getMemberRole());

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
