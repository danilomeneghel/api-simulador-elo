package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.dto.request.FluxoTransacionalRequestCriteriaDTO;
import br.com.elo.domain.dto.request.FluxoTransacionalRequestDTO;
import br.com.elo.domain.dto.response.FluxoTransacionalResponseDTO;
import br.com.elo.model.BitMensagem;
import br.com.elo.model.FluxoTransacional;
import br.com.elo.model.MensagemISO8583;
import br.com.elo.repository.FluxoTransacionalRepository;
import br.com.elo.repository.MensagemISO8583Repository;
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
public class FluxoTransacionalServiceTest {

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private FluxoTransacionalService service;

    @Mock
    private  SequencesGeneratorService sequenceGenerator;

    @Mock
    FluxoTransacionalRepository repository;

    @Mock
    MensagemISO8583Repository mensagemISO8583Repository;

    private FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO = DadosMockUtil.criaNovoFluxoTransacionalRequestDTO();
    private FluxoTransacional fluxoTransacional = DadosMockUtil.criaNovoFluxoTransacional();
    private FluxoTransacional fluxoTransacionalSalvo = DadosMockUtil.fluxoTransacionalSalvo();
    private FluxoTransacionalResponseDTO fluxoTransacionalResponse = DadosMockUtil.fluxoTransacionalDB();
    private FluxoTransacionalRequestCriteriaDTO fluxoTransacionalRequestCriteriaDTO = DadosMockUtil.criaNovoFluxoTransacionalRequestCriteriaDTO();

    @Test
    public void saveTest(){
        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;
        FluxoTransacional fluxoTransacionalMock = fluxoTransacional;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        MensagemISO8583 mensagemISO8583 =  DadosMockUtil.criaNovaMensagemISO8583();
        mensagemISO8583.setBitsMensagem(new ArrayList<>());
        mensagemISO8583.getBitsMensagem().add(BitMensagem.builder().numeroDoBit(11).build());

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);
        Mockito.when(modelMapper.map(fluxoTransacionalRequestMock, FluxoTransacional.class)).thenReturn(fluxoTransacionalMock);
        Mockito.when( sequenceGenerator.generateSequence(FluxoTransacional.SEQUENCE_NAME)).thenReturn(codigoSequence);

        FluxoTransacional fluxoTransacionalSalvoMock  = fluxoTransacionalSalvo;
        Mockito.when( repository.save(fluxoTransacionalMock)).thenReturn(fluxoTransacionalSalvoMock);

        FluxoTransacionalResponseDTO fluxoTransacionalResponseSalvoMock = fluxoTransacionalResponse;

        Mockito.when(modelMapper.map(fluxoTransacionalSalvoMock, FluxoTransacionalResponseDTO.class)).thenReturn(fluxoTransacionalResponseSalvoMock);

        //Execução
        FluxoTransacionalResponseDTO fluxoTransacionalSalvo = service.save(fluxoTransacionalRequestMock);

