package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.Status;


import br.com.elo.domain.dto.request.CredenciadorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CredenciadorRequestDTO;



import br.com.elo.domain.dto.response.CredenciadorResponseDTO;

import br.com.elo.model.Credenciador;


import br.com.elo.repository.CredenciadorRepository;
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
public class CredenciadorServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    CredenciadorService credenciadorService;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    CredenciadorRepository credenciadorRepository;

    private CredenciadorRequestDTO credenciadorRequestDTO = DadosMockUtil.criaNovoCredenciadorRequestDTO();
    private CredenciadorResponseDTO crendeciadorSalvoResponse = DadosMockUtil.credenciadorResponseDTO();
    private Credenciador credenciador = DadosMockUtil.criaNovoCredenciador();
    private Credenciador credenciadorSalvo = DadosMockUtil.credenciadorSalvo();
    private CredenciadorRequestCriteriaDTO credenciadorCriteriaDTO = DadosMockUtil.dadosFindAllCriteriaCredenciador();

    @Test
    public void saveTest(){
        Long codigoSequence = 1L;
        CredenciadorRequestDTO credenciadorRequestMock = credenciadorRequestDTO;
        Credenciador credenciadorMock = credenciador;

        Mockito.when(modelMapper.map(credenciadorRequestDTO, Credenciador.class)).thenReturn(credenciadorMock);
       // Mockito.when(sequenceGenerator.generateSequence(Credenciador.SEQUENCE_NAME)).thenReturn(codigoSequence);

        Credenciador credenciadorSalvoMock = credenciadorSalvo;

        Mockito.when( credenciadorRepository.save(credenciadorMock) ).thenReturn(credenciadorSalvoMock);
        CredenciadorResponseDTO credenciadorResponseSalvoMock = crendeciadorSalvoResponse;
        Mockito.when(modelMapper.map(credenciadorSalvoMock, CredenciadorResponseDTO.class)).thenReturn(credenciadorResponseSalvoMock);

        //Execução
        CredenciadorResponseDTO credenciadorSalvo = credenciadorService.save(credenciadorRequestMock);

        //verificação
        assertThat(credenciadorSalvo.getId()).isNotNull();
        //assertThat(credenciadorSalvo.getCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getCredenciadorCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getNome()).isEqualTo("Nome credenciador");
        assertThat(credenciadorSalvo.getStatus()).isEqualTo(Status.ATIVO);
    }

    @Test
    public void findByIdTest(){
        String id = "60144466ae01a77266bdcf0d";
        CredenciadorRequestDTO credenciadorRequestMock = credenciadorRequestDTO;
        Credenciador credenciadorSalvoMock  = credenciadorSalvo;

        Mockito.when( credenciadorRepository.findById(id) )
                .thenReturn(Optional.ofNullable(credenciadorSalvoMock));
        CredenciadorResponseDTO credenciadorResponseSalvoMock = crendeciadorSalvoResponse;
        Mockito.when(modelMapper.map(credenciadorSalvoMock, CredenciadorResponseDTO.class)).thenReturn(credenciadorResponseSalvoMock);

        //Execução
        CredenciadorResponseDTO credenciadorSalvo = credenciadorService.findById(id);

        //verificação
        assertThat(credenciadorSalvo.getId()).isNotNull();
        //assertThat(credenciadorSalvo.getCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getCredenciadorCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getNome()).isEqualTo("Nome credenciador");
        assertThat(credenciadorSalvo.getStatus()).isEqualTo(Status.ATIVO);
    }

    public void findByCodigoTest(){
        Integer credenciadorCodigo = 1;
        String id = "60144466ae01a77266bdcf0d";
        CredenciadorRequestDTO credenciadorRequestMock = credenciadorRequestDTO;
        Credenciador credenciadorSalvoMock  = credenciadorSalvo;

        Mockito.when( credenciadorRepository.findByCredenciadorCodigo(credenciadorCodigo) )
                .thenReturn(credenciadorSalvoMock);
        CredenciadorResponseDTO credenciadorResponseSalvoMock = crendeciadorSalvoResponse;
        Mockito.when(modelMapper.map(credenciadorSalvoMock, CredenciadorResponseDTO.class)).thenReturn(credenciadorResponseSalvoMock);

        //Execução
        CredenciadorResponseDTO credenciadorSalvo = credenciadorService.findByCodigo(credenciadorCodigo);

        //verificação
        assertThat(credenciadorSalvo.getId()).isNotNull();
        //assertThat(credenciadorSalvo.getCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getCredenciadorCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getNome()).isEqualTo("Nome credenciador");
        assertThat(credenciadorSalvo.getStatus()).isEqualTo(Status.ATIVO);
    }

    @Test
    public void findCredenciadorCodigoNotFound(){
        Integer codigoCredenciador = 999;
        CredenciadorRequestDTO credenciadorRequestMock = credenciadorRequestDTO;
        Mockito.when(credenciadorRepository.findByCredenciadorCodigo(codigoCredenciador)).thenReturn(null);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            credenciadorService.findByCodigo(codigoCredenciador);
        });
        String expectedMessage = MensagensRetorno.CREDENCIADOR_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void updateTest(){
        Integer codigoCredenciador = 1;
        CredenciadorRequestDTO credenciadorRequestMock = credenciadorRequestDTO;
        Credenciador credenciadorMock = credenciador;

        Mockito.when( modelMapper.map(credenciadorRequestMock, Credenciador.class)).thenReturn(credenciadorMock);
        Credenciador credenciadorDbMock  = credenciadorSalvo;
        Mockito.when( credenciadorRepository.findByCredenciadorCodigo(codigoCredenciador) ).thenReturn(credenciadorDbMock);
        Mockito.when(credenciadorRepository.save(credenciadorMock)).thenReturn(credenciadorDbMock);

        CredenciadorResponseDTO credenciadorResponseMock = crendeciadorSalvoResponse;
        Mockito.when(modelMapper.map(credenciadorDbMock, CredenciadorResponseDTO.class)).thenReturn(crendeciadorSalvoResponse);

        //Execução
        CredenciadorResponseDTO credenciadorResponse = credenciadorService.update(codigoCredenciador, credenciadorRequestMock);

        //verificação
        assertThat(credenciadorSalvo.getCredenciadorCodigo()).isNotNull();
        assertThat(credenciadorSalvo.getNome()).isEqualTo("Nome credenciador");
        assertThat(credenciadorSalvo.getStatus()).isEqualTo(Status.ATIVO);
    }


    @Test
    public void findAllTest(){

        Long codigoSequence = 1L;
        CredenciadorRequestCriteriaDTO credenciadorRequestCriteriaMock = credenciadorCriteriaDTO;
        Credenciador credenciadorMock  = credenciadorSalvo;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example example = Example.of(credenciadorSalvo, matcher);

        List<Credenciador> listaBuscaCredenciador = new ArrayList<>();
        listaBuscaCredenciador.add(credenciadorMock);

        List<CredenciadorResponseDTO> listaBuscaCredenciadorResponse = new ArrayList<>();
        listaBuscaCredenciadorResponse.add(crendeciadorSalvoResponse);

        Mockito.when( modelMapper.map(credenciadorRequestCriteriaMock, Credenciador.class)).thenReturn(credenciadorMock);
        Mockito.when(credenciadorRepository.findAll(example)).thenReturn(listaBuscaCredenciador);
        Mockito.when(modelMapper.map(listaBuscaCredenciador, new TypeToken<List<CredenciadorResponseDTO>>() {}.getType())).thenReturn(listaBuscaCredenciadorResponse);

        //Execução
        List<CredenciadorResponseDTO> credenciadorResponseList = credenciadorService.findAll(credenciadorRequestCriteriaMock);

        //verificação
        assertThat(credenciadorResponseList.size()).isEqualTo(1);
    }
}