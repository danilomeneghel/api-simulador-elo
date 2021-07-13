package br.com.elo.controller;

import br.com.elo.domain.dto.request.ProdutoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProdutoRequestDTO;
import br.com.elo.domain.dto.response.ProdutoResponseDTO;
import br.com.elo.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final ModelMapper modelMapper;

    public ProdutoController(ProdutoService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> save(@RequestBody @Valid  ProdutoRequestDTO dto) {
        ProdutoResponseDTO responseDTO= service.save(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable("id") String id) {
        ProdutoResponseDTO responseDTO =  service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/cod/{codigoBandeira}/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> findByCodigoBandeiraAndCodigoProduto(@PathVariable("codigoBandeira") Integer codigoBandeira, @PathVariable("codigoProduto") Integer codigoProduto) {
        ProdutoResponseDTO responseDTO = service.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{codigoBandeira}/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable ("codigoBandeira") Integer codigoBandeira, @PathVariable ("codigoProduto") Integer codigoProduto,@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO responseDTO = service.update(codigoBandeira, codigoProduto, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProdutoResponseDTO>> findAll( @Valid ProdutoRequestCriteriaDTO dto) {
       List<ProdutoResponseDTO> list = service.findAll(dto);
       return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
