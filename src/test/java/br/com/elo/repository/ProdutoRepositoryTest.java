package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.dto.request.ProdutoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProdutoRequestDTO;
import br.com.elo.domain.dto.response.ProdutoResponseDTO;
import br.com.elo.model.Produto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Deve testar os métodos da camada repository da entidade Produto")
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private ProdutoRequestDTO produtoRequestDTO = DadosMockUtil.criaNovoProdutoRequestDTO();
    private Produto produtoUpdate = DadosMockUtil.produtoSalvo();
    private Produto produtoRepository = DadosMockUtil.criaProdutoTestRepository();
    private Produto produtoUpdateRepository = DadosMockUtil.produtoSalvo();
    private ProdutoResponseDTO produtoSalvoResponse = DadosMockUtil.produtoResponseDTO();
    private ProdutoRequestCriteriaDTO produtoCriteriaDTO = DadosMockUtil.dadosProdutoFindAllCriteria();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um produto que contenha dados válidos")
    public void deveSalvarProdutoComDadosValidos() {


        //cenário
        Produto produto = produtoRepository;

        //execução
        Produto produtoSalvo = repository.save(produto);

        //verificação
        assertThat(produtoSalvo.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um produto na base com o codigo informado")
    public void deveVerificarProdutoCadastrado() {

        //cenário
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;

        Produto produto = produtoRepository;
        //execução
        Produto produtoSalvo = repository.save(produto);
        boolean exists = repository.existsByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);

        //verificação
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um produto cadastrado")
    public void deveAlterarProdutoCadastrado() {

        //cenário
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;

        Produto produto = produtoRepository;

        //execução
        Produto produtoSalvo = repository.save(produto);
        Produto produtoAlterado = repository.save(produtoSalvo);

        //verificação
        assertThat(produtoSalvo.getCodigoBandeira()).isEqualTo(codigoBandeira);
        assertThat(produtoSalvo.getCodigoProduto()).isEqualTo(codigoProduto);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir um produto na base com o código informado")
    public void deveTestarProdutoNaoCadastrado() {
        //cenário
        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;

        //execucao
        boolean exists = repository.existsByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);

        //verificacao
        assertThat(exists).isFalse();
    }

}
