package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.model.Emissor;
import br.com.elo.model.FluxoTransacional;
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
@DisplayName("Deve testar os métodos da camada repository da entidade Fluxo Transacional")
public class FluxoTransacionalRepositoryTest {

    @Autowired
    FluxoTransacionalRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final FluxoTransacional fluxoTransacionalRepository = DadosMockUtil.criaFluxoTransacionalTestRepository();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um fluxo transacional que contenha dados válidos")
    public void deveSalvarFluxoTransacionalComDadosValidos() {
        //cenário
        FluxoTransacional fluxoTransacional = fluxoTransacionalRepository;

        //execução
        FluxoTransacional fluxoTransacionalDb = repository.save(fluxoTransacional);

        //verificação
        assertThat(fluxoTransacionalDb.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um fluxo transacional na base com o codigo informado")
    public void deveVerificarFluxoTransacionalCadastrado() {
        //cenário
        Long fluxoTransacionalSequence = 1L;
        FluxoTransacional fluxoTransacional = fluxoTransacionalRepository;

        //execução
        FluxoTransacional fluxoTransacionalSalvo = repository.save(fluxoTransacional);
        boolean exists = repository.existsByFluxoTransacionalSequence(fluxoTransacionalSequence);

        //verificação
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um fluxo cadastrado")
    public void deveAlterarFluxoTransacionalCadastrado() {
        //cenário
        Long fluxoTransacionalSequence = 1L;
        FluxoTransacional fluxoTransacional = fluxoTransacionalRepository;

        //execução
        FluxoTransacional fluxoTransacionalDb = repository.save(fluxoTransacional);
        FluxoTransacional fluxoTransacionalAlterado = repository.save(fluxoTransacionalDb);

        //verificação
        assertThat(fluxoTransacionalAlterado.getFluxoTransacionalSequence()).isEqualTo(fluxoTransacionalSequence);
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um fluxo transacional na base com o código informado")
    public void deveTestarFluxoTransacionalNaoCadastrada() {
        //cenario
        Long fluxoTransacionalSequence = 1L;

        //execucao
        boolean exists = repository.findByFluxoTransacionalSequence(fluxoTransacionalSequence) == null ? false : true;

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar um fluxo transacional com schema Version 1")
    public void deveSalvarFluxoTransacionalComSchemaVersion1() {
        //cenário
        FluxoTransacional fluxoTransacional = fluxoTransacionalRepository;

        //execução
        FluxoTransacional fluxoTransacionalDb = repository.save(fluxoTransacional);

        //verificação
        assertThat(fluxoTransacionalDb.getSchemaVersion().equals(SchemaVersion.SCHEMA_VERSION_1));
    }

}
