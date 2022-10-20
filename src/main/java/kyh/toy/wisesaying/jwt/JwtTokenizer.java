package kyh.toy.wisesaying.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@Component
public class JwtTokenizer {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      Key secretKey) {
        return Jwts.builder().setClaims(claims).setSubject
                (subject).setExpiration(expiration).signWith(secretKey).compact();
    }

    public String generateRefreshToken(String subject,
                                      Date expiration,
                                      Key secretKey) {
        return Jwts.builder().setSubject
                (subject).setExpiration(expiration).signWith(secretKey).compact();
    }

    public Date getAccessTokenExpiration() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, this.accessTokenExpirationMinutes);

        return calendar.getTime();
    }

    public Key getSecretKey() {

        byte[] bytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }






    //필요한것

    //UserDetails
    //인증 필터
    //- 인증성공했을때, 토큰 발행
    //검증 필터 (요청 들어올때)
    //- 토큰 검증해서 유효한거라면 SecurityContext를 Holder에 저장
    //
}
