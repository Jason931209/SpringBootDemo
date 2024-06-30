package org.example.springjdbcdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class NotFound extends ErrorResponseException {
    private NotFound(ProblemDetail body) {
        super(HttpStatus.NOT_FOUND, body, null);
    }

    public static NotFound of(String msg) {
        var body = ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
        body.setDetail(msg);
        body.setTitle("Not found");
        return new NotFound(body);
    }
}
