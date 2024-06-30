package org.example.springjdbcdemo.clients;

import org.example.springjdbcdemo.config.TriviaClientConfig;
import org.example.springjdbcdemo.model.Category;
import org.example.springjdbcdemo.utils.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class TriviaClient extends RestClient {

    @Autowired
    public TriviaClient(WebClient.Builder builder, TriviaClientConfig config) {
        super(builder, config);
    }

    public String getTrivia(Category category) {
        return this.client.get()
                .uri("/v1/trivia?category={category}", category.name().toLowerCase())
                .header("X-Api-Key", "API_KEY")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
