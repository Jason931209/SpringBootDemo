package org.example.springjdbcdemo.utils.rest;

import io.netty.channel.ChannelOption;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;

public abstract class RestClient {
    protected final WebClient client;

    protected RestClient(WebClient.Builder builder, RestClientConfig config) {
        var millis = config.timeout().toMillis();
        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) millis);

        this.client = builder.baseUrl(config.host())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
