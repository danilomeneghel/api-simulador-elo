package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.*;
import br.com.elo.domain.dto.response.ProtocoloResponseDTO;
import br.com.elo.model.Protocolo;
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
public class ProtocoloServiceTest {

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private ProtocoloService service;

    @Mock
    private  SequencesGeneratorService sequenceGenerator;

    @Mock
    ProtocoloRepository repository;

    private ProtocoloRequestDTO protocoloRequestDTO = DadosMockUtil.criaNovoProtocoloRequestDTO();
    private Protocolo protocolo = DadosMockUtil.criaNovoProtocolo();
    private Protocolo protocoloSalvo = DadosMockUtil.protocoloSalvo();
    private ProtocoloResponseDTO protocoloResponse = DadosMockUtil.protocoloResponseDTO();
    private ProtocoloRequestCriteriaDTO protocoloRequestCriteriaDTO = DadosMockUtil.criaNovoProtocoloRequestCriteriaDTO();

    @Test
    public void saveTest(){
        Long codigoSequence = 1L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        protocoloRequestMock.setBitsProtocolo(new ArrayList<>());
        Protocolo protocoloMock = protocolo;

        Mockito.when(modelMapper.map(protocoloRequestMock, Protocolo.class)).thenReturn(protocoloMock);
        Mockito.when( sequenceGenerator.generateSequence(Protocolo.SEQUENCE_NAME)).thenReturn(codigoSequence);

        Protocolo protocoloSalvoMock  = protocoloSalvo;
        Mockito.when( repository.save(protocoloMock) ).thenReturn(protocoloSalvoMock);

        ProtocoloResponseDTO protocoloResponseSalvoMock = protocoloResponse;

        Mockito.when(modelMapper.map(protocoloSalvoMock, ProtocoloResponseDTO.class)).thenReturn(protocoloResponseSalvoMock);

        //Execução
        ProtocoloResponseDTO protocoloSalvo = service.save(protocoloRequestMock);

        //verificação
        assertThat(protocoloSalvo.getId()).isNotNull();
        assertThat(protocoloSalvo.getProtocoloSequence()).isNotNull();
    }

    @Test
    public void updateTest(){
        Long codigoSequence = 1L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        protocoloRequestMock.setBitsProtocolo(new ArrayList<>());
        Protocolo protocoloMock = protocolo;

        Mockito.when(modelMapper.map(protocoloRequestMock, Protocolo.class)).thenReturn(protocoloMock);


        Protocolo protocoloSalvoMock  = protocoloSalvo;
        Mockito.when( repository.findByProtocoloSequence(codigoSequence) ).thenReturn(protocoloSalvoMock);
        Mockito.when( repository.save(protocoloMock) ).thenReturn(protocoloSalvoMock);

        ProtocoloResponseDTO protocoloResponseSalvoMock = protocoloResponse;

        Mockito.when(modelMapper.map(protocoloSalvoMock, ProtocoloResponseDTO.class)).thenReturn(protocoloResponseSalvoMock);

        //Execução
        ProtocoloResponseDTO protocoloSalvo = service.update(codigoSequence,protocoloRequestMock);

        //verificação
        assertThat(protocoloSalvo.getId()).isNotNull();
        assertThat(protocoloSalvo.getProtocoloSequence()).isNotNull();
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Long codigoSequence = 0L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        protocoloRequestMock.setBitsProtocolo(new ArrayList<>());
        Protocolo protocoloMock = protocolo;

        Mockito.when(modelMapper.map(protocoloRequestMock, Protocolo.class)).thenReturn(protocoloMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoSequence, protocoloRequestMock);
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateProtocoloInexistenteTest(){
        Long codigoSequence = 1L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        protocoloRequestMock.setBitsProtocolo(new ArrayList<>());
        Protocolo protocoloMock = protocolo;

        Mockito.when(modelMapper.map(protocoloRequestMock, Protocolo.class)).thenReturn(protocoloMock);
        Mockito.when( repository.findByProtocoloSequence(codigoSequence) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoSequence, protocoloRequestMock);
        });

