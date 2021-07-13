package br.com.elo.controller;

import br.com.elo.domain.dto.request.*;
import br.com.elo.domain.dto.response.ValidationRulesResponseDTO;
import br.com.elo.service.ValidationRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/simulator-management/v1/validation-rules")
public class ValidationRulesController {

    private final ValidationRuleService service;

    public ValidationRulesController(ValidationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ValidationRulesResponseDTO> save(@RequestBody @Valid ValidationRulesRequestDTO validationRules) {
        ValidationRulesResponseDTO responseDTO = service.save(validationRules);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ValidationRulesResponseDTO> findById(@PathVariable("id") String id) {
        ValidationRulesResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ValidationRulesResponseDTO>> findAll(@Valid ValidationRulesRequestCriteriaDTO dto) {
        List<ValidationRulesResponseDTO> list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
