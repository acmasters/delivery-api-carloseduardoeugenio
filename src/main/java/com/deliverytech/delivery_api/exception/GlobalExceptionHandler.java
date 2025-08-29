package com.deliverytech.delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseException> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                "Erro de validação nos dados enviados",
                request.getDescription(false).replace("uri=", "")
        );
        errorResponse.setErrorCode("VALIDATION_ERROR");
        errorResponse.setDetails(errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseException> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        errorResponse.setErrorCode(ex.getErrorCode());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseException> handleConflictException(
            ConflictException ex, WebRequest request) {

        Map<String, String> details = new HashMap<>();
        if (ex.getConflictField() != null) {
            details.put(ex.getConflictField(), ex.getConflictValue().toString());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.CONFLICT.value(),
                "Conflito de dados",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setDetails(details.isEmpty() ? null : details);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseException> handleGenericException(
            Exception ex, WebRequest request) {

        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getDescription(false).replace("uri=", "")
        );
        errorResponse.setErrorCode("INTERNAL_ERROR");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
