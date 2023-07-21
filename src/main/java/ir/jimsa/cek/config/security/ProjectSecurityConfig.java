package ir.jimsa.cek.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(conf ->
                        conf.configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                            corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                            corsConfiguration.setAllowCredentials(true);
                            corsConfiguration.setMaxAge(3600L);
                            return corsConfiguration;
                        })
                )
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
