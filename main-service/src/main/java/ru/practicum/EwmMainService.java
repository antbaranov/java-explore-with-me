package ru.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication(scanBasePackages = {"ru.practicum.stats-client", "ru.practicum.main-service"})
//@ComponentScan(basePackages = "ru.practicum")
@SpringBootApplication(scanBasePackages = {"ru.practicum.stats-client", "ru.practicum.main-service"})
public class EwmMainService {
    public static void main(String[] args) {
        SpringApplication.run(EwmMainService.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();

    }
}