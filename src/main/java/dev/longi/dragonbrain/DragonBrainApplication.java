package dev.longi.dragonbrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author lngtr
 * @since 7/16/2017
 */
@SpringBootApplication
public class DragonBrainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DragonBrainApplication.class, args);
    }
}
