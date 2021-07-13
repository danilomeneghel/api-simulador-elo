package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.dto.request.EmissorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.EmissorRequestDTO;
import br.com.elo.domain.dto.response.EmissorResponseDTO;
import br.com.elo.model.Emissor;
import br.com.elo.repository.BandeiraRepository;
import br.com.elo.repository.EmissorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

@ExtendWith(SpringExtension.class)
public class EmissorServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    EmissorService service;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    private EmissorRepository repository;

    @Mock
    private BandeiraRepository bandeiraRepository;

    private EmissorRequestDTO emissorSemBandeiraDTO = DadosMockUtil.criaNovoEmissorSemBandeiraDTO();
    private EmissorRequestDTO emissorComBandeiraDTO = DadosMockUtil.criaNovoEmissorComBandeiraDTO();
    private EmissorRequestDTO emissorUpdateInvalidoDTO = DadosMockUtil.criaNovoEmissorRequestDTO();
    private Emissor emissorUpdate = DadosMockUtil.emissorSalvo();
    private Emissor emissorComBandeira = DadosMockUtil.criaNovoEmissorComBandeiraService();
    private Emissor emissorSemBandeira = DadosMockUtil.criaNovoEmissorSemBandeiraService();
    private Emissor emissorSemBandeiraSalvo = DadosMockUtil.emissorSemBandeiraSalvo();
    private Emissor emissorComBandeiraSalvo = DadosMockUtil.emissorComBandeiraSalvo();
    private EmissorResponseDTO emissorSalvoResponse = DadosMockUtil.emissorSemBandeiraResponseDTO();
    private EmissorResponseDTO emissorComBandeiraSalvoResponse = DadosMockUtil.emissorComBandeiraResponseDTO();
    private EmissorRequestCriteriaDTO emissorCriteriaDTO = DadosMockUtil.dadosFindAllCriteriaEmissor();



    @Test
    public void testSaveEmissorSemBandeira(){
        Long codigoSequence = 1L;
        EmissorRequestDTO emissorRequestMock = emissorSemBandeiraDTO;

        Emissor emissorSemBandeiraMock = emissorSemBandeira;
        Mockito.when(modelMapper.map(emissorRequestMock, Emissor.class)).thenReturn(emissorSemBandeiraMock);
        Mockito.when(sequenceGenerator.generateSequence(Emissor.SEQUENCE_NAME)).thenReturn(codigoSequence);

        Emissor emissorSalvoMock  = emissorSemBandeiraSalvo;

        Mockito.when( repository.save(emissorSemBandeiraMock) ).thenReturn(emissorSalvoMock);
        EmissorResponseDTO emissorResponseSalvoMock = emissorSalvoResponse;
        Mockito.when(modelMapper.map(emissorSalvoMock, EmissorResponseDTO.class)).thenReturn(emissorResponseSalvoMock);

        //Execução
        EmissorResponseDTO simuladorSalvo = service.save(emissorRequestMock);

        //verificação
        assertThat(simuladorSalvo.getId()).isNotNull();
        assertThat(simuladorSalvo.getNomeEmissor()).isEqualTo("Cadastro de emissor");
        //assertThat(simuladorSalvo.getBandeiraCodigo()).isEqualTo(1L);
    }

    @Test
    public void testSaveEmissorComBandeira(){

        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;

        Emissor emissorMock = emissorComBandeira;
        Mockito.when(modelMapper.map(emissorRequestMock, Emissor.class)).thenReturn(emissorMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(emissorMock.getCodigoBandeira())).thenReturn(true);
        Emissor emissorSalvoMock  = emissorComBandeiraSalvo;

        Mockito.when( repository.save(emissorMock) ).thenReturn(emissorSalvoMock);
        EmissorResponseDTO emissorResponseSalvoMock = emissorComBandeiraSalvoResponse;
        Mockito.when(modelMapper.map(emissorSalvoMock, EmissorResponseDTO.class)).thenReturn(emissorResponseSalvoMock);

        //Execução
        EmissorResponseDTO simuladorSalvo = service.save(emissorRequestMock);

        //verificação
        assertThat(simuladorSalvo.getId()).isNotNull();
        assertThat(simuladorSalvo.getNomeEmissor()).isEqualTo("Cadastro de emissor");
        assertThat(simuladorSalvo.getCodigoBandeira()).isEqualTo(1);
        assertThat(simuladorSalvo.getCodigoEmissor()).isEqualTo(1);
    }

    @Test
    public void findAllTest(){

        EmissorRequestCriteriaDTO emissorRequestCriteriaMock = emissorCriteriaDTO;
        Emissor simuladorMock  = emissorSemBandeiraSalvo;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(emissorSemBandeiraSalvo, matcher);

        List<Emissor> listaBuscaSimulador = new ArrayList<>();
        listaBuscaSimulador.add(simuladorMock);

        List<EmissorResponseDTO> listaBuscaEmissorResponse = new ArrayList<>();
        listaBuscaEmissorResponse.add(emissorSalvoResponse);

        Mockito.when(modelMapper.map(emissorRequestCriteriaMock, Emissor.class)).thenReturn(simuladorMock);
        Mockito.when(repository.findAll(example)).thenReturn(listaBuscaSimulador);
        Mockito.when(modelMapper.map(listaBuscaSimulador, new TypeToken<List<EmissorResponseDTO>>() {}.getType())).thenReturn(listaBuscaEmissorResponse);
        //Execução
        List<EmissorResponseDTO> simuladorResponseList = service.findAll(emissorRequestCriteriaMock);
        //verificação
        assertThat(simuladorResponseList.size()).isEqualTo(1);

    }

    @Test
    public void findByIdTest(){
        Long codigoSequence = 1L;
        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Emissor emissorMock  = emissorComBandeira;

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.ofNullable(emissorMock));
        EmissorResponseDTO emissorResponseSalvoMock = emissorComBandeiraSalvoResponse;
        Mockito.when(modelMapper.map(emissorComBandeira, EmissorResponseDTO.class)).thenReturn(emissorResponseSalvoMock);

        //Execução
        EmissorResponseDTO emissorResponse = service.findById("5feb955b37b9fb7770ec3155");

        //verificação
        assertThat(emissorResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void notFoundByIdTest(){
        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Emissor emissorMock  = emissorComBandeira;
        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.empty());
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3155");
        });

        String expectedMessage = "Não foi possível localizar o emissor informado";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));

        //verificação
        assertThat(emissorComBandeiraSalvoResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }


    @Test
    public void updateTest(){
        Integer codigoEmissor = 1;
        Integer codigoBandeira = 1;

        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Emissor emissorMock  = emissorComBandeira;

        Mockito.when( modelMapper.map(emissorRequestMock, Emissor.class)).thenReturn(emissorMock);
        Emissor emissorDbMock  = emissorComBandeiraSalvo;
        Mockito.when(repository.save(emissorMock)).thenReturn(emissorDbMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(emissorMock.getCodigoBandeira())).thenReturn(true);

        //findByCodigoEmissorAndCodigoBandeira
        EmissorResponseDTO emissorResponseMock = emissorComBandeiraSalvoResponse;
        Mockito.when(repository.findByCodigoEmissorAndCodigoBandeira( emissorMock.getCodigoEmissor(),emissorMock.getCodigoBandeira())).thenReturn(emissorDbMock);
        Mockito.when(modelMapper.map(emissorDbMock, EmissorResponseDTO.class)).thenReturn(emissorResponseMock);

        //Execução
        EmissorResponseDTO emissorResponse = service.update(codigoEmissor, codigoBandeira,emissorRequestMock);

        //verificação
        assertThat(emissorResponse.getId()).isNotNull();
        assertThat(emissorResponse.getCodigoEmissor()).isEqualTo(codigoEmissor);
        assertThat(emissorResponse.getCodigoBandeira()).isEqualTo(codigoBandeira);
        assertThat(emissorResponse.getNomeEmissor()).isEqualTo("Cadastro de emissor");
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Integer codigoEmissor = 1;
        Integer bandeiraCodigo = 1;
        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Emissor emissorMock  = emissorComBandeira;

        Mockito.when(modelMapper.map(emissorRequestMock, Emissor.class)).thenReturn(emissorMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(emissorMock.getCodigoBandeira())).thenReturn(true);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoEmissor, bandeiraCodigo, emissorRequestMock);
        });

        String expectedMessage = MensagensRetorno.EMISSOR_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void findByCodigoTest(){
        Integer codigoEmissor = 1;
        Integer codigoBandeira = 1;
        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Emissor emissorMock  = emissorComBandeira;

        Mockito.when( repository.findByCodigoEmissor(codigoEmissor) ).thenReturn(emissorMock);
        EmissorResponseDTO emissorResponseMock = emissorComBandeiraSalvoResponse;
        Mockito.when(modelMapper.map(emissorMock, EmissorResponseDTO.class)).thenReturn(emissorComBandeiraSalvoResponse);
        Mockito.when(repository.findByCodigoEmissorAndCodigoBandeira( emissorMock.getCodigoEmissor(),emissorMock.getCodigoBandeira())).thenReturn(emissorMock);

        //Execução
        EmissorResponseDTO emissorResponse = service.findByCodigoBandeiraAndCodigoEmissor(codigoEmissor, codigoBandeira);

        //verificação
        assertThat(emissorResponse.getCodigoEmissor()).isEqualTo(1L);
    }

    @Test
    public void findEmissorNotFound(){
        Integer codigoEmissor = 999;
        Integer bandeiraCodigo = 999;
        EmissorRequestDTO emissorRequestMock = emissorComBandeiraDTO;
        Mockito.when(repository.findByCodigoEmissor(codigoEmissor)).thenReturn(null);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByCodigoBandeiraAndCodigoEmissor(codigoEmissor, bandeiraCodigo);
        });

        String expectedMessage = MensagensRetorno.EMISSOR_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }



}
