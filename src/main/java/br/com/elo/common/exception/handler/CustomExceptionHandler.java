package br.com.elo.common.exception.handler;

import br.com.elo.common.exception.BusinessException;
import br.com.elo.common.exception.ExceptionResponse;
import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.http.HttpStatus.*;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " : " + "Campo "+error.getField()+" invalido");
        }

        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(errors)
                .build();
        return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(MensagensRetorno.BAD_REQUEST.getDescricao());

        if(ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            errors.add("Campo invalido : " + invalidFormatException.getOriginalMessage());
        }

        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(errors)
                .build();
        return super.handleExceptionInternal(ex, response, headers, response.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " : " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
        }

        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(errors)
                .build();
        return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> all(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(INTERNAL_SERVER_ERROR.value())
                .status(INTERNAL_SERVER_ERROR)
                .details(request.getDescription(false))
                .errors(Arrays.asList(ex.getMessage() == null ? INTERNAL_SERVER_ERROR.getReasonPhrase() : ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> objectNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(NOT_FOUND.value())
                .status(NOT_FOUND)
                .details(request.getDescription(false))
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public final ResponseEntity<ExceptionResponse> parametroInvalidoException(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }
    //HttpMessageNotReadableException
    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ExceptionResponse> invalidFormatException(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ExceptionResponse> businessException(Exception ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss")))
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .details(request.getDescription(false))
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

}