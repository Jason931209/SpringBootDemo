package org.example.springjdbcdemo.utils.rest;

import java.time.Duration;

public interface RestClientConfig {
    String host();
    Duration timeout();
}