        //verificação
        assertThat(fluxoTransacionalSalvo.getId()).isNotNull();
        assertThat(fluxoTransacionalSalvo.getFluxoTransacionalSequence()).isNotNull();
    }

    @Test
    public void saveMensagensPerna12InvalidasTest(){

        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(false);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_SOL_PERNA_UM_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(false);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        expectedMessage = MensagensRetorno.MENSAGEM_SOL_PERNA_DOIS_NAO_LOCALIZADA.getDescricao();
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void saveMensagensPernas34InvalidasTest(){

        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaDoisSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(false);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_RESP_PERNA_TRES_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaDoisSequence(null);
        fluxoTransacionalRequestMock.setMensagemRespostaPernaTresSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(false);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        expectedMessage = MensagensRetorno.MENSAGEM_RESP_PERNA_QUATRO_NAO_LOCALIZADA.getDescricao();
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void saveBitInvalidoMensagensPernas14Test(){

        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(null);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void saveBitInvalidoMensagensPernas23Test(){

        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        fluxoTransacionalRequestMock.setBitVinculacaoMensagensPernasUmQuatro(null);

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(null);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void updateTest(){
        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;
        FluxoTransacional fluxoTransacionalMock = fluxoTransacional;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        MensagemISO8583 mensagemISO8583 =  DadosMockUtil.criaNovaMensagemISO8583();
        mensagemISO8583.setBitsMensagem(new ArrayList<>());
        mensagemISO8583.getBitsMensagem().add(BitMensagem.builder().numeroDoBit(11).build());

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);

        Mockito.when(modelMapper.map(fluxoTransacionalRequestMock, FluxoTransacional.class)).thenReturn(fluxoTransacionalMock);


        FluxoTransacional fluxoTransacionalSalvoMock  = fluxoTransacionalSalvo;
        Mockito.when( repository.findByFluxoTransacionalSequence(codigoSequence) ).thenReturn(fluxoTransacionalSalvoMock);
        Mockito.when( repository.save(fluxoTransacionalMock) ).thenReturn(fluxoTransacionalSalvoMock);

        FluxoTransacionalResponseDTO fluxoTransacionalResponseSalvoMock = fluxoTransacionalResponse;

        Mockito.when(modelMapper.map(fluxoTransacionalSalvoMock, FluxoTransacionalResponseDTO.class)).thenReturn(fluxoTransacionalResponseSalvoMock);

        //Execução
        FluxoTransacionalResponseDTO fluxoTransacionalSalvo = service.update(codigoSequence,fluxoTransacionalRequestMock);

        //verificação
        assertThat(fluxoTransacionalSalvo.getId()).isNotNull();
        assertThat(fluxoTransacionalSalvo.getFluxoTransacionalSequence()).isNotNull();
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Long codigoSequence = 0L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateCodigoInexistenteTest(){
        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;
        FluxoTransacional fluxoTransacionalMock = fluxoTransacional;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        MensagemISO8583 mensagemISO8583 =  DadosMockUtil.criaNovaMensagemISO8583();
        mensagemISO8583.setBitsMensagem(new ArrayList<>());
        mensagemISO8583.getBitsMensagem().add(BitMensagem.builder().numeroDoBit(11).build());

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);
        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(mensagemISO8583);



        Mockito.when( repository.findByFluxoTransacionalSequence(codigoSequence) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void updateMensagemPerna1234InvalidasTest(){
        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(false);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_SOL_PERNA_UM_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(false);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        expectedMessage = MensagensRetorno.MENSAGEM_SOL_PERNA_DOIS_NAO_LOCALIZADA.getDescricao();
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaDoisSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(false);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        expectedMessage = MensagensRetorno.MENSAGEM_RESP_PERNA_TRES_NAO_LOCALIZADA.getDescricao();
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaUmSequence(null);
        fluxoTransacionalRequestMock.setMensagemSolicitacaoPernaDoisSequence(null);
        fluxoTransacionalRequestMock.setMensagemRespostaPernaTresSequence(null);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(false);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, fluxoTransacionalRequestMock);
        });

        expectedMessage = MensagensRetorno.MENSAGEM_RESP_PERNA_QUATRO_NAO_LOCALIZADA.getDescricao();
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void updateBitInvalidoMensagensPernas14Test(){

        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasUmQuatro())).thenReturn(null);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence,fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void updateBitInvalidoMensagensPernas23Test(){
        Long codigoSequence = 1L;
        FluxoTransacionalRequestDTO fluxoTransacionalRequestMock = fluxoTransacionalRequestDTO;

        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaUmSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaTresSequence())).thenReturn(true);
        Mockito.when( mensagemISO8583Repository.existsByMensagemSequence(fluxoTransacionalRequestMock.getMensagemRespostaPernaQuatroSequence())).thenReturn(true);

        fluxoTransacionalRequestMock.setBitVinculacaoMensagensPernasUmQuatro(null);

        Mockito.when( mensagemISO8583Repository.findNroBitByMensagemSequence(fluxoTransacionalRequestMock.getMensagemSolicitacaoPernaDoisSequence(),fluxoTransacionalRequestMock.getBitVinculacaoMensagensPernasDoisTres())).thenReturn(null);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence,fluxoTransacionalRequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void findByIdTest(){

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155")).thenReturn(Optional.ofNullable(fluxoTransacionalSalvo));
        FluxoTransacionalResponseDTO fluxoTransacionalResponseSalvoMock = fluxoTransacionalResponse;
        Mockito.when(modelMapper.map(fluxoTransacionalSalvo, FluxoTransacionalResponseDTO.class)).thenReturn(fluxoTransacionalResponseSalvoMock);

        //Execução
        FluxoTransacionalResponseDTO fluxoTransacionalResponse = service.findById("5feb955b37b9fb7770ec3155");

        //verificação
        assertThat(fluxoTransacionalResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void notFoundByIdTest(){

        Mockito.when(repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3155");
        });

        String expectedMessage = MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findByCodigoTest(){

        Long codigoSequence = 1L;
        FluxoTransacional fluxoTransacionalSalvoMock  = fluxoTransacionalSalvo;

        Mockito.when( repository.findByFluxoTransacionalSequence(codigoSequence) ).thenReturn(fluxoTransacionalSalvoMock);
        FluxoTransacionalResponseDTO fluxoTransacionalResponseSalvoMock = fluxoTransacionalResponse;
        Mockito.when(modelMapper.map(fluxoTransacionalSalvoMock, FluxoTransacionalResponseDTO.class)).thenReturn(fluxoTransacionalResponseSalvoMock);

        //Execução
        FluxoTransacionalResponseDTO fluxoTransacionalResponse = service.findByFluxoTransacionalSequence(codigoSequence);

        //verificação
        assertThat(fluxoTransacionalResponse.getFluxoTransacionalSequence()).isEqualTo(1L);
    }

    @Test
    public void notFoundByCodigoTest(){

        Mockito.when(repository.findByFluxoTransacionalSequence(1L) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByFluxoTransacionalSequence(1L);
        });

        String expectedMessage = MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }



    @Test
    public void findAllTest(){

        FluxoTransacionalRequestCriteriaDTO fluxoTransacionalRequestCriteriaMock = fluxoTransacionalRequestCriteriaDTO;
        FluxoTransacional fluxoTransacionalMock  = new FluxoTransacional();

        List<FluxoTransacional> listaBuscaFluxoTransacional = new ArrayList<>();
        listaBuscaFluxoTransacional.add(fluxoTransacionalMock);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<FluxoTransacional> fluxoTransacionalExample = Example.of(fluxoTransacionalMock, matcher);

        List<FluxoTransacionalResponseDTO> listaBuscaFluxoTransacionalResponse = new ArrayList<>();
        listaBuscaFluxoTransacionalResponse.add(new FluxoTransacionalResponseDTO());

        Mockito.when( modelMapper.map(fluxoTransacionalRequestCriteriaMock, FluxoTransacional.class)).thenReturn(fluxoTransacionalMock);
        Mockito.when( repository.findAll(fluxoTransacionalExample)).thenReturn(listaBuscaFluxoTransacional);
        Mockito.when(modelMapper.map(listaBuscaFluxoTransacional, new TypeToken<List<FluxoTransacionalResponseDTO>>() {}.getType())).thenReturn(listaBuscaFluxoTransacionalResponse);

        //Execução
        List<FluxoTransacionalResponseDTO> fluxoTransacionalResponseList = service.findAll(fluxoTransacionalRequestCriteriaMock);

        //verificação
        assertThat(fluxoTransacionalResponseList.size()).isEqualTo(1);
    }
}
