package br.com.elo.controller;

import br.com.elo.domain.dto.request.CredenciadorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CredenciadorRequestDTO;
import br.com.elo.domain.dto.response.CredenciadorResponseDTO;
import br.com.elo.service.CredenciadorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/credenciadores")
public class CredenciadorController {

    private final CredenciadorService service;
    private final ModelMapper modelMapper;

    public CredenciadorController(CredenciadorService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CredenciadorResponseDTO> save(@RequestBody @Valid CredenciadorRequestDTO dto){
        CredenciadorResponseDTO responseDTO = service.save(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CredenciadorResponseDTO> findById(@PathVariable("id") String id) {
        CredenciadorResponseDTO responseDTO =  service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/seq/{credenciadorCodigo}")
    public ResponseEntity<CredenciadorResponseDTO> findByCodigo(@PathVariable("credenciadorCodigo") Integer credenciadorCodigo) {
        CredenciadorResponseDTO responseDTO = service.findByCodigo(credenciadorCodigo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{credenciadorCodigo}")
    public ResponseEntity<CredenciadorResponseDTO> update(@PathVariable ("credenciadorCodigo") Integer credenciadorCodigo, @RequestBody @Valid CredenciadorRequestDTO dto) {
        CredenciadorResponseDTO responseDTO = service.update(credenciadorCodigo, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CredenciadorResponseDTO>>findAll(@Valid CredenciadorRequestCriteriaDTO dto) {
        List<CredenciadorResponseDTO> list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}