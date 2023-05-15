package com.js.portfolio_backend.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProtoWebConfig {

    @Bean
    public RestTemplate protoRestTemplate(){
        return new RestTemplateBuilder().build();
    }

}
