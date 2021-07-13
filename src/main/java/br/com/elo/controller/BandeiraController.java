package br.com.elo.controller;

import br.com.elo.domain.dto.request.BandeiraRequestCriteriaDTO;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.service.BandeiraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bandeiras")
public class BandeiraController {

    private final BandeiraService service;

    public BandeiraController(BandeiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BandeiraResponseDTO> save(@RequestBody @Valid BandeiraRequestDTO bandeira) {
        BandeiraResponseDTO responseDTO = service.save(bandeira);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BandeiraResponseDTO> findById(@PathVariable("id") String id) {
        BandeiraResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/cod/{codigoBandeira}")
    public ResponseEntity<BandeiraResponseDTO> findByCodigo(@PathVariable("codigoBandeira") Integer codigoBandeira) {
        BandeiraResponseDTO responseDTO = service.findByCodigoBandeira(codigoBandeira);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{codigoBandeira}")
    public ResponseEntity<BandeiraResponseDTO> update(@PathVariable("codigoBandeira") Integer codigoBandeira, @RequestBody @Valid BandeiraRequestDTO dto) {
        BandeiraResponseDTO responseDTO = service.update(codigoBandeira,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BandeiraResponseDTO>> findAll(@Valid BandeiraRequestCriteriaDTO dto) {
        List<BandeiraResponseDTO> list = service.findAll(dto);
        return new ResponseEntity<List<BandeiraResponseDTO>>(list, HttpStatus.OK);
    }


}
