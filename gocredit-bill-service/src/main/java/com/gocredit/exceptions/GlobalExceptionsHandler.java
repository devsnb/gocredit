package com.gocredit.exceptions;

import com.gocredit.model.APIError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        APIError errors = new APIError(LocalDateTime.now(), status, message, path);

        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        APIError errors = new APIError(LocalDateTime.now(), status, message, path);
        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        APIError errors = new APIError(LocalDateTime.now(), status, message, path);
        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        APIError errors = new APIError(LocalDateTime.now(), status, message, path);
        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        APIError errors = new APIError(LocalDateTime.now(), status, message, path);
        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    @ExceptionHandler(value = BillNotFoundException.class)
    public ResponseEntity<Object> handleServiceCenterNotFoundException(BillNotFoundException ex) {
        String message = ex.getMessage();

        APIError errors = new APIError(LocalDateTime.now(), HttpStatus.NOT_FOUND, message, "");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(value = CreditCardNotFoundException.class)
    public ResponseEntity<Object> handleAddressNotFoundException(CreditCardNotFoundException ex) {
        String message = ex.getMessage();

        APIError errors = new APIError(LocalDateTime.now(), HttpStatus.NOT_FOUND, message, "");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleAddressNotFoundException(UserNotFoundException ex) {
        String message = ex.getMessage();

        APIError errors = new APIError(LocalDateTime.now(), HttpStatus.NOT_FOUND, message, "");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(value = UserAlreadyExitsException.class)
    public ResponseEntity<Object> handleAddressNotFoundException(UserAlreadyExitsException ex) {
        String message = ex.getMessage();

        APIError errors = new APIError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, message, "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
