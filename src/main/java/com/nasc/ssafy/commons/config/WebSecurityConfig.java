package com.nasc.ssafy.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 임시 아이디
     * id : test
     * password : test
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("test").password(passwordEncoder().encode("test"))
                .authorities("ROLE_USER");
    }

    /**
     * 비밀번호 암호화를 위한 함수
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * WebSecurityCustomizer의 역할
     * 터 적용에 제외시키는 내용을 제어
     */
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {

        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()    // 리소스 접근 권한 설정
                .antMatchers("/", "/users/sign-up").permitAll() // 접속 리소스, 회원가입 리소스는 누구나 허용
                .antMatchers("/admin/**").hasAnyRole("ADMIN")   // 어드민 관련 리소스는 어드민 권한자만
                .anyRequest().authenticated()   // 그 외 리소스는 로그인된 사용자만 허용

                .and()

                .formLogin()    // 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리
//                .loginPage("/users/log-in").permitAll() //사용자가 만든 로그인 페이지 사용, 사용하지 않으면 기본 로그인 페이지
                .defaultSuccessUrl("/hello")    // 정상적으로 인증 성공했을 경우 이동하는 페이지
//                .successHandler(customOAuthSuccessHandler)   // 정상적 인증 성공 후 별도의 처리가 필요할 경우 커스텀 핸들러를 생성하여 등록
                .failureUrl("/")

                .and()

                .logout()
                .logoutSuccessUrl("/").permitAll();

        return http.build();
    }
}
