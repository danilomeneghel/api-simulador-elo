package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.dto.request.CartaoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CartaoRequestDTO;
import br.com.elo.domain.dto.response.CartaoResponseDTO;
import br.com.elo.model.Cartao;
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
@DisplayName("Deve testar os métodos da camada repository da entidade Cartao")
public class CartaoRepositoryTest {

    @Autowired
    CartaoRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private CartaoRequestDTO cartaoRequestDTO = DadosMockUtil.criaNovoCartaoRequestDTO();
    private Cartao cartao = DadosMockUtil.criaNovoCartao();
    private Cartao cartaoRepository = DadosMockUtil.criaCartaoTestRepository();
    private Cartao cartaoSalvo = DadosMockUtil.cartaoSalvo();
    private CartaoResponseDTO cartaoResponse = DadosMockUtil.cartaoResponseDTO();
    private CartaoRequestCriteriaDTO cartaoRequestCriteriaDTO = DadosMockUtil.criaNovoCartaoRequestCriteriaDTO();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um cartao que contenha dados válidos")
    public void deveSalvarCartaoComDadosValidos() {
        //cenário
        Cartao cartao = cartaoRepository;

        //execução
        Cartao cartaoSalvo = repository.save(cartao);

        //verificação
        assertThat(cartaoSalvo.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um cartao na base com o pan informado")
    public void deveVerificarCartaoCadastrado() {
        //cenário
        String pan = "1234567890123456789";
        Cartao cartao = cartaoRepository;
        //execução
        repository.save(cartao);
        boolean exists = repository.findByPan(pan) == null ? false : true;

        //verificação
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um cartao cadastrado")
    public void deveAlterarCartaoCadastrado() {
        //cenário
        String pan = "1234567890123456789";
        Cartao cartao = cartaoRepository;
        //execução
        Cartao cartaoSalvo = repository.save(cartao);
        Cartao cartaoAlterado = repository.save(cartaoSalvo);

        //verificação
        assertThat(cartaoAlterado.getPan()).isEqualTo(pan);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir um cartao na base com o código informado")
    public void deveTestarCartaoNaoCadastrado() {
        //cenario
        String pan = "1234567890123456789";
        //execucao
        boolean exists = repository.findByPan(pan) == null ? false : true;

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true quando existir um cartao na base com o código informado")
    public void deveTestarCartaoCadastradoNaBase() {

        //cenário
        String pan = "1234567890123456789";
        Cartao cartao = cartaoRepository;
        //execução
        Cartao cartaoSalvo = repository.save(cartao);
        Cartao cartaoAlterado = repository.save(cartaoSalvo);

        //execucao
        boolean exists = repository.existsByPan(pan);

        //verificacao
        assertThat(exists).isTrue();
    }

}
