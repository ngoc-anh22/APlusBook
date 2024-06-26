package fit.se2.APlusBook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/css/**", "/images/**", "/upload/**").permitAll()
                        .requestMatchers("/register", "/register-process", "/list", "/detail/**", "/search")
                        .permitAll()
                        .requestMatchers("/update/**", "/delete/**" ,"/save", "/insert", "/comment").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/log-in")
                        .loginProcessingUrl("/login_processing_url")
                        .successHandler(new UrlSuccessHandler())
                        .failureUrl("/log-in?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/log-out")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider)
                .build();
    }
}
