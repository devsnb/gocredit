package com.gocredit.exceptions;

import com.gocredit.model.ApiErrors;
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

import java.time.LocalDate;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();
        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), status, message, path);
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();

        String path = request.getContextPath();
        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), status, message, path);
        return ResponseEntity.status(status).body(errors);
    }


    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();

        String path = request.getContextPath();
        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), status, message, path);
        return ResponseEntity.status(status).body(errors);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();

        String path = request.getContextPath();
        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), status, message, path);
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        String path = request.getContextPath();

        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), status, message, path);
        return ResponseEntity.status(status).body(errors);
    }


    @ExceptionHandler(value = CreditCardNotFoundException.class)
    public ResponseEntity<Object> handleCreditCardNotFound(CreditCardNotFoundException ex) {
        String message = ex.getMessage();

        String path = "";
        //This is the body for the response entity
        ApiErrors errors = new ApiErrors(LocalDate.now(), HttpStatus.NOT_FOUND, message, path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOther(Exception ex) {
        String message = ex.getMessage();

        String path = "";

        ApiErrors errors = new ApiErrors(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR, message, path);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
