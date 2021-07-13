package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.TipoPlataforma;
import br.com.elo.domain.dto.request.ProdutoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProdutoRequestDTO;
import br.com.elo.domain.dto.response.ProdutoResponseDTO;
import br.com.elo.model.Produto;
import br.com.elo.repository.BandeiraRepository;
import br.com.elo.repository.ProdutoRepository;
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
public class ProdutoServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    ProdutoService service;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    private BandeiraRepository bandeiraRepository;

    @Mock
    private ProdutoRepository repository;

    private ProdutoRequestDTO produtoRequestDTO = DadosMockUtil.criaNovoProdutoRequestDTO();
    private ProdutoRequestDTO produtoUpdateInvalidoDTO = DadosMockUtil.criaNovoProdutoRequestDTO();
    private Produto produtoUpdate = DadosMockUtil.produtoSalvo();
    private Produto produto = DadosMockUtil.criaNovoProduto();
    private Produto produtoSalvo = DadosMockUtil.produtoSalvo();
    private ProdutoResponseDTO produtoSalvoResponse = DadosMockUtil.produtoResponseDTO();
    private ProdutoRequestCriteriaDTO produtoCriteriaDTO = DadosMockUtil.criaNovoProdutoRequestCriteriaDTO();

    @Test
    public void saveTest(){

        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoMock = produto;

        Mockito.when(bandeiraRepository.existsByCodigoBandeira(1)).thenReturn(true);
        Mockito.when(repository.existsByCodigoBandeiraAndCodigoProduto(produtoRequestMock.getCodigoBandeira(),produtoRequestMock.getCodigoProduto()) ).thenReturn(false);
        Mockito.when(modelMapper.map(produtoRequestMock, Produto.class)).thenReturn(produtoMock);


        Produto produtoSalvoMock  = produtoSalvo;

        Mockito.when( repository.save(produtoMock) ).thenReturn(produtoSalvoMock);
        ProdutoResponseDTO produtoResponseSalvoMock = produtoSalvoResponse;
        Mockito.when(modelMapper.map(produtoSalvoMock, ProdutoResponseDTO.class)).thenReturn(produtoResponseSalvoMock);

        //Execução
        ProdutoResponseDTO produtoSalvo = service.save(produtoRequestMock);

        //verificação
        assertThat(produtoSalvo.getId()).isNotNull();
        assertThat(produtoSalvo.getCodigoBandeira()).isEqualTo(1);
        assertThat(produtoSalvo.getCodigoProduto()).isEqualTo(1);
        assertThat(produtoSalvo.getTipoPlataforma()).isEqualTo(TipoPlataforma.CREDITO);
        assertThat(produtoSalvo.getDescricao()).isEqualTo("Cadastro de Produto");
    }

    @Test
    public void findByIdTest(){
        String id = "5feb955b37b9fb7770ec3156";
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoSalvoMock  = produtoSalvo;

        Mockito.when( repository.findById(id) )
                .thenReturn(Optional.ofNullable(produtoSalvoMock));
        ProdutoResponseDTO produtoResponseSalvoMock = produtoSalvoResponse;
        Mockito.when(modelMapper.map(produtoSalvoMock, ProdutoResponseDTO.class)).thenReturn(produtoResponseSalvoMock);

        //Execução
        ProdutoResponseDTO produtoSalvo = service.findById(id);

        //verificação
        assertThat(produtoSalvo.getId()).isEqualTo(id);
        assertThat(produtoSalvo.getCodigoBandeira()).isEqualTo(1);
        assertThat(produtoSalvo.getCodigoProduto()).isEqualTo(1);
        assertThat(produtoSalvo.getTipoPlataforma()).isEqualTo(TipoPlataforma.CREDITO);
        assertThat(produtoSalvo.getDescricao()).isEqualTo("Cadastro de Produto");
    }

    @Test
    public void findByCodigoBandeiraAndCodigoProdutoTest(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        String id = "5feb955b37b9fb7770ec3156";
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoSalvoMock  = produtoSalvo;

        Mockito.when( repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto)).thenReturn(produtoSalvoMock);
        ProdutoResponseDTO produtoResponseSalvoMock = produtoSalvoResponse;
        Mockito.when(modelMapper.map(produtoSalvoMock, ProdutoResponseDTO.class)).thenReturn(produtoResponseSalvoMock);

        //Execução
        ProdutoResponseDTO produtoSalvo = service.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);

        //verificação
        assertThat(produtoSalvo.getId()).isEqualTo(id);
        assertThat(produtoSalvo.getCodigoBandeira()).isEqualTo(1);
        assertThat(produtoSalvo.getCodigoProduto()).isEqualTo(1);
        assertThat(produtoSalvo.getTipoPlataforma()).isEqualTo(TipoPlataforma.CREDITO);
        assertThat(produtoSalvo.getDescricao()).isEqualTo("Cadastro de Produto");
    }

    @Test
    public void findProdutoCodigoNotFound(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Mockito.when(repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto)).thenReturn(null);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);
        });
        String expectedMessage = MensagensRetorno.PRODUTO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void updateTest(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoMock = produto;

        Mockito.when(bandeiraRepository.existsByCodigoBandeira(1)).thenReturn(true);
        Mockito.when(modelMapper.map(produtoRequestMock, Produto.class)).thenReturn(produtoMock);
        Produto produtoDbMock  = produtoSalvo;
        Mockito.when( repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira,codigoProduto) ).thenReturn(produtoDbMock);
        Mockito.when(repository.save(produtoMock)).thenReturn(produtoDbMock);

        ProdutoResponseDTO produtoResponseMock = produtoSalvoResponse;
        Mockito.when(modelMapper.map(produtoDbMock, ProdutoResponseDTO.class)).thenReturn(produtoSalvoResponse);

        //Execução
        ProdutoResponseDTO produtoResponse = service.update(codigoBandeira,codigoProduto,produtoRequestMock);

        //verificação
        assertThat(produtoSalvo.getId()).isNotNull();
        assertThat(produtoSalvo.getCodigoBandeira()).isEqualTo(1);
        assertThat(produtoSalvo.getCodigoProduto()).isEqualTo(1);
        assertThat(produtoSalvo.getTipoPlataforma()).isEqualTo(TipoPlataforma.CREDITO);
        assertThat(produtoSalvo.getDescricao()).isEqualTo("Cadastro de Produto");
    }

    @Test
    public void updateCodigoBandeiraInvalidoTest(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoMock = produto;


        Mockito.when(modelMapper.map(produtoRequestMock, Produto.class)).thenReturn(produtoMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(codigoBandeira)).thenReturn(false);
        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoBandeira,codigoProduto,produtoRequestMock);
        });

        String expectedMessage = MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateCodigoBandeiraAndCodigoProdutoInvalidoDuplicadoTest(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        produtoRequestMock.setCodigoProduto(2);
        Produto produtoMock = produto;


        Mockito.when(modelMapper.map(produtoRequestMock, Produto.class)).thenReturn(produtoMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(codigoBandeira)).thenReturn(true);
        Mockito.when(repository.existsByCodigoBandeiraAndCodigoProduto(produtoRequestMock.getCodigoBandeira(),produtoRequestMock.getCodigoProduto())).thenReturn(true);
        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoBandeira,codigoProduto,produtoRequestMock);
        });

        String expectedMessage = MensagensRetorno.PRODUTO_COD_BAD_EXISTE.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateCodigoBandeiraAndCodigoProdutoNaoExistenteTest(){
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        ProdutoRequestDTO produtoRequestMock = produtoRequestDTO;
        Produto produtoMock = produto;


        Mockito.when(modelMapper.map(produtoRequestMock, Produto.class)).thenReturn(produtoMock);
        Mockito.when(bandeiraRepository.existsByCodigoBandeira(codigoBandeira)).thenReturn(true);
        Mockito.when( repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira,codigoProduto) ).thenReturn(null);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoBandeira,codigoProduto,produtoRequestMock);
        });

        String expectedMessage = MensagensRetorno.PRODUTO_NAO_LOCALIZADO.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void findAllTest(){

        Long codigoSequence = 1L;
        ProdutoRequestCriteriaDTO produtoRequestCriteriaMock = produtoCriteriaDTO;

        Produto produtoMock  = produto;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(produto, matcher);

        List<Produto> listaBuscaProduto = new ArrayList<>();
        listaBuscaProduto.add(produtoMock);

        List<ProdutoResponseDTO> listaBuscaProdutoResponse = new ArrayList<>();
        listaBuscaProdutoResponse.add(produtoSalvoResponse);

        Mockito.when( modelMapper.map(produtoRequestCriteriaMock, Produto.class)).thenReturn(produtoMock);
        Mockito.when(repository.findAll(example)).thenReturn(listaBuscaProduto);
        Mockito.when(modelMapper.map(listaBuscaProduto, new TypeToken<List<ProdutoResponseDTO>>() {}.getType())).thenReturn(listaBuscaProdutoResponse);

        //Execução
        List<ProdutoResponseDTO> produtoResponseList = service.findAll(produtoRequestCriteriaMock);

        //verificação
        assertThat(produtoResponseList.size()).isEqualTo(1);

    }

}
