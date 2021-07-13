package br.com.elo.controller;

import br.com.elo.domain.dto.request.CartaoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CartaoRequestDTO;
import br.com.elo.domain.dto.response.CartaoResponseDTO;
import br.com.elo.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cartoes")
public class CartaoController {


    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDTO> save(@RequestBody @Valid CartaoRequestDTO cartao) {
        CartaoResponseDTO responseDTO = service.save(cartao);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CartaoResponseDTO> findById(@PathVariable("id") String id) {
        CartaoResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/cod/{pan}")
    public ResponseEntity<CartaoResponseDTO> findByPan(@PathVariable("pan") String pan) {
        CartaoResponseDTO responseDTO = service.findByPan(pan);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "{pan}")
    public ResponseEntity<CartaoResponseDTO> update(@PathVariable("pan") String pan,@RequestBody @Valid CartaoRequestDTO dto) {
        CartaoResponseDTO responseDTO = service.update(pan,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CartaoResponseDTO> >findAll(@Valid CartaoRequestCriteriaDTO dto) {
        List<CartaoResponseDTO>  list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
