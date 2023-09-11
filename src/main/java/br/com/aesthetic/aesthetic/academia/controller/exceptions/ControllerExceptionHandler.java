package br.com.aesthetic.aesthetic.academia.controller.exceptions;

import br.com.aesthetic.aesthetic.academia.service.exceptions.DuplicateEnrollmentException;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundExceptions.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExceptions e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setError("Resouce not found");
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(DuplicateEnrollmentException.class)
    public ResponseEntity<StandardError> duplicateEnrollmentException(DuplicateEnrollmentException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setError("Resouce not found");
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

}
