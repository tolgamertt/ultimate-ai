package com.chat.application.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "ultimate.ai")
@Configuration("application")
public class ApplicationProperties {
    private double confidencePercentage;
}