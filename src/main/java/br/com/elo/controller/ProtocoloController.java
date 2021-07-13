package br.com.elo.controller;

import br.com.elo.domain.dto.request.ProtocoloRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import br.com.elo.domain.dto.response.ProtocoloResponseDTO;
import br.com.elo.service.ProtocoloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/protocolos")
public class ProtocoloController {


    private final ProtocoloService service;

    public ProtocoloController(ProtocoloService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProtocoloResponseDTO> save(@RequestBody @Valid ProtocoloRequestDTO protocolo) {
        ProtocoloResponseDTO responseDTO = service.save(protocolo);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProtocoloResponseDTO> findById(@PathVariable("id") String id) {
        ProtocoloResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/seq/{protocoloSequence}")
    public ResponseEntity<ProtocoloResponseDTO> findByCodigo(@PathVariable("protocoloSequence") Long protocoloSequence) {
        ProtocoloResponseDTO responseDTO = service.findByProtocoloSequence(protocoloSequence);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "{protocoloSequence}")
    public ResponseEntity<ProtocoloResponseDTO> update(@PathVariable("protocoloSequence") Long protocoloSequence,@RequestBody @Valid ProtocoloRequestDTO dto) {
        ProtocoloResponseDTO responseDTO = service.update(protocoloSequence,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProtocoloResponseDTO> >findAll(@Valid ProtocoloRequestCriteriaDTO dto) {
        List<ProtocoloResponseDTO>  list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
