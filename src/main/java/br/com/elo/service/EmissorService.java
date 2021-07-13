package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.EmissorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.EmissorRequestDTO;
import br.com.elo.domain.dto.response.EmissorResponseDTO;
import br.com.elo.model.Emissor;
import br.com.elo.repository.BandeiraRepository;
import br.com.elo.repository.EmissorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EmissorService {

    @Autowired
    private EmissorRepository emissorRepository;
    @Autowired
    private BandeiraRepository bandeiraRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public EmissorResponseDTO save(EmissorRequestDTO dto) {
        if (Objects.nonNull(dto.getCodigoBandeira()) && !bandeiraRepository.existsByCodigoBandeira(dto.getCodigoBandeira())) {
            throw new ObjectNotFoundException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());
        }

        if (emissorRepository.existsByCodigoEmissorAndCodigoBandeira(dto.getCodigoEmissor(),dto.getCodigoBandeira())) {

            throw new ParametroInvalidoException(MensagensRetorno.EMISSOR_COD_BAD_EXISTE.getDescricao());
        }

        Emissor emissor = modelMapper.map(dto, Emissor.class);
        emissor.setDataCriacao(LocalDateTime.now());
        emissor.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        emissor = emissorRepository.save(emissor);
        return modelMapper.map(emissor, EmissorResponseDTO.class);
    }

    public EmissorResponseDTO findById(String id) {
        Emissor emissor = emissorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.EMISSOR_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(emissor, EmissorResponseDTO.class);
    }

    public EmissorResponseDTO findByCodigoBandeiraAndCodigoEmissor(Integer codigoEmissor, Integer codigoBandeira) {
        Emissor emissor = emissorRepository.findByCodigoEmissorAndCodigoBandeira(codigoEmissor, codigoBandeira);
        if (Objects.isNull(emissor))
            throw new ObjectNotFoundException(MensagensRetorno.EMISSOR_NAO_LOCALIZADO.getDescricao());
        return modelMapper.map(emissor, EmissorResponseDTO.class);
    }

    public EmissorResponseDTO update(Integer codigoEmissor, Integer codigoBandeira, EmissorRequestDTO dto) {
        Emissor emissor = modelMapper.map(dto, Emissor.class);

        //verifica se existe uma bandeira válida
        if (!(bandeiraRepository.existsByCodigoBandeira(codigoBandeira) && bandeiraRepository.existsByCodigoBandeira(emissor.getCodigoBandeira()))) {
            throw new ObjectNotFoundException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());
        }
        //Carrega o Emissor por codigoEmissor e Bandeira formando uma chave composta
        Emissor emissorAux = emissorRepository.findByCodigoEmissorAndCodigoBandeira(codigoEmissor, codigoBandeira);

        if (Objects.isNull(emissorAux)) {
            throw new ObjectNotFoundException(MensagensRetorno.EMISSOR_NAO_LOCALIZADO.getDescricao());
        }

        if((!dto.getCodigoEmissor().equals(codigoEmissor)) || (!dto.getCodigoBandeira().equals(codigoBandeira))){
            if(emissorRepository.existsByCodigoEmissorAndCodigoBandeira(dto.getCodigoEmissor(), dto.getCodigoBandeira())){
                throw new ParametroInvalidoException(MensagensRetorno.EMISSOR_COD_BAD_EXISTE.getDescricao());
            }
        }

        emissor.setId(emissorAux.getId());
        emissor.setDataCriacao(emissorAux.getDataCriacao());
        emissor.setDataAlteracao(LocalDateTime.now());
        emissor.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        emissor = emissorRepository.save(emissor);

        return modelMapper.map(emissor, EmissorResponseDTO.class);

    }



    public List<EmissorResponseDTO> findAll(EmissorRequestCriteriaDTO dto) {

        Emissor emissor = modelMapper.map(dto, Emissor.class);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(emissor, matcher);

        return modelMapper.map(emissorRepository.findAll(example), new TypeToken<List<EmissorResponseDTO>>() {
        }.getType());
    }

}
