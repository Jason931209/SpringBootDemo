package org.example.springjdbcdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record Student (

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "100", type = "integer")
    long rollNo,

    @NotBlank(message = "Name is mandatory")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Jason", type = "string")
    String name,

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "55", type = "integer")
    int marks
) {
}
