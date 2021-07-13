package br.com.elo.controller;


import br.com.elo.domain.dto.request.MensagemISO8583RequestCriteriaDTO;
import br.com.elo.domain.dto.request.MensagemISO8583RequestDTO;
import br.com.elo.domain.dto.response.MensagemISO8583ResponseDTO;
import br.com.elo.service.MensagemISO8583Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mensagens")
public class MensagemISO8583Controller {


    private final MensagemISO8583Service service;

    public MensagemISO8583Controller(MensagemISO8583Service service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MensagemISO8583ResponseDTO> save(@RequestBody @Valid MensagemISO8583RequestDTO mensagem) {
        MensagemISO8583ResponseDTO responseDTO = service.save(mensagem);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MensagemISO8583ResponseDTO> findById(@PathVariable("id") String id) {
        MensagemISO8583ResponseDTO responseDTO = service.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/seq/{mensagemSequence}")
    public ResponseEntity<MensagemISO8583ResponseDTO> findByMensagemSequence(@PathVariable("mensagemSequence") Long mensagemSequence) {
        MensagemISO8583ResponseDTO responseDTO = service.findByMensagemSequence(mensagemSequence);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "{mensagemSequence}")
    public ResponseEntity<MensagemISO8583ResponseDTO> update(@PathVariable("mensagemSequence") Long mensagemSequence,@RequestBody @Valid MensagemISO8583RequestDTO dto) {
        MensagemISO8583ResponseDTO responseDTO = service.update(mensagemSequence,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MensagemISO8583ResponseDTO>>findAll(@Valid MensagemISO8583RequestCriteriaDTO dto) {
        List<MensagemISO8583ResponseDTO>  list = service.findAll(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
