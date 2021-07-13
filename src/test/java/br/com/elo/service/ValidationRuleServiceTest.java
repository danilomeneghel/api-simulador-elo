package br.com.elo.service;


import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.dto.request.BandeiraRequestCriteriaDTO;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.request.ValidationRulesRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ValidationRulesRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.domain.dto.response.ValidationRulesResponseDTO;
import br.com.elo.model.Bandeira;
import br.com.elo.model.ValidationRules;
import br.com.elo.repository.ValidationRulesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ValidationRuleServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    ValidationRuleService service;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    private ValidationRulesRepository repository;

    @Test
    public void saveTest(){
        ValidationRulesRequestDTO validationRulesRequestDTO = DadosMockUtil.criaNovaValidationRulesRequestDTO();
        ValidationRules validationRulesMock = DadosMockUtil.criaNovaValidationRulesRepository();
        ValidationRules validationRulesDbMock = DadosMockUtil.criaNovaValidationRules();

        Mockito.when( modelMapper.map(validationRulesRequestDTO, ValidationRules.class)).thenReturn(validationRulesMock);
        Mockito.when(repository.save(validationRulesMock)).thenReturn(validationRulesDbMock);

        ValidationRulesResponseDTO validationRulesResponseDTO = DadosMockUtil.validationRulesResponseDTO();
        Mockito.when(modelMapper.map(validationRulesDbMock, ValidationRulesResponseDTO.class)).thenReturn(validationRulesResponseDTO);

        //Execução
        ValidationRulesResponseDTO validationRulesResponseDTOAux = service.save(validationRulesRequestDTO);

        //verificação
        assertThat(validationRulesResponseDTOAux.getId()).isNotNull();
        assertThat(validationRulesResponseDTOAux.getId()).isEqualTo(validationRulesDbMock.getId());
        assertThat(validationRulesResponseDTOAux.getValidationRulesSequence()).isEqualTo(validationRulesDbMock.getValidationRulesSequence());
    }

    @Test
    public void saveValidationBitTamInvalidoTest(){
        ValidationRulesRequestDTO validationRulesRequestDTO = DadosMockUtil.criaNovaValidationRulesRequestDTO();
        validationRulesRequestDTO.setValidationBits(null);
        ValidationRules validationRulesMock = DadosMockUtil.criaNovaValidationRulesRepository();
        validationRulesMock.setValidationBits(null);

        Mockito.when(modelMapper.map(validationRulesRequestDTO, ValidationRules.class)).thenReturn(validationRulesMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(validationRulesRequestDTO);
        });

        String expectedMessage = "Não foi possível processar a solicitação, validation bit necessita de pelos menos uma regra cadastrada";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findByIdTest(){
        ValidationRules validationRulesDbMock = DadosMockUtil.criaNovaValidationRules();

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3158") ).thenReturn(Optional.ofNullable(validationRulesDbMock));
        ValidationRulesResponseDTO validationRulesResponseDTO = DadosMockUtil.validationRulesResponseDTO();
        Mockito.when(modelMapper.map(validationRulesDbMock, ValidationRulesResponseDTO.class)).thenReturn(validationRulesResponseDTO);

        //Execução
        ValidationRulesResponseDTO validationRulesResponseDTOAux = service.findById("5feb955b37b9fb7770ec3158");

        //verificação
        assertThat(validationRulesResponseDTOAux.getId()).isEqualTo("5feb955b37b9fb7770ec3158");
    }

    @Test
    public void notFoundByIdTest(){

        Mockito.when(repository.findById("5feb955b37b9fb7770ec3158") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3158");
        });

        String expectedMessage = MensagensRetorno.VALIDATION_RULES_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findAllTest(){

        ValidationRulesRequestCriteriaDTO validationRulesRequestCriteriaDTO = DadosMockUtil.criaNovaValidationRulesRequestCriteriaDTO();
        ValidationRules validationRulesMock = DadosMockUtil.criaNovaValidationRulesRepository();

        List<ValidationRules> listaBuscaValidationRules = new ArrayList<>();
        listaBuscaValidationRules.add(validationRulesMock);

        List<ValidationRulesResponseDTO> listaBuscaBandeiraResponse = new ArrayList<>();
        listaBuscaBandeiraResponse.add(DadosMockUtil.validationRulesResponseDTO());

        Mockito.when( modelMapper.map(validationRulesRequestCriteriaDTO, ValidationRules.class)).thenReturn(validationRulesMock);
        Mockito.when(repository.findAll((Example<ValidationRules>) Mockito.any())).thenReturn(listaBuscaValidationRules);
        Mockito.when(modelMapper.map(listaBuscaValidationRules, new TypeToken<List<ValidationRulesResponseDTO>>() {}.getType())).thenReturn(listaBuscaBandeiraResponse);

        //Execução
        List<ValidationRulesResponseDTO> validationResponseList = service.findAll(validationRulesRequestCriteriaDTO);

        //verificação
        assertThat(validationResponseList.size()).isEqualTo(1);
    }

}
