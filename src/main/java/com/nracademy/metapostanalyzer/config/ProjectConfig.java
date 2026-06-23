package com.nracademy.metapostanalyzer.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    static {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        if (System.getProperty("DB_URL") == null && System.getenv("DB_URL") != null) {
            System.setProperty("DB_URL", System.getenv("DB_URL"));
            System.setProperty("DB_USERNAME", System.getenv("DB_USERNAME"));
            System.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));
            System.setProperty("META_ACCESS_TOKEN", System.getenv("META_ACCESS_TOKEN"));
            System.setProperty("META_API_URL", System.getenv("META_API_URL"));
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}