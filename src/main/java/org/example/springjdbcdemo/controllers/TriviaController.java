package org.example.springjdbcdemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springjdbcdemo.model.Category;
import org.example.springjdbcdemo.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Trivia", description = "Trivia endpoints")
public class TriviaController {
    private final TriviaService triviaService;

    @Autowired
    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping(value = "/getTrivia/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve a Trivia question", description = "hit downstream api call for a trivia question")
    public String getTrivia(
            @PathVariable("category")
            @Parameter(description = "The category of the trivia question", required = true, example = "select a category")
            Category category
    ) {
        return triviaService.getTrivia(category);
    }
}
