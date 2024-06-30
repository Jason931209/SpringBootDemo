package org.example.springjdbcdemo;

import org.example.springjdbcdemo.config.TriviaClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({TriviaClientConfig.class})
@SpringBootApplication
public class SpringJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcDemoApplication.class, args);
    }

}
