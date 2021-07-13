package br.com.elo.common.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ParametroInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParametroInvalidoException(String menssagem) {
        super(menssagem);
    }
}