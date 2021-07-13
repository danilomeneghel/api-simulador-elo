package br.com.elo.controller;

import br.com.elo.domain.dto.request.EmissorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.EmissorRequestDTO;
import br.com.elo.domain.dto.response.EmissorResponseDTO;
import br.com.elo.service.EmissorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/emissores")
public class EmissorController {

    private final ModelMapper modelMapper;
    private final EmissorService service;

    public EmissorController(ModelMapper modelMapper, EmissorService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmissorResponseDTO> save(@RequestBody @Valid EmissorRequestDTO dto) {
        EmissorResponseDTO responseDTO= service.save(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmissorResponseDTO> findById(@PathVariable("id") String id) {
        EmissorResponseDTO responseDTO =  service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/cod/{codigoBandeira}/{codigoEmissor}")
    public ResponseEntity<EmissorResponseDTO> findByCodigoEmissorAndBandeiraCodigo(
            @PathVariable("codigoBandeira") Integer codigoBandeira, @PathVariable("codigoEmissor") Integer codigoEmissor) {
        EmissorResponseDTO responseDTO = service.findByCodigoBandeiraAndCodigoEmissor(codigoEmissor, codigoBandeira);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "{codigoBandeira}/{codigoEmissor}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<EmissorResponseDTO> update( @PathVariable("codigoBandeira") Integer codigoBandeira, @PathVariable("codigoEmissor") Integer codigoEmissor,
                                                      @RequestBody @Valid EmissorRequestDTO dto) {
        EmissorResponseDTO responseDTO = service.update(codigoEmissor, codigoBandeira, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmissorResponseDTO>>findAll(@Valid EmissorRequestCriteriaDTO dto) {

        List<EmissorResponseDTO> list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
