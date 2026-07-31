package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.exceptions.BadGatewayException;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.GatewayTimeoutException;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.UsuarioEmailExists;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> genericException(Exception ex, WebRequest req) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }

    // 502
    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ProblemDetail> badGatewayException(BadGatewayException ex, WebRequest req) {
        var status = HttpStatus.BAD_GATEWAY;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }
    // 503
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ProblemDetail> serviceUnavailableException(ServiceUnavailableException ex, WebRequest req) {
        var status = HttpStatus.SERVICE_UNAVAILABLE;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }
    // 504
    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<ProblemDetail> gatewayTimeoutException(GatewayTimeoutException ex, WebRequest req) {
        var status = HttpStatus.GATEWAY_TIMEOUT;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }

    @ExceptionHandler({
            UsuarioEmailExists.class
    })
    public ResponseEntity<ProblemDetail> emailExistsException(RuntimeException ex, WebRequest req) {
        var status = HttpStatus.CONFLICT;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }
    @ExceptionHandler({
            UsuarioNotFoundException.class,
    })
    public ResponseEntity<ProblemDetail> notFoundException(RuntimeException ex, WebRequest req) {
        var status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> argumentNotValidException(MethodArgumentNotValidException ex, WebRequest req) {
        var status = HttpStatus.BAD_REQUEST;

        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(problemDetail(ex, req, status, errors), status);
    }

    private ProblemDetail problemDetail(Exception ex, WebRequest req, HttpStatus status) {
        String instance = getInstance(req);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problem.setTitle(status.getReasonPhrase());
        problem.setInstance(URI.create(instance));
        problem.setProperty("errors", List.of(ex.getMessage()));
        return problem;

    }
    private ProblemDetail problemDetail(Exception ex, WebRequest req, HttpStatus status, List<String> errors) {
        String instance = getInstance(req);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problem.setTitle(status.getReasonPhrase());
        problem.setInstance(URI.create(getInstance(req)));
        problem.setProperty("errors", List.of(ex.getMessage()));
        return problem;
    }
    private String getInstance(WebRequest req) {
        return ((ServletWebRequest) req).getRequest().getRequestURI();
    }
}
