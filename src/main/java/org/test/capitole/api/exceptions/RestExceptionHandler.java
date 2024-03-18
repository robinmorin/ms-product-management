package org.test.capitole.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> processErrorsValidation(ConstraintViolationException constraintViolationException, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

        Set<String> lstErrors = constraintViolationException.getConstraintViolations()
                                                             .stream()
                                                             .map(ConstraintViolation::getMessage)
                                                             .collect(Collectors.toSet());

        var errors = Error.builder().timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                                    .status(HttpStatus.BAD_REQUEST)
                                    .path(servletRequest.getRequestURI())
                                    .errors(new ArrayList<>(lstErrors))
                          .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                              .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> processUnHandleError(Exception exception, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

        List<String> lstErrors = List.of("Message: ".concat(Optional.ofNullable(exception.getMessage()).orElse("<empty>")),
                                       "Cause: ".concat(Optional.ofNullable(exception.getCause()).map(Throwable::getMessage).orElse("<unknown>")));
        var errors = Error.builder().timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                                    .status(HttpStatus.BAD_REQUEST)
                                    .path(servletRequest.getRequestURI())
                                    .errors(lstErrors)
                          .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                              .body(errors);
    }


    @Builder
    @AllArgsConstructor
    @Getter
    private static class Error {
        private final String timestamp;
        private final HttpStatus status;
        private final String path;
        private final List<String> errors;
    }

}
