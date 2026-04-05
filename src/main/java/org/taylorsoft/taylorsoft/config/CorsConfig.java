package org.taylorsoft.taylorsoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // ✅ Autorise ces origines (frontend Angular)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:4200",
                "http://localhost:49673",
                "http://localhost:63485",
                "http://localhost:61674",
                "http://localhost:56617",
                "http://localhost:58577",
                "http://localhost:49773",
                "http://localhost:5942",
                "http://localhost:3000",
                "http://localhost:55176",
                "http://localhost:62604"

        ));

        // ✅ Autorise toutes les méthodes HTTP
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // ✅ Autorise tous les headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // ✅ Permet l'envoi de cookies/tokens
        configuration.setAllowCredentials(true);

        // ✅ Cache la config pendant 1h
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }
}

