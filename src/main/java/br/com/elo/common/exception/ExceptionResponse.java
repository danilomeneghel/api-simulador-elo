package br.com.elo.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String timestamp;
    private int code;
    private HttpStatus status;
    private String details;
    private List<String> errors;
}