package kyh.toy.wisesaying.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kyh.toy.wisesaying.jwt.JwtTokenizer;
import kyh.toy.wisesaying.member.entity.Member;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();

        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        //리턴할 Authentication 구현 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //아래 코드는 인증된 Authentication 을 SecurityContext에 담아 SecurityContextHolder에 저장하는것인데
        //현재 서버는 REST API 서버이기 때문에 세션도 가지지 않는다. 그러므로 현재 단계에서 SecurityContext를 저장시킬 필요가 없다. 라고 생각한다.
//        super.successfulAuthentication(request, response, chain, authResult);

        //그러므로 토큰만 발행해주면 된다.


        Member member = (Member)authResult.getPrincipal();
        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        response.setHeader("Authorization", "Bearer" + accessToken);
        response.setHeader("Refresh", refreshToken);




    }

    private String delegateAccessToken(Member member) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username",member.getEmail());
        claims.put("roles",member.getRoles());
        claims.put("name",member.getName());

        String subject = member.getEmail();

        Date accessTokenExpiration = jwtTokenizer.getAccessTokenExpiration();
        Key secretKey = jwtTokenizer.getSecretKey();

        return jwtTokenizer.generateAccessToken(claims, subject, accessTokenExpiration, secretKey);
    }

    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();

        Date accessTokenExpiration = jwtTokenizer.getAccessTokenExpiration();
        Key secretKey = jwtTokenizer.getSecretKey();

        return jwtTokenizer.generateRefreshToken(subject, accessTokenExpiration, secretKey);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    private static class LoginDto{

        private String username;
        private String password;
    }
}
