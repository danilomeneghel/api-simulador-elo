package br.com.elo.repository;


import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.model.ValidationRules;
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
@DisplayName("Deve testar os métodos da camada repository da entidade ValidationRules")
public class ValidationRuleRepositoryTest {

    @Autowired
    ValidationRulesRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar uma bandeira que contenha dados válidos")
    public void deveSalvarValidationRuleComDadosValidos() {
        //cenário
        ValidationRules validationRules = DadosMockUtil.criaNovaValidationRulesRepository();

        //execução
        ValidationRules validationRulesDb = repository.save(validationRules);

        //verificação
        assertThat(validationRulesDb.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar uma validationRule na base com o id informado")
    public void deveVerificarSimuladorCadastrado() {
        //cenário
        ValidationRules validationRules = DadosMockUtil.criaNovaValidationRulesRepository();

        //execução
        ValidationRules validationRulesDb = repository.save(validationRules);
        String id = validationRulesDb.getId();
        ValidationRules validationRulesAux = repository.findById(id).get();

        //verificação
        Assertions.assertThat(validationRulesAux).isNotNull();
        Assertions.assertThat(validationRulesAux.getId()).isNotNull();
        Assertions.assertThat(validationRulesAux.getId()).isEqualTo(id);
    }


    @Test
    @DisplayName("Deve salvar uma bandeira com schema Version 1")
    public void deveSalvarBandeiraComSchemaVersion1() {
        //cenário
        ValidationRules validationRules = DadosMockUtil.criaNovaValidationRulesRepository();

        //execução
        ValidationRules validationRulesDb = repository.save(validationRules);

        //verificação
        assertThat(validationRulesDb.getSchemaVersion().equals(SchemaVersion.SCHEMA_VERSION_1));
    }

}
