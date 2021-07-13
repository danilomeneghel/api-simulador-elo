package br.com.elo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.elo")
public class TestConfig {
    public static void main(final String[] args) {
        SpringApplication.run(TestConfig.class, args);
    }
}
