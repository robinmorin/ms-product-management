package org.test.capitole.api.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class RecordNotFound extends HttpClientErrorException {

    public RecordNotFound(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
