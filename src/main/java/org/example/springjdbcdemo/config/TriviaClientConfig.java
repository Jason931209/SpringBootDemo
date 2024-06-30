package org.example.springjdbcdemo.config;

import org.example.springjdbcdemo.utils.rest.RestClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.time.Duration;

@ConfigurationProperties(prefix = "trivia-service")
public record TriviaClientConfig(String host, Duration timeout) implements RestClientConfig {
}
