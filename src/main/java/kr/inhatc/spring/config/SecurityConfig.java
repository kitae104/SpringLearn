package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * InMemoryUserDetailsManager를 통해 사용자를 생성
     * @return InMemoryUserDetailsManager
     */
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("test@test.com", "1111");
        UserDetails userDetails2 = createNewUser("hong@test.com", "1111");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        return User.builder()
                .passwordEncoder(s -> passwordEncoder().encode(s)) // 암호화를 한 상태(추가)
                .username(username)
                .password(password)           // 암호화를 하지 않은 상태
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .anyRequest().authenticated()
        );
//        http.formLogin(withDefaults());
//        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
//        http.headers(httpSecurityHeadersConfigurer ->
//                httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        return http.build();
    }
}
