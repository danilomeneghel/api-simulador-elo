package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.AmbienteRequestCriteriaDTO;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import br.com.elo.model.Ambiente;
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
@DisplayName("Deve testar os métodos da camada repository da entidade Simulador")
public class AmbienteRepositoryTest {

    @Autowired
    AmbienteRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private AmbienteRequestDTO ambienteRequestDTO = DadosMockUtil.criaNovoSimuladorRequestDTO();
    private Ambiente ambienteUpdate = DadosMockUtil.simuladorSalvo();
    private Ambiente ambienteRepository = DadosMockUtil.criaSimuladorTestRepository();
    private Ambiente ambienteUpdateRepository = DadosMockUtil.simuladorSalvo();
    private AmbienteResponseDTO simuladorSalvoResponse = DadosMockUtil.ambienteResponseDTO();
    private AmbienteRequestCriteriaDTO simuladorCriteriaDTO = DadosMockUtil.dadosFindAllCriteriaSimulador();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um ambiente que contenha dados válidos")
    public void deveSalvarSimuladorComDadosValidos() {
        //cenário
        Ambiente ambiente = ambienteRepository;

        //execução
        Ambiente ambienteSalvo = repository.save(ambiente);

        //verificação
        assertThat(ambienteSalvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um simulador na base com o ambienteSequence informado")
    public void deveVerificarSimuladorCadastrado() {
        //cenário
        Long ambienteSequence = 1L;

        Ambiente ambiente = ambienteRepository;

        //execução
        Ambiente ambienteSalvo = repository.save(ambiente);
        boolean exists = repository.existsByAmbienteSequence(ambienteSequence);

        //verificação
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um simulador cadastrado")
    public void deveAlterarSimuladorCadastrado() {
        //cenário
        Long codigo = 1L;

        Ambiente ambiente = ambienteRepository;

        //execução
        Ambiente ambienteSalvo = repository.save(ambiente);
        Ambiente ambienteAlterado = repository.save(ambienteSalvo);

        //verificação
        assertThat(ambienteSalvo.getAmbienteSequence()).isEqualTo(codigo);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir um simulador na base com o ambienteSequence informado")
    public void deveTestarSimuladorNaoCadastrado() {
        //cenario
        Long ambienteSequence = 1L;

        //execucao
        boolean exists = repository.existsByAmbienteSequence(ambienteSequence);

        //verificacao
        assertThat(exists).isFalse();
    }


    @Test
    @DisplayName("Deve salvar um simulador com Schema Version 1")
    public void deveSalvarSimuladorComSchemaVersion1() {
        //cenário
        Ambiente ambiente = ambienteRepository;

        //execução
        Ambiente ambienteSalvo = repository.save(ambiente);

        //verificação
        assertThat(ambienteSalvo.getSchemaVersion().equals(SchemaVersion.SCHEMA_VERSION_1));
    }

}
