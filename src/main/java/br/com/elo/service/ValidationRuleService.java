package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.common.utils.converters.MapConverters;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.ValidationRulesRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ValidationRulesRequestDTO;
import br.com.elo.domain.dto.response.ValidationRulesResponseDTO;
import br.com.elo.model.*;
import br.com.elo.repository.ValidationRulesRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidationRuleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidationRulesRepository repository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public ValidationRulesResponseDTO save(ValidationRulesRequestDTO dto) {

        ValidationRules validationRules = modelMapper.map(dto, ValidationRules.class);

        if(dto.getValidationBits() == null || dto.getValidationBits().size() == 0)
            throw new ParametroInvalidoException(MensagensRetorno.VALIDATION_RULES_VALIDATION_BIT_BAD_REQUEST.getDescricao());


        validationRules.setCreateDate(LocalDateTime.now());
        validationRules.setValidationRulesSequence(sequenceGenerator.generateSequence(ValidationRules.SEQUENCE_NAME));
        validationRules.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        validationRules = repository.save(validationRules);
        return modelMapper.map(validationRules, ValidationRulesResponseDTO.class);
    }

    public ValidationRulesResponseDTO findById(String id) {
        ValidationRules validationRules = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.VALIDATION_RULES_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(validationRules, ValidationRulesResponseDTO.class);
    }

    public List<ValidationRulesResponseDTO> findAll(ValidationRulesRequestCriteriaDTO dto) {
        modelMapper.addConverter(MapConverters.toStatus);

        ValidationRules validationRules = modelMapper.map(dto, ValidationRules.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<ValidationRules> example = Example.of(validationRules, matcher);

        return modelMapper.map(repository.findAll(example), new TypeToken<List<ValidationRulesResponseDTO>>() {}.getType());
    }

}
