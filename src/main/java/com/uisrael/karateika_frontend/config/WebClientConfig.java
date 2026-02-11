package com.uisrael.karateika_frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Bean
    WebClient webClient(WebClient.Builder builder) {
        int size = 10 * 1024 * 1024; // 10 MB
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(size))
                .build();

        HttpClient httpClient = HttpClient.create();

        return builder
                .baseUrl("http://localhost:8080/api")
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build(); // solo para desarrollo y pisp
    } 
}