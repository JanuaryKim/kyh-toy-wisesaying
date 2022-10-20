package kyh.toy.wisesaying.config;

import kyh.toy.wisesaying.filter.JwtAuthenticationFilter;
import kyh.toy.wisesaying.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {


    private final JwtTokenizer jwtTokenizer;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable() //해당 설정을 제거하면 어떻게 될지 테스트 해볼것
                //아마 프로젝트때는 클라이언트서버가 백엔드서버와 다른 도메인에 있을것이기 때문에 설정을 해주어야 할것이다.
                .cors(Customizer.withDefaults())
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfiguration())

                .and()
                .exceptionHandling()
//                .accessDeniedHandler(new MemberAccessDeniedHandler()) //예외처리 추가시 다시 추가
//                .authenticationEntryPoint(new MemberAuthenticationEntryPoint()) //예외처리 추가시 다시 추가
                .and()
                .authorizeHttpRequests(authorize-> authorize
                        .antMatchers(HttpMethod.DELETE, "/api_v1/members/*").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.PATCH, "/api_v1/members/*").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.GET, "/api_v1/members/*").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.POST, "/api_v1/members/").permitAll()
                        .antMatchers(HttpMethod.GET, "/api_v1/members").hasRole("ADMIN") //전체 멤버 조회는 관리자만


                        .antMatchers(HttpMethod.DELETE, "/api_v1/cards/*").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.PATCH, "/api_v1/cards/*").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.POST, "/images/upload").hasAnyRole("ADMIN","USER")
                        .antMatchers(HttpMethod.POST, "/api_v1/cards/").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll()
                );

        return httpSecurity.build();
    }

    public class CustomFilterConfiguration extends AbstractHttpConfigurer<CustomFilterConfiguration, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api_v1/auth/login");
            builder.addFilter(jwtAuthenticationFilter);

        }
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
