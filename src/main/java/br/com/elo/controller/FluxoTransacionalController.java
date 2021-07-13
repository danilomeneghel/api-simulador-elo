package br.com.elo.controller;


import br.com.elo.domain.dto.request.FluxoTransacionalRequestCriteriaDTO;
import br.com.elo.domain.dto.request.FluxoTransacionalRequestDTO;
import br.com.elo.domain.dto.response.FluxoTransacionalResponseDTO;
import br.com.elo.service.FluxoTransacionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fluxostransacionais")
public class FluxoTransacionalController {


    private final FluxoTransacionalService service;

    public FluxoTransacionalController(FluxoTransacionalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FluxoTransacionalResponseDTO> save(@RequestBody @Valid FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO) {
        FluxoTransacionalResponseDTO responseDTO = service.save(fluxoTransacionalRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FluxoTransacionalResponseDTO> findById(@PathVariable("id") String id) {
        FluxoTransacionalResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/seq/{fluxoTransacionalSequence}")
    public ResponseEntity<FluxoTransacionalResponseDTO> findByFluxoTransacionalSequence(@PathVariable("fluxoTransacionalSequence") Long fluxoTransacionalSequence) {
        FluxoTransacionalResponseDTO responseDTO = service.findByFluxoTransacionalSequence(fluxoTransacionalSequence);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{fluxoTransacionalSequence}")
    public ResponseEntity<FluxoTransacionalResponseDTO> update(@PathVariable("fluxoTransacionalSequence") Long fluxoTransacionalSequence, @RequestBody @Valid FluxoTransacionalRequestDTO dto) {
        FluxoTransacionalResponseDTO responseDTO = service.update(fluxoTransacionalSequence,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<FluxoTransacionalResponseDTO>> findAll(@Valid FluxoTransacionalRequestCriteriaDTO dto) {
        List<FluxoTransacionalResponseDTO> list = service.findAll(dto);
        return new ResponseEntity<List<FluxoTransacionalResponseDTO>>(list, HttpStatus.OK);
    }

}
