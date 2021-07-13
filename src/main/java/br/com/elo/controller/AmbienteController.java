package br.com.elo.controller;

import br.com.elo.domain.dto.request.AmbienteRequestCriteriaDTO;
import br.com.elo.service.AmbienteService;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ambientes")
public class AmbienteController {

    private final AmbienteService service;

    public AmbienteController(AmbienteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AmbienteResponseDTO> save(@RequestBody @Valid AmbienteRequestDTO dto) {
        AmbienteResponseDTO responseDTO= service.save(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AmbienteResponseDTO> findById(@PathVariable("id") String id) {
        AmbienteResponseDTO responseDTO =  service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/seq/{ambienteSequence}")
    public ResponseEntity<AmbienteResponseDTO> findByAmbienteSequence(@PathVariable("ambienteSequence") Long ambienteSequence) {
        AmbienteResponseDTO responseDTO = service.findByAmbienteSequence(ambienteSequence);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{ambienteSequence}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AmbienteResponseDTO> update(@PathVariable ("ambienteSequence") Long ambienteSequence, @RequestBody @Valid AmbienteRequestDTO dto) {
        AmbienteResponseDTO responseDTO = service.update(ambienteSequence, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{ambienteSequence}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AmbienteResponseDTO> updateNSU(@PathVariable ("ambienteSequence") Long ambienteSequence) {
        AmbienteResponseDTO responseDTO = service.updateNSU(ambienteSequence);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


   @GetMapping(produces = "application/json")
    public ResponseEntity<List<AmbienteResponseDTO> >findAll(@Valid AmbienteRequestCriteriaDTO dto) {
       List<AmbienteResponseDTO> list = service.findAll(dto);
       return new ResponseEntity<>(list, HttpStatus.OK);
    }




}