        String expectedMessage = MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void verificaBitsMensagemTest(){
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        Protocolo protocoloMock = protocolo;

        LayoutBitsProtocoloRequestDTO campoMensagemTipoFixoSemTamanho = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).build();
        List<LayoutBitsProtocoloRequestDTO> listaCampos = new ArrayList<>();
        listaCampos.add(campoMensagemTipoFixoSemTamanho);
        protocoloRequestMock.setBitsProtocolo(listaCampos);


        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        String expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao() + " " + "Tamanho obrigátorio para tipo de tamanho FIXO";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);


        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoFixoComTamMin = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).tam(1).tamMinimo(1).build();
        listaCampos.add(campoMensagemTipoFixoComTamMin);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min ou Max, não deve ser informado para tipo de tamanho FIXO";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);


        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoVariavelComTamanho = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.LLVAR).tam(1).build();
        listaCampos.add(campoMensagemTipoVariavelComTamanho);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho não deve ser informado para tipo de tamanho LLVAR ou LLLVAR";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoVariavelSemTamMaxMin = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.LLVAR).build();
        listaCampos.add(campoMensagemTipoVariavelSemTamMaxMin);


        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min e Max obrigatórios para tipo de tamanho LLVAR ou LLLVAR";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoVariavelSemEncodeTam = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.LLLVAR).tamMaximo(1).tamMinimo(1).build();
        listaCampos.add(campoMensagemTipoVariavelSemEncodeTam);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A encapsulação do tamanho é obrigatória para tipo de tamanho LLVAR ou LLLVAR";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);


        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoVariavelEncodeBCDSemNibble = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.LLLVAR).tamMaximo(1).tamMinimo(1).encodeTamCampo(EncodeTamanho.BCD).encodeDadosCampo(EncodeDadosCampo.NROBCD).build();
        listaCampos.add(campoMensagemTipoVariavelEncodeBCDSemNibble);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibbleValue é obrigatório para tipo de tamanho LLVAR ou LLLVAR e formatação de dados Número BCD";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoMensagemTipoVariavelEncodeBCDSemPosition = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.LLLVAR).tamMaximo(1).tamMinimo(1).encodeTamCampo(EncodeTamanho.BCD).encodeDadosCampo(EncodeDadosCampo.NROBCD).bcdFillerNibbleValue("1").build();
        listaCampos.add(campoMensagemTipoVariavelEncodeBCDSemPosition);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibblePosition é obrigatório para tipo de tamanho LLVAR ou LLLVAR e formatação de dados Número BCD";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoFormatacaoDataComExpansaoDadosBin = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).tam(1).formatoDadosCampo(FormatacaoDadosCampo.DATAHORA).encodeDadosCampo(EncodeDadosCampo.NROASCII).expandirDadosBinarios(true).build();
        listaCampos.add(campoFormatacaoDataComExpansaoDadosBin);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção expandir dados binários só esta disponivel para o tipo de dados binários";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);


        listaCampos.clear();
        List<DescricaoDadosRequestDTO> descricaoDados = new ArrayList<>();
        descricaoDados.add(DescricaoDadosRequestDTO.builder().descricao("teste marcelo").conteudoCampo("teste").build());
        LayoutBitsProtocoloRequestDTO campoFormatacaoDataComDescricaoDados = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).tam(1).formatoDadosCampo(FormatacaoDadosCampo.DATAHORA).descricaoDadosCampo(descricaoDados).encodeDadosCampo(EncodeDadosCampo.NROASCII).build();
        listaCampos.add(campoFormatacaoDataComDescricaoDados);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção descricaoDadosCampo só esta disponivel para as formatações de dados CODPAIS,CODMOEDA,SRVCODE e MCC";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);


        listaCampos.clear();
        LayoutBitsProtocoloRequestDTO campoFormatacaoDataSemFormatacao = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).tam(1).formatoDadosCampo(FormatacaoDadosCampo.DADOSEMV).encodeDadosCampo(EncodeDadosCampo.NROASCII).formatacaoDataHora("YYYYmmdd").build();
        listaCampos.add(campoFormatacaoDataSemFormatacao);

        exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
        });

        expectedMessage = MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção formatacaoDataHora só esta disponivel para a formatacao de dados DATAHORA";
        actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void verificaBitsMensagemTestSucesso(){

        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        LayoutBitsProtocoloRequestDTO campoMensagemTipoFixoSemTamanho = LayoutBitsProtocoloRequestDTO.builder().tipoTamCampo(TipoTamanho.FIXO).tam(10).encodeDadosCampo(EncodeDadosCampo.NROASCII).build();
        List<LayoutBitsProtocoloRequestDTO> listaCampos = new ArrayList<>();
        listaCampos.add(campoMensagemTipoFixoSemTamanho);
        protocoloRequestMock.setBitsProtocolo(listaCampos);
        service.validarLayoutDeBitsProtocolo(protocoloRequestMock.getBitsProtocolo());
    }

    @Test
    public void findByIdTest(){
        Long codigoSequence = 1L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        Protocolo protocoloSalvoMock  = protocoloSalvo;

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155")).thenReturn(Optional.ofNullable(protocoloSalvoMock));
        ProtocoloResponseDTO protocoloResponseSalvoMock = protocoloResponse;
        Mockito.when(modelMapper.map(protocoloSalvoMock, ProtocoloResponseDTO.class)).thenReturn(protocoloResponseSalvoMock);

        //Execução
        ProtocoloResponseDTO protocoloResponse = service.findById("5feb955b37b9fb7770ec3155");

        //verificação
        assertThat(protocoloResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void notFoundByIdTest(){

        Mockito.when(repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3155");
        });

        String expectedMessage = MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findByCodigoTest(){
        Long codigoSequence = 1L;
        ProtocoloRequestDTO protocoloRequestMock = protocoloRequestDTO;
        Protocolo protocoloSalvoMock  = protocoloSalvo;

        Mockito.when( repository.findByProtocoloSequence(codigoSequence) ).thenReturn(protocoloSalvoMock);
        ProtocoloResponseDTO protocoloResponseSalvoMock = protocoloResponse;
        Mockito.when(modelMapper.map(protocoloSalvoMock, ProtocoloResponseDTO.class)).thenReturn(protocoloResponseSalvoMock);

        //Execução
        ProtocoloResponseDTO protocoloResponse = service.findByProtocoloSequence(codigoSequence);

        //verificação
        assertThat(protocoloResponse.getProtocoloSequence()).isEqualTo(1L);
    }

    @Test
    public void notFoundByCodigoTest(){

        Mockito.when(repository.findByProtocoloSequence(1L) ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByProtocoloSequence(1L);
        });

        String expectedMessage = MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void findAllTest(){


        ProtocoloRequestCriteriaDTO protocoloRequestCriteriaMock = protocoloRequestCriteriaDTO;
        Protocolo protocoloMock  = new Protocolo();

        List<Protocolo> listaBuscaProtocolo = new ArrayList<>();
        listaBuscaProtocolo.add(protocoloMock);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<Protocolo> protocoloExample = Example.of(protocoloMock, matcher);

        List<ProtocoloResponseDTO> listaBuscaProtocoloResponse = new ArrayList<>();
        listaBuscaProtocoloResponse.add(new ProtocoloResponseDTO());

        Mockito.when( modelMapper.map(protocoloRequestCriteriaMock, Protocolo.class)).thenReturn(protocoloMock);
        Mockito.when( repository.findAll(protocoloExample)).thenReturn(listaBuscaProtocolo);
        Mockito.when(modelMapper.map(listaBuscaProtocolo, new TypeToken<List<ProtocoloResponseDTO>>() {}.getType())).thenReturn(listaBuscaProtocoloResponse);

        //Execução
        List<ProtocoloResponseDTO> protocoloResponseList = service.findAll(protocoloRequestCriteriaMock);

        //verificação
        assertThat(protocoloResponseList.size()).isEqualTo(1);
    }



}
