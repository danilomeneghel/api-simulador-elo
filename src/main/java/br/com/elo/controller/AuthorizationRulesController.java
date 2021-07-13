package br.com.elo.controller;


import br.com.elo.domain.dto.request.AuthorizationRulesRequestDTO;
import br.com.elo.domain.dto.response.AuthorizationRulesResponseDTO;
import br.com.elo.service.AuthorizationRulesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/simulator-management/v1/authorization-rules")
public class AuthorizationRulesController {

    private final AuthorizationRulesService service;

    public AuthorizationRulesController(AuthorizationRulesService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorizationRulesResponseDTO> save(@RequestBody @Valid AuthorizationRulesRequestDTO dto) {
        AuthorizationRulesResponseDTO responseDTO= service.save(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}