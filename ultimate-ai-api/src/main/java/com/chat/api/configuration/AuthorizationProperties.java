package com.chat.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "ultimate.ai")
@Configuration("authorization")
public class AuthorizationProperties {
    private String apiKey;
}