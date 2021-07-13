package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.model.Emissor;
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
@DisplayName("Deve testar os métodos da camada repository da entidade Emissor")
public class EmissorRepositoryTest {

    @Autowired
    EmissorRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Emissor emissorRepository = DadosMockUtil.criaEmissorTestRepository();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um emissor que contenha dados válidos")
    public void deveSalvarEmissorComDadosValidos() {
        //cenário
        Emissor emissor = emissorRepository;

        //execução
        Emissor emissorSalvo = repository.save(emissor);

        //verificação
        assertThat(emissorSalvo.getId()).isNotNull();
        assertThat(emissorSalvo.getCodigoEmissor()).isEqualTo(emissorRepository.getCodigoEmissor());
        assertThat(emissorSalvo.getCodigoBandeira()).isEqualTo(emissorRepository.getCodigoBandeira());
        assertThat(emissorSalvo.getNomeEmissor()).isEqualTo(emissorRepository.getNomeEmissor());
        assertThat(emissorSalvo.getCodigoProcessadora()).isEqualTo(emissorRepository.getCodigoProcessadora());
        assertThat(emissorSalvo.getPlataforma()).isEqualTo(emissorRepository.getPlataforma());


    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um emissor na base com o codigo informado")
    public void deveVerificarEmissorCadastrado() {
        //cenário
        Integer codigoEmissor = 1;
        Emissor emissor = emissorRepository;
        emissor.setCodigoEmissor(codigoEmissor);
        //execução
        Emissor emissorSalvo = repository.save(emissor);
        //verificação
        assertThat(emissorSalvo.getCodigoEmissor()).isEqualTo(codigoEmissor);

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um emissor cadastrado")
    public void deveAlterarEmissorCadastrado() {
        //cenário
        Integer codigoEmissor = 2;
        Emissor emissor = emissorRepository;
        //execução
        Emissor emissorSalvo = repository.save(emissor);
        emissorSalvo.setCodigoEmissor(codigoEmissor);
        Emissor emissorAlterado = repository.save(emissorSalvo);
        //verificação
        assertThat(emissorAlterado.getCodigoEmissor()).isEqualTo(codigoEmissor);
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um emissor na base com o código de emissor e bandeira informado")
    public void deveTestarCodigoEmissorAndCodigoBandeira() {
        //cenario
        Integer codigoEmissor = 10;
        Integer codigoBandeira = 1;
        //execucao
        boolean exists = repository.existsByCodigoEmissorAndCodigoBandeira(codigoEmissor, codigoBandeira);
        //verificacao
        assertThat(exists).isFalse();
    }

}
