package br.com.elo.service;

import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.AuthorizationRulesRequestDTO;
import br.com.elo.domain.dto.response.AuthorizationRulesResponseDTO;
import br.com.elo.model.AuthorizationRules;
import br.com.elo.repository.AuthorizationRulesRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
public class AuthorizationRulesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationRulesService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorizationRulesRepository authorizationRulesRepository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    //private static String SEQUENCE = "AuthorizationRulesSequence";

    public AuthorizationRulesResponseDTO save(@Valid AuthorizationRulesRequestDTO dto){

//        if(authorizationRulesRepository.existsByAuthorizationRulesSequence(dto.getId())){
//            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_COD_BAD_EXISTE.getDescricao());
//        }

        AuthorizationRules authorizationRules = modelMapper.map(dto, AuthorizationRules.class);

        authorizationRules.setCreateDate(LocalDateTime.now());
        authorizationRules.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        authorizationRules = authorizationRulesRepository.save(authorizationRules);

        return modelMapper.map(authorizationRules, AuthorizationRulesResponseDTO.class);
    }
}