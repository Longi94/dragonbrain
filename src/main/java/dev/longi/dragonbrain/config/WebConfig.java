package dev.longi.dragonbrain.config;

import dev.longi.dragonbrain.util.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${env}")
    private String environment;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (!Environment.PRODUCTION.equals(environment)) {
            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
        }
    }
}