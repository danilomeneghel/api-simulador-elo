package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.Status;
import br.com.elo.domain.dto.request.AmbienteRequestCriteriaDTO;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import br.com.elo.model.Ambiente;
import br.com.elo.model.Protocolo;
import br.com.elo.repository.AmbienteRepository;
import br.com.elo.repository.ProtocoloRepository;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AmbienteServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    AmbienteService service;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    private AmbienteRepository repository;

    @Mock
    private ProtocoloRepository protocoloRepository;

    private AmbienteRequestDTO ambienteRequestDTO = DadosMockUtil.criaNovoSimuladorRequestDTO();
    private AmbienteRequestDTO simuladorUpdateInvalidoDTO = DadosMockUtil.criaNovoSimuladorRequestDTO();
    private Ambiente ambienteUpdate = DadosMockUtil.simuladorSalvo();
    private Ambiente ambiente = DadosMockUtil.criaNovoSimulador();
    private Ambiente ambienteSalvo = DadosMockUtil.simuladorSalvo();
    private AmbienteResponseDTO simuladorSalvoResponse = DadosMockUtil.ambienteResponseDTO();
    private AmbienteRequestCriteriaDTO simuladorCriteriaDTO = DadosMockUtil.dadosFindAllCriteriaSimulador();
    private Ambiente ambientePortaServidorProtocoloInexistente = DadosMockUtil.criaAmbientePortaServidorProtocoloInexistente();
    private Ambiente getAmbientePortaClienteProtocoloInexistente = DadosMockUtil.criaAmbientePortaClienteProtocoloInexistente();



    @Test
    public void saveTest(){
        Long codigoSequence = 1L;

        AmbienteRequestDTO simuladorRequestMock = ambienteRequestDTO;

        Ambiente ambienteMock = ambiente;

        Mockito.when(modelMapper.map(simuladorRequestMock, Ambiente.class)).thenReturn(ambienteMock);
        Mockito.when( sequenceGenerator.generateSequence(Ambiente.SEQUENCE_NAME)).thenReturn(codigoSequence);

        Ambiente ambienteSalvoMock = ambienteSalvo;

        Mockito.when(protocoloRepository.findByProtocoloSequence(1L)).thenReturn(DadosMockUtil.criaProtocoloTestRepository());
        Mockito.when( repository.save(ambienteMock) )
                .thenReturn(ambienteSalvoMock);
        AmbienteResponseDTO simuladorResponseSalvoMock = simuladorSalvoResponse;
        Mockito.when(modelMapper.map(ambienteSalvoMock, AmbienteResponseDTO.class)).thenReturn(simuladorResponseSalvoMock);

        //Execução
        AmbienteResponseDTO simuladorSalvo = service.save(simuladorRequestMock);

        //verificação
        assertThat(simuladorSalvo.getId()).isNotNull();
        assertThat(simuladorSalvo.getStatus()).isEqualTo(Status.ATIVO);
        assertThat(simuladorSalvo.getDescricao()).isEqualTo("Cadastro de injetor");
        assertThat(simuladorSalvo.getPortasClientes().get(0).getNomeHost()).isEqualTo("hostCliente");
        assertThat(simuladorSalvo.getPortasClientes().get(0).getNomePorta()).isEqualTo("portaCliente");
        assertThat(simuladorSalvo.getPortasClientes().get(0).getNumeroPorta()).isEqualTo(0);
        assertThat(simuladorSalvo.getPortasServidores().get(0).getNomeHost()).isEqualTo("hostServidor");
        assertThat(simuladorSalvo.getPortasServidores().get(0).getNomePorta()).isEqualTo("portaServidor");
        assertThat(simuladorSalvo.getPortasServidores().get(0).getNumeroPorta()).isEqualTo(0);
        assertThat(simuladorSalvo.getChaves().getChavePin()).isEqualTo("chave PIN");
        assertThat(simuladorSalvo.getChaves().getChaveCavv()).isEqualTo("chave CAVV");

    }

    @Test
    public void uniqueServerPortTest() {
        Ambiente ambienteSalvoMock = ambienteSalvo;
        Ambiente ambienteMock = ambiente;

        List<Ambiente> ambientesSalvos = new ArrayList<>();

        ambientesSalvos.add(ambienteSalvoMock);

        Mockito.when( repository.findPortaServidor(ambienteMock.getAmbienteSequence(), ambienteMock.getPortasServidores().get(0).getNumeroPorta()))
                .thenReturn(ambienteSalvoMock);

        boolean resultTrue = service.portaServidorExistente(ambienteMock, null);
        assertTrue(resultTrue);

        ambienteMock.getPortasServidores().get(0).setNumeroPorta(2);
        boolean resultFalse = service.portaServidorExistente(ambienteMock, null);
        assertFalse(resultFalse);
    }

    @Test
    public void uniqueClientPortTest() {
        Ambiente ambienteSalvoMock = ambienteSalvo;
        Ambiente ambienteMock = ambiente;

        List<Ambiente> ambientesSalvos = new ArrayList<>();

        ambientesSalvos.add(ambienteSalvoMock);

        Mockito.when( repository.findPortaCliente(ambienteMock.getAmbienteSequence(), ambienteMock.getPortasClientes().get(0).getNumeroPorta()))
                .thenReturn(ambienteSalvoMock);

        boolean resultTrue = service.portaClienteExistente(ambienteMock, null);
        assertTrue(resultTrue);

        ambienteMock.getPortasClientes().get(0).setNumeroPorta(2);
        boolean resultFalse = service.portaClienteExistente(ambienteMock, null);
        assertFalse(resultFalse);
    }

    @Test
    public void existsProtocoloTest() {
        Ambiente ambienteMock = ambiente;

        Protocolo protocoloMock = Protocolo.builder()
                .protocoloSequence(1L)
                .descricao("Protocolo de Teste").build();

        Mockito.when(protocoloRepository.findByProtocoloSequence(1L)).thenReturn(protocoloMock);

        assertTrue(service.existsProtocolo(ambienteMock));
    }

    @Test
    public void notExistsProtocoloForPortaClienteTest() {
        Ambiente ambienteMock = getAmbientePortaClienteProtocoloInexistente;

        Protocolo protocoloMock = Protocolo.builder()
                .protocoloSequence(1L)
                .descricao("Protocolo de Teste").build();
        Mockito.when(protocoloRepository.findByProtocoloSequence(100L)).thenReturn(null);

        assertFalse(service.existsProtocolo(ambienteMock));
    }

    @Test
    public void notExistsProtocoloForPortaServidorTest() {
        Ambiente ambienteMock = ambientePortaServidorProtocoloInexistente;

        Protocolo protocoloMock = Protocolo.builder()
                .protocoloSequence(1L)
                .descricao("Protocolo de Teste").build();

        assertFalse(service.existsProtocolo(ambienteMock));
    }


    @Test
    public void findByIdTest() {
        String id = "5feb955b37b9fb7770ec3155";

        AmbienteRequestDTO simuladorRequestMock = ambienteRequestDTO;
        Ambiente ambienteSalvoMock = ambienteSalvo;

        Mockito.when( repository.findById(id) )
                .thenReturn(Optional.ofNullable(ambienteSalvoMock));
        AmbienteResponseDTO simuladorResponseSalvoMock = simuladorSalvoResponse;
        Mockito.when(modelMapper.map(ambienteSalvoMock, AmbienteResponseDTO.class)).thenReturn(simuladorResponseSalvoMock);

        //Execução
        AmbienteResponseDTO simuladorSalvo = service.findById(id);

        //verificação
        assertThat(simuladorSalvo.getId()).isEqualTo(id);
        assertThat(simuladorSalvo.getStatus()).isEqualTo(Status.ATIVO);
        assertThat(simuladorSalvo.getDescricao()).isEqualTo("Cadastro de injetor");
    }

    @Test
    public void findByCodigoTest(){
        Long ambienteSequence = 1L;
        String id = "5feb955b37b9fb7770ec3155";
        AmbienteRequestDTO simuladorRequestMock = ambienteRequestDTO;
        Ambiente ambienteSalvoMock = ambienteSalvo;

        Mockito.when( repository.findByAmbienteSequence(ambienteSequence) )
                .thenReturn(ambienteSalvoMock);
        AmbienteResponseDTO simuladorResponseSalvoMock = simuladorSalvoResponse;
        Mockito.when(modelMapper.map(ambienteSalvoMock, AmbienteResponseDTO.class)).thenReturn(simuladorResponseSalvoMock);

        //Execução
        AmbienteResponseDTO simuladorSalvo = service.findByAmbienteSequence(ambienteSequence);

        //verificação
        assertThat(simuladorSalvo.getId()).isEqualTo(id);
        assertThat(simuladorSalvo.getStatus()).isEqualTo(Status.ATIVO);
        assertThat(simuladorSalvo.getDescricao()).isEqualTo("Cadastro de injetor");
    }

    @Test
    public void findSimuladorCodigoNotFound() {
        Long ambienteSequence = 999L;

        AmbienteRequestDTO simuladorRequestMock = ambienteRequestDTO;

        Mockito.when(repository.findByAmbienteSequence(ambienteSequence)).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByAmbienteSequence(ambienteSequence);
        });

        String expectedMessage = MensagensRetorno.AMBIENTE_NAO_LOCALIZADO.getDescricao();

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void updateTest() {
        Long codigoSequence = 1L;

        AmbienteRequestDTO ambienteRequestMock = ambienteRequestDTO;
        Ambiente ambienteMock = ambiente;

        Mockito.when( modelMapper.map(ambienteRequestMock, Ambiente.class)).thenReturn(ambienteMock);

        Ambiente ambienteDbMock = ambienteSalvo;

        Mockito.when(protocoloRepository.findByProtocoloSequence(1L)).thenReturn(DadosMockUtil.criaProtocoloTestRepository());
        Mockito.when( repository.findByAmbienteSequence(codigoSequence) ).thenReturn(ambienteDbMock);
        Mockito.when(repository.save(ambienteMock)).thenReturn(ambienteDbMock);

        AmbienteResponseDTO simuladorResponseMock = simuladorSalvoResponse;

        Mockito.when(modelMapper.map(ambienteDbMock, AmbienteResponseDTO.class)).thenReturn(simuladorSalvoResponse);

        //Execução
        AmbienteResponseDTO simuladorResponse = service.update(codigoSequence,ambienteRequestMock);

        //verificação
        assertThat(ambienteSalvo.getId()).isNotNull();
        assertThat(ambienteSalvo.getStatus()).isEqualTo(Status.ATIVO);
        assertThat(ambienteSalvo.getDescricao()).isEqualTo("Cadastro de injetor");
        assertThat(ambienteSalvo.getChaves().getChavePin()).isEqualTo("chave PIN");
        assertThat(ambienteSalvo.getChaves().getChaveCavv()).isEqualTo("chave CAVV");
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Long codigoSequence = 0L;
        AmbienteRequestDTO simuladorRequestMock = ambienteRequestDTO;
        Ambiente ambienteMock = ambiente;

        Mockito.when(modelMapper.map(simuladorRequestMock, Ambiente.class)).thenReturn(ambienteMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, simuladorRequestMock);
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void findAllTest(){
        AmbienteRequestCriteriaDTO simuladorRequestCriteriaMock = simuladorCriteriaDTO;
        Ambiente ambienteMock = ambienteSalvo;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example example = Example.of(ambienteSalvo, matcher);

        List<Ambiente> listaBuscaAmbiente = new ArrayList<>();
        listaBuscaAmbiente.add(ambienteMock);

        List<AmbienteResponseDTO> listaBuscaSimuladorResponse = new ArrayList<>();
        listaBuscaSimuladorResponse.add(simuladorSalvoResponse);

        Mockito.when( modelMapper.map(simuladorRequestCriteriaMock, Ambiente.class)).thenReturn(ambienteMock);
        Mockito.when(repository.findAll(example)).thenReturn(listaBuscaAmbiente);
        Mockito.when(modelMapper.map(listaBuscaAmbiente, new TypeToken<List<AmbienteResponseDTO>>() {}.getType())).thenReturn(listaBuscaSimuladorResponse);

        //Execução
        List<AmbienteResponseDTO> simuladorResponseList = service.findAll(simuladorRequestCriteriaMock);

        //verificação
        assertThat(simuladorResponseList.size()).isEqualTo(1);
    }

}
