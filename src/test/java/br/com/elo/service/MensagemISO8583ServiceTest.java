package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.*;
import br.com.elo.domain.dto.response.MensagemISO8583ResponseDTO;
import br.com.elo.model.BitMensagem;
import br.com.elo.model.LayoutBitsProtocolo;
import br.com.elo.model.MensagemISO8583;
import br.com.elo.model.Protocolo;
import br.com.elo.repository.MensagemISO8583Repository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MensagemISO8583ServiceTest {

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private MensagemISO8583Service service;

    @Mock
    private  SequencesGeneratorService sequenceGenerator;

    @Mock
    MensagemISO8583Repository repository;

    @Mock
    ProtocoloRepository protocoloRepository;

    private MensagemISO8583RequestDTO mensagemISO8583RequestDTO = DadosMockUtil.criaNovaMensagemISO8583RequestDTO();
    private MensagemISO8583 mensagemISO8583 = DadosMockUtil.criaNovaMensagemISO8583();
    private MensagemISO8583 mensagemISO8583Salva = DadosMockUtil.mensagemISO8583Salva();
    private MensagemISO8583ResponseDTO mensagemISO8583Response = DadosMockUtil.mensagemISO8583ResponseDTO();
    private MensagemISO8583RequestCriteriaDTO mensagemISO8583RequestCriteriaDTO = DadosMockUtil.criaNovaMensagemISO8583RequestCriteriaDTO();

    private Protocolo protocoloSalvoMock = DadosMockUtil.protocoloSalvo();

    @Test
    public void saveTest(){
        Long codigoSequence = 1L;
        MensagemISO8583RequestDTO mensagemISO8583RequestMock = mensagemISO8583RequestDTO;
        mensagemISO8583RequestMock.setBitsMensagem(new ArrayList<>());

        MensagemISO8583 mensagemISO8583Mock = mensagemISO8583;
        mensagemISO8583Mock.setBitsMensagem(new ArrayList<>());

        Mockito.when(modelMapper.map(mensagemISO8583RequestMock, MensagemISO8583.class)).thenReturn(mensagemISO8583Mock);
        Mockito.when( protocoloRepository.existsByProtocoloSequence(mensagemISO8583Mock.getProtocoloSequence()) ).thenReturn(true);

        protocoloSalvoMock.setBitsProtocolo(new ArrayList<>());
        Mockito.when( protocoloRepository.findByProtocoloSequence(codigoSequence) ).thenReturn(protocoloSalvoMock);

        Mockito.when( sequenceGenerator.generateSequence(MensagemISO8583.SEQUENCE_NAME)).thenReturn(codigoSequence);

        MensagemISO8583 mensagemISO8583SalvoMock  = mensagemISO8583Salva;
        Mockito.when( repository.save(mensagemISO8583Mock) ).thenReturn(mensagemISO8583SalvoMock);

        MensagemISO8583ResponseDTO mensagemISO8583ResponseSalvoMock = mensagemISO8583Response;

        Mockito.when(modelMapper.map(mensagemISO8583SalvoMock, MensagemISO8583ResponseDTO.class)).thenReturn(mensagemISO8583ResponseSalvoMock);

        //Execução
        MensagemISO8583ResponseDTO mensagemISO8583ResponseSalvo = service.save(mensagemISO8583RequestMock);

        //verificação
        assertThat(mensagemISO8583ResponseSalvo.getId()).isNotNull();
        assertThat(mensagemISO8583ResponseSalvo.getMensagemSequence()).isNotNull();
    }

    @Test
    public void updateTest(){
        Long codigoSequence = 1L;
        MensagemISO8583RequestDTO mensagemISO8583RequestMock = mensagemISO8583RequestDTO;
        mensagemISO8583RequestMock.setBitsMensagem(new ArrayList<>());
        MensagemISO8583 mensagemISO8583Mock = mensagemISO8583;
        mensagemISO8583Mock.setBitsMensagem(new ArrayList<>());

        Mockito.when(modelMapper.map(mensagemISO8583RequestMock, MensagemISO8583.class)).thenReturn(mensagemISO8583Mock);
        Mockito.when( protocoloRepository.existsByProtocoloSequence(mensagemISO8583Mock.getProtocoloSequence()) ).thenReturn(true);

        MensagemISO8583 mensagemISO8583SalvoMock  = mensagemISO8583Salva;
        Mockito.when( repository.findByMensagemSequence(codigoSequence) ).thenReturn(mensagemISO8583SalvoMock);

        protocoloSalvoMock.setBitsProtocolo(new ArrayList<>());
        Mockito.when( protocoloRepository.findByProtocoloSequence(codigoSequence) ).thenReturn(protocoloSalvoMock);

        Mockito.when( repository.findByMensagemSequence(codigoSequence) ).thenReturn(mensagemISO8583Mock);

        Mockito.when( repository.save(mensagemISO8583Mock) ).thenReturn(mensagemISO8583SalvoMock);

        MensagemISO8583ResponseDTO mensagemISO8583ResponseSalvoMock = mensagemISO8583Response;

        Mockito.when(modelMapper.map(mensagemISO8583SalvoMock, MensagemISO8583ResponseDTO.class)).thenReturn(mensagemISO8583ResponseSalvoMock);

        //Execução
        MensagemISO8583ResponseDTO mensagemISO8583ResponseSalva = service.update(codigoSequence,mensagemISO8583RequestMock);

        //verificação
        assertThat(mensagemISO8583ResponseSalva.getId()).isNotNull();
        assertThat(mensagemISO8583ResponseSalva.getMensagemSequence()).isNotNull();
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Long codigoSequence = 0L;
        MensagemISO8583RequestDTO mensagemISO8583RequestMock = mensagemISO8583RequestDTO;
        mensagemISO8583RequestMock.setBitsMensagem(new ArrayList<>());
        MensagemISO8583 mensagemISO8583Mock = mensagemISO8583;

        Mockito.when(modelMapper.map(mensagemISO8583RequestMock, MensagemISO8583.class)).thenReturn(mensagemISO8583Mock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, mensagemISO8583RequestMock);
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateMensagemISO8583InexistenteTest(){
        Long codigoSequence = 1L;
        MensagemISO8583RequestDTO mensagemISO8583RequestMock = mensagemISO8583RequestDTO;
        mensagemISO8583RequestMock.setBitsMensagem(new ArrayList<>());
        MensagemISO8583 mensagemISO8583Mock = mensagemISO8583;

        Mockito.when(modelMapper.map(mensagemISO8583RequestMock, MensagemISO8583.class)).thenReturn(mensagemISO8583Mock);
        Mockito.when( protocoloRepository.existsByProtocoloSequence(mensagemISO8583Mock.getProtocoloSequence()) ).thenReturn(true);
        Mockito.when( repository.findByMensagemSequence(codigoSequence) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoSequence, mensagemISO8583RequestMock);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void validaNroBitTest(){

        MensagemISO8583 mensagemISO8583RequestMock = mensagemISO8583;
        BitMensagem nroBitExistente = BitMensagem.builder().numeroDoBit(2).build();
        List<BitMensagem> listaCampos = new ArrayList<>();
        listaCampos.add(nroBitExistente);

        LayoutBitsProtocolo numeroBit = LayoutBitsProtocolo.builder().numeroDoBit(2).build();
        List<LayoutBitsProtocolo> listaCamposLayout = new ArrayList<>();
        listaCamposLayout.add(numeroBit);
        protocoloSalvoMock.setBitsProtocolo(listaCamposLayout);
        Mockito.when( protocoloRepository.findNroBitByProtocoloSequence(1L,nroBitExistente.getNumeroDoBit()) ).thenReturn(protocoloSalvoMock);

        mensagemISO8583RequestMock.setBitsMensagem(listaCampos);
        assertThat(service.validarPresencaBitProtocolo(1L,nroBitExistente.getNumeroDoBit())).isTrue();

        Mockito.when( protocoloRepository.findNroBitByProtocoloSequence(1L,nroBitExistente.getNumeroDoBit()) ).thenReturn(null);

        mensagemISO8583RequestMock.setBitsMensagem(listaCampos);
        assertThat(service.validarPresencaBitProtocolo(1L,nroBitExistente.getNumeroDoBit())).isFalse();

        protocoloSalvoMock.getBitsProtocolo().clear();
        protocoloSalvoMock.setBitsProtocolo(null);
        Mockito.when( protocoloRepository.findNroBitByProtocoloSequence(1L,nroBitExistente.getNumeroDoBit()) ).thenReturn(null);

        mensagemISO8583RequestMock.setBitsMensagem(listaCampos);
        assertThat(service.validarPresencaBitProtocolo(1L,nroBitExistente.getNumeroDoBit())).isFalse();
    }

    @Test
    public void validaNrosBitsTest(){

        MensagemISO8583 mensagemISO8583RequestMock = mensagemISO8583;
        BitMensagem nroBitExistente = BitMensagem.builder().numeroDoBit(2).build();
        List<BitMensagem> listaCampos = new ArrayList<>();
        listaCampos.add(nroBitExistente);
        mensagemISO8583RequestMock.setBitsMensagem(listaCampos);

        LayoutBitsProtocolo numeroBit = LayoutBitsProtocolo.builder().numeroDoBit(2).build();
        List<LayoutBitsProtocolo> listaCamposLayout = new ArrayList<>();
        listaCamposLayout.add(numeroBit);
        protocoloSalvoMock.setBitsProtocolo(listaCamposLayout);
        Mockito.when( protocoloRepository.findNroBitByProtocoloSequence(1L,nroBitExistente.getNumeroDoBit()) ).thenReturn(protocoloSalvoMock);

        mensagemISO8583RequestMock.setBitsMensagem(listaCampos);
        service.validarPresencaBitsProtocolo(1L,mensagemISO8583RequestMock.getBitsMensagem());


        protocoloSalvoMock.getBitsProtocolo().clear();
        protocoloSalvoMock.setBitsProtocolo(null);
        Mockito.when( protocoloRepository.findNroBitByProtocoloSequence(1L,nroBitExistente.getNumeroDoBit()) ).thenReturn(protocoloSalvoMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarPresencaBitsProtocolo(1L,mensagemISO8583RequestMock.getBitsMensagem());
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Bit número 2 não cadastrado no protocolo";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void preencherDescricaoBitTest()
    {
        MensagemISO8583 mensagemISO8583Mock = mensagemISO8583;
        BitMensagem nroBitExistente = BitMensagem.builder().numeroDoBit(2).build();
        List<BitMensagem> listaCampos = new ArrayList<>();
        listaCampos.add(nroBitExistente);
        mensagemISO8583Mock.setBitsMensagem(listaCampos);

        LayoutBitsProtocolo numeroBit = LayoutBitsProtocolo.builder().numeroDoBit(2).descricao("Cartao Digitado").build();
        List<LayoutBitsProtocolo> listaCamposLayout = new ArrayList<>();
        listaCamposLayout.add(numeroBit);
        protocoloSalvoMock.setBitsProtocolo(listaCamposLayout);

        service.preencherDescricaoBitsMensagemProtocolo(mensagemISO8583Mock.getBitsMensagem(),protocoloSalvoMock.getBitsProtocolo());

        assertThat(listaCampos.get(0).getDescricao()).isEqualTo(protocoloSalvoMock.getBitsProtocolo().get(0).getDescricao());
    }

    @Test
    public void findByIdTest(){
        Long codigoSequence = 1L;
        MensagemISO8583RequestDTO mensagemRequestMock = mensagemISO8583RequestDTO;
        MensagemISO8583 mensagemISO8583SalvaMock  = mensagemISO8583Salva;

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155")).thenReturn(Optional.ofNullable(mensagemISO8583SalvaMock));
        MensagemISO8583ResponseDTO mensagemResponseSalvoMock = mensagemISO8583Response;
        Mockito.when(modelMapper.map(mensagemISO8583SalvaMock, MensagemISO8583ResponseDTO.class)).thenReturn(mensagemResponseSalvoMock);

        //Execução
        MensagemISO8583ResponseDTO mensagemResponse = service.findById("5feb955b37b9fb7770ec3155");

        //verificação
        assertThat(mensagemResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void notFoundByIdTest(){

        Mockito.when(repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3155");
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findByCodigoTest(){
        Long codigoSequence = 1L;
        MensagemISO8583RequestDTO mensagemRequestMock = mensagemISO8583RequestDTO;
        MensagemISO8583 mensagemISO8583SalvaMock  = mensagemISO8583Salva;

        Mockito.when( repository.findByMensagemSequence(codigoSequence) ).thenReturn(mensagemISO8583SalvaMock);
        MensagemISO8583ResponseDTO mensagemResponseSalvoMock = mensagemISO8583Response;
        Mockito.when(modelMapper.map(mensagemISO8583SalvaMock, MensagemISO8583ResponseDTO.class)).thenReturn(mensagemResponseSalvoMock);

        //Execução
        MensagemISO8583ResponseDTO mensagemResponse = service.findByMensagemSequence(codigoSequence);

        //verificação
        assertThat(mensagemResponse.getMensagemSequence()).isEqualTo(1L);
    }

    @Test
    public void notFoundByCodigoTest(){

        Mockito.when(repository.findByMensagemSequence(1L) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByMensagemSequence(1L);
        });

        String expectedMessage = MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void findAllTest(){

        MensagemISO8583RequestCriteriaDTO mensagemISORequestCriteriaMock = mensagemISO8583RequestCriteriaDTO;
        MensagemISO8583 mensagemISO8583Mock  = new MensagemISO8583();

        List<MensagemISO8583> listaBuscaMensagem = new ArrayList<>();
        listaBuscaMensagem.add(mensagemISO8583Mock);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<MensagemISO8583> mensagemISO8583Example = Example.of(mensagemISO8583Mock, matcher);

        List<MensagemISO8583ResponseDTO> listaBuscaMensagemISO8583Response = new ArrayList<>();
        listaBuscaMensagemISO8583Response.add(new MensagemISO8583ResponseDTO());

        Mockito.when( modelMapper.map(mensagemISORequestCriteriaMock, MensagemISO8583.class)).thenReturn(mensagemISO8583Mock);
        Mockito.when( repository.findAll(mensagemISO8583Example)).thenReturn(listaBuscaMensagem);
        Mockito.when(modelMapper.map(listaBuscaMensagem, new TypeToken<List<MensagemISO8583ResponseDTO>>() {}.getType())).thenReturn(listaBuscaMensagemISO8583Response);

        //Execução
        List<MensagemISO8583ResponseDTO> mensagemResponseList = service.findAll(mensagemISORequestCriteriaMock);

        //verificação
        assertThat(mensagemResponseList.size()).isEqualTo(1);
    }

}
