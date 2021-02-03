package br.com.ifood.suggestiontrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class SuggestionTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuggestionTrackApplication.class, args);
    }

}
