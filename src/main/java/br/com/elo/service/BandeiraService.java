package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.BandeiraRequestCriteriaDTO;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.model.Bandeira;
import br.com.elo.repository.BandeiraRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BandeiraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BandeiraService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BandeiraRepository repository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public BandeiraResponseDTO save(BandeiraRequestDTO dto) {

        if(repository.existsByCodigoBandeira(dto.getCodigoBandeira())){
            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_COD_BAD_EXISTE.getDescricao());
        }

        Bandeira bandeira = modelMapper.map(dto, Bandeira.class);
        bandeira.setDataCriacao(LocalDateTime.now());
        bandeira.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        bandeira = repository.save(bandeira);

        LOGGER.info("Bandeira salva com sucesso: {}", bandeira);
        
        return modelMapper.map(bandeira, BandeiraResponseDTO.class);
    }

    public BandeiraResponseDTO findById(String id) {
        Bandeira bandeira = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao()));
        return modelMapper.map(bandeira, BandeiraResponseDTO.class);
    }

    public BandeiraResponseDTO findByCodigoBandeira(Integer codigoBandeira) {

        Bandeira bandeira = repository.findByCodigoBandeira(codigoBandeira);

        if(bandeira == null)
            throw new ObjectNotFoundException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());

        return modelMapper.map(bandeira, BandeiraResponseDTO.class);
    }


    public BandeiraResponseDTO update(Integer codigoBandeira, BandeiraRequestDTO dto) {

        Bandeira bandeira = modelMapper.map(dto, Bandeira.class);

        if(codigoBandeira == null || codigoBandeira == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_BAD_REQUEST.getDescricao());
        }

        Bandeira bandeiraAux =  repository.findByCodigoBandeira(codigoBandeira);

        if(bandeiraAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());
        }

        if( (!codigoBandeira.equals(dto.getCodigoBandeira())) && (repository.existsByCodigoBandeira(dto.getCodigoBandeira()))) {
            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_COD_BAD_EXISTE.getDescricao());
        }


        bandeira.setId(bandeiraAux.getId());
        bandeira.setDataAlteracao(LocalDateTime.now());
        bandeira.setDataCriacao(bandeiraAux.getDataCriacao());
        bandeira.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        bandeira = repository.save(bandeira);
        return modelMapper.map(bandeira, BandeiraResponseDTO.class);
    }

    public List<BandeiraResponseDTO> findAll(BandeiraRequestCriteriaDTO dto) {
        //Pageable paging = PageRequest.of(page, size);

        Bandeira bandeira = modelMapper.map(dto, Bandeira.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(bandeira, matcher);

        List<BandeiraResponseDTO> bandeiraList = modelMapper.map(repository.findAll(example), new TypeToken<List<BandeiraResponseDTO>>() {}.getType());


        return bandeiraList;
    }

}
