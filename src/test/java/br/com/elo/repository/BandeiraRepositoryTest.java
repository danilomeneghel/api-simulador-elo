package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.BandeiraRequestCriteriaDTO;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.model.Bandeira;
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
@DisplayName("Deve testar os métodos da camada repository da entidade Bandeira")
public class BandeiraRepositoryTest {

    @Autowired
    BandeiraRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final BandeiraRequestDTO bandeiraRequestDTO = DadosMockUtil.criaNovaBandeiraRequestDTO();
    private final BandeiraRequestCriteriaDTO bandeiraRequestCriteriaDTO = DadosMockUtil.criaNovaBandeiraRequestCriteriaDTO();
    private final Bandeira bandeiraRepository = DadosMockUtil.criaBandeiraTestRepository();
    private final Bandeira bandeira = DadosMockUtil.criaNovaBandeira();
    private final Bandeira bandeiraDb = DadosMockUtil.bandeiraDB();
    private final BandeiraResponseDTO bandeiraResponse = DadosMockUtil.bandeiraResponseDTO();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar uma bandeira que contenha dados válidos")
    public void deveSalvarBandeiraComDadosValidos() {
        //cenário
        Bandeira bandeira = bandeiraRepository;

        //execução
        Bandeira bandeiraDb = repository.save(bandeira);

        //verificação
        assertThat(bandeiraDb.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir uma bandeira na base com o codigoBandeira informado")
    public void deveVerificarBandeiraCadastrada() {
        //cenário
        Long codigo = 1L;
        Bandeira bandeira = bandeiraRepository;
        //execução
        Bandeira bandeiraDb = repository.save(bandeira);
        boolean exists = repository.existsByCodigoBandeira(bandeiraDb.getCodigoBandeira());

        //verificação
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de uma bandeira cadastrada")
    public void deveAlterarBandeiraCadastrada() {
        //cenário
        Integer codigoBandeira = 7;
        Bandeira bandeira = bandeiraRepository;
        //execução
        Bandeira bandeiraDb = repository.save(bandeira);
        Bandeira bandeiraAlterada = repository.save(bandeiraDb);

        //verificação
        assertThat(bandeiraAlterada.getCodigoBandeira()).isEqualTo(codigoBandeira);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir uma bandeira na base com o códigoBandeira informado")
    public void deveTestarBandeiraNaoCadastrada() {
        //cenario
        Integer codigo = 60;
        //execucao
        boolean exists = repository.existsByCodigoBandeira(codigo);

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar uma bandeira com schema Version 1")
    public void deveSalvarBandeiraComSchemaVersion1() {
        //cenário
        Bandeira bandeira = bandeiraRepository;

        //execução
        Bandeira bandeiraDb = repository.save(bandeira);

        //verificação
        assertThat(bandeiraDb.getSchemaVersion().equals(SchemaVersion.SCHEMA_VERSION_1));
    }

}
