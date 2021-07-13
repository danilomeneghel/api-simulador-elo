package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.model.Credenciador;

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
@DisplayName("Deve testar os métodos da camada repository da entidade Credenciador")
public class CredenciadorRepositoryTest {

    @Autowired
    CredenciadorRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Credenciador credenciadorRepository = DadosMockUtil.criaCredenciadorTestRepository();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um credenciador que contenha dados válidos")
    public void deveSalvarCredenciadorComDadosValidos() {
        //cenário
        Credenciador credenciador = credenciadorRepository;

        //execução
        Credenciador credenciadorSalvo = repository.save(credenciador);

        //verificação
        assertThat(credenciadorSalvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um credenciador na base com o codigo informado")
    public void deveVerificarCredenciadorCadastrado() {
        //cenário
        Integer codigoCredenciador = 1;
        Credenciador credenciador = credenciadorRepository;
        //execução
        Credenciador credenciadorSalvo = repository.save(credenciador);
        boolean exists = repository.existsByCredenciadorCodigo(codigoCredenciador);

        //verificação
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um credenciador cadastrado")
    public void deveAlterarCredenciadorCadastrado() {
        //cenário
        Integer codigoCredenciador = 1;
        Credenciador credenciador = credenciadorRepository;
        //execução
        Credenciador CredenciadorSalvo = repository.save(credenciador);
        Credenciador credenciadorAlterado = repository.save(CredenciadorSalvo);

        //verificação
        assertThat(credenciadorAlterado.getCredenciadorCodigo()).isEqualTo(codigoCredenciador);
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um credenciador na base com o código informado")
    public void deveTestarCredenciadorNaoCadastrado() {
        //cenario
        Integer codigoCredenciador = 1;
        //execucao
        boolean exists = repository.existsByCredenciadorCodigo(codigoCredenciador);

        //verificacao
        assertThat(exists).isFalse();
    }
}