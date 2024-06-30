package org.example.springjdbcdemo.service;

import org.example.springjdbcdemo.clients.TriviaClient;
import org.example.springjdbcdemo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriviaService {

    private final TriviaClient client;

    @Autowired
    public TriviaService(TriviaClient client) {
        this.client = client;
    }
    public String getTrivia(Category category) {
        return this.client.getTrivia(category);
    }
}
