package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.dto.request.CartaoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CartaoRequestDTO;
import br.com.elo.domain.dto.response.CartaoResponseDTO;
import br.com.elo.model.Cartao;
import br.com.elo.repository.CartaoRepository;
import br.com.elo.repository.EmissorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
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
public class CartaoServiceTest {

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private CartaoService service;

    @Mock
    private  SequencesGeneratorService sequenceGenerator;

    @Mock
    CartaoRepository repository;

    @Mock
    EmissorRepository emissorRepository;

    private CartaoRequestDTO cartaoRequestDTO = DadosMockUtil.criaNovoCartaoRequestDTO();
    private Cartao cartao = DadosMockUtil.criaNovoCartao();
    private Cartao cartaoSalvo = DadosMockUtil.cartaoSalvo();
    private CartaoResponseDTO cartaoResponse = DadosMockUtil.cartaoResponseDTO();
    private CartaoRequestCriteriaDTO cartaoRequestCriteriaDTO = DadosMockUtil.criaNovoCartaoRequestCriteriaDTO();

    @Test
    public void saveTest(){
        CartaoRequestDTO cartaoRequestMock = cartaoRequestDTO;
        Cartao cartaoMock = cartao;

        Mockito.when(modelMapper.map(cartaoRequestMock, Cartao.class)).thenReturn(cartaoMock);
        Mockito.when( emissorRepository.existsByCodigoEmissorAndCodigoBandeira(cartaoRequestMock.getCodigoEmissor(), cartaoRequestMock.getCodigoBandeira()) ).thenReturn(true);

        Cartao cartaoSalvoMock  = cartaoSalvo;
        Mockito.when( repository.save(cartaoMock) ).thenReturn(cartaoSalvoMock);

        CartaoResponseDTO cartaoResponseSalvoMock = cartaoResponse;

        Mockito.when(modelMapper.map(cartaoSalvoMock, CartaoResponseDTO.class)).thenReturn(cartaoResponseSalvoMock);

        //Execução
        CartaoResponseDTO cartaoSalvo = service.save(cartaoRequestMock);

        //verificação
        assertThat(cartaoSalvo.getId()).isNotNull();
        assertThat(cartaoSalvo.getPan()).isNotNull();
    }

    @Test
    public void updateTest(){
        String pan = "1";
        CartaoRequestDTO cartaoRequestMock = cartaoRequestDTO;
        Cartao cartaoMock = cartao;

        Mockito.when(modelMapper.map(cartaoRequestMock, Cartao.class)).thenReturn(cartaoMock);


        Cartao cartaoSalvoMock  = cartaoSalvo;
        Mockito.when( repository.findByPan(pan) ).thenReturn(cartaoSalvoMock);
        Mockito.when(emissorRepository.existsByCodigoEmissorAndCodigoBandeira(cartaoSalvoMock.getCodigoEmissor(), cartaoSalvoMock.getCodigoBandeira())).thenReturn(true);
        Mockito.when( repository.save(cartaoMock) ).thenReturn(cartaoSalvoMock);

        CartaoResponseDTO cartaoResponseSalvoMock = cartaoResponse;

        Mockito.when(modelMapper.map(cartaoSalvoMock, CartaoResponseDTO.class)).thenReturn(cartaoResponseSalvoMock);

        //Execução
        CartaoResponseDTO cartaoSalvo = service.update(pan,cartaoRequestMock);

        //verificação
        assertThat(cartaoSalvo.getId()).isNotNull();
        assertThat(cartaoSalvo.getPan()).isNotNull();
    }

    @Test
    public void findByIdTest(){
        String id = "5feb955b37b9fb7770ec3158";
        CartaoRequestDTO cartaoRequestMock = cartaoRequestDTO;
        Cartao cartaoSalvoMock  = cartaoSalvo;

        Mockito.when( repository.findById(id) )
                .thenReturn(Optional.ofNullable(cartaoSalvoMock));
        CartaoResponseDTO cartaoResponseSalvoMock = cartaoResponse;
        Mockito.when(modelMapper.map(cartaoSalvoMock, CartaoResponseDTO.class)).thenReturn(cartaoResponseSalvoMock);

        //Execução
        CartaoResponseDTO cartaoSalvo = service.findById(id);

        //verificação
        assertThat(cartaoSalvo.getId()).isEqualTo(id);
        assertThat(cartaoSalvo.getPan()).isEqualTo("1234567890123456789");
        assertThat(cartaoSalvo.getCodigoEmissor()).isEqualTo(1);
        assertThat(cartaoSalvo.getTipoCartao()).isEqualTo(TipoCartao.CREDITO);
    }

    @Test
    public void notFoundByIdTest(){

        Mockito.when(repository.findById("5feb955b37b9fb7770ec3157") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3157");
        });

        String expectedMessage = MensagensRetorno.CARTAO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findByPanTest(){
        String pan = "1234567890123456789";
        CartaoRequestDTO cartaoRequestMock = cartaoRequestDTO;
        Cartao cartaoSalvoMock  = cartaoSalvo;

        Mockito.when( repository.findByPan(pan) ).thenReturn(cartaoSalvoMock);
        CartaoResponseDTO cartaoResponseSalvoMock = cartaoResponse;
        Mockito.when(modelMapper.map(cartaoSalvoMock, CartaoResponseDTO.class)).thenReturn(cartaoResponseSalvoMock);

        //Execução
        CartaoResponseDTO cartaoResponse = service.findByPan(pan);

        //verificação
        assertThat(cartaoResponse.getPan()).isEqualTo("1234567890123456789");
    }

    @Test
    public void notFoundByPanTest(){

        Mockito.when(repository.findByPan("1") ).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByPan("1");
        });

        String expectedMessage = MensagensRetorno.CARTAO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void findAllTest(){

        CartaoRequestCriteriaDTO cartaoRequestCriteriaMock = cartaoRequestCriteriaDTO;

        Cartao cartaoMock  = cartao;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example example = Example.of(cartao, matcher);

        List<Cartao> listaBuscaCartao = new ArrayList<>();
        listaBuscaCartao.add(cartaoMock);

        List<CartaoResponseDTO> listaBuscaCartaoResponse = new ArrayList<>();
        listaBuscaCartaoResponse.add(cartaoResponse);

        Mockito.when( modelMapper.map(cartaoRequestCriteriaMock, Cartao.class)).thenReturn(cartaoMock);
        Mockito.when(repository.findAll(example)).thenReturn(listaBuscaCartao);
        Mockito.when(modelMapper.map(listaBuscaCartao, new TypeToken<List<CartaoResponseDTO>>() {}.getType())).thenReturn(listaBuscaCartaoResponse);

        //Execução
        List<CartaoResponseDTO> cartaoResponseList = service.findAll(cartaoRequestCriteriaMock);

        //verificação
        assertThat(cartaoResponseList.size()).isEqualTo(1);

    }

}
