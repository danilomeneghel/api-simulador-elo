package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.ProtocoloRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import br.com.elo.domain.dto.response.ProtocoloResponseDTO;
import br.com.elo.model.LayoutBitsProtocolo;
import br.com.elo.model.Protocolo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Deve testar os métodos da camada repository da entidade Protocolo")
public class ProtocoloRepositoryTest {

    @Autowired
    ProtocoloRepository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private ProtocoloRequestDTO protocoloRequestDTO = DadosMockUtil.criaNovoProtocoloRequestDTO();
    private Protocolo protocolo = DadosMockUtil.criaNovoProtocolo();
    private Protocolo protocoloRepository = DadosMockUtil.criaProtocoloTestRepository();
    private Protocolo protocoloSalvo = DadosMockUtil.protocoloSalvo();
    private ProtocoloResponseDTO protocoloResponse = DadosMockUtil.protocoloResponseDTO();
    private ProtocoloRequestCriteriaDTO protocoloRequestCriteriaDTO = DadosMockUtil.criaNovoProtocoloRequestCriteriaDTO();


    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar um protocolo que contenha dados válidos")
    public void deveSalvarProtocoloComDadosValidos() {
        //cenário
        Protocolo protocolo = protocoloRepository;

        //execução
        Protocolo protocoloSalvo = repository.save(protocolo);

        //verificação
        assertThat(protocoloSalvo.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um protocolo na base com o codigo informado")
    public void deveVerificarProtocoloCadastrado() {
        //cenário
        Long codigo = 1L;
        Protocolo protocolo = protocoloRepository;
        //execução
        repository.save(protocolo);
        boolean exists = repository.findByProtocoloSequence(codigo) == null ? false : true;

        //verificação
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de um protocolo cadastrado")
    public void deveAlterarProtocoloCadastrado() {
        //cenário
        Long codigo = 1L;
        Protocolo protocolo = protocoloRepository;
        //execução
        Protocolo protocoloSalvo = repository.save(protocolo);
        Protocolo protocoloAlterado = repository.save(protocoloSalvo);

        //verificação
        assertThat(protocoloAlterado.getProtocoloSequence()).isEqualTo(codigo);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir um protocolo na base com o código informado")
    public void deveTestarProtocoloNaoCadastrado() {
        //cenario
        Long codigo = 1L;
        //execucao
        boolean exists = repository.findByProtocoloSequence(codigo) == null ? false : true;

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true quando existir um protocolo na base com o código informado")
    public void deveTestarProtocoloCadastradoNaBase() {

        //cenário
        Long codigo = 1L;
        Protocolo protocolo = protocoloRepository;
        //execução
        Protocolo protocoloSalvo = repository.save(protocolo);
        Protocolo protocoloAlterado = repository.save(protocoloSalvo);

        //execucao
        boolean exists = repository.existsByProtocoloSequence(codigo);

        //verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um protocolo na base com o código informado")
    public void deveTestarProtocoloNaoCadastradoNaBase() {
        //cenario
        Long codigo = 1L;
        //execucao
        boolean exists = repository.existsByProtocoloSequence(codigo);

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true quando existir um numero bit na lista de bits do protcolo na base com o código informado")
    public void deveTestarNumeroBitProtocoloCadastradoNaBase() {

        //cenário
        Long codigo = 1L;
        Protocolo protocolo = protocoloRepository;

        LayoutBitsProtocolo numeroBit = LayoutBitsProtocolo.builder().numeroDoBit(2).build();
        List<LayoutBitsProtocolo> listaCamposLayout = new ArrayList<>();
        listaCamposLayout.add(numeroBit);
        protocolo.setBitsProtocolo(listaCamposLayout);

        //execução
        Protocolo protocoloSalvo = repository.save(protocolo);

        //execucao
        boolean exists = repository.findNroBitByProtocoloSequence(codigo, 2) == null ? false : true;

        //verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um numero bit na lista de bits do protocolo na base com o código informado")
    public void deveTestarNumeroBitNaoCadastradoNaBase() {
        //cenario
        Long codigo = 1L;
        //execucao
        boolean exists = repository.findNroBitByProtocoloSequence(codigo, 2) == null ? false : true;

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve salvar um protocol com Schema Version 1")
    public void deveSalvarProtocoloComSchemaVersion1() {
        //cenário
        Protocolo protocolo = protocoloRepository;

        //execução
        Protocolo protocoloSalvo = repository.save(protocolo);

        //verificação
        assertThat(protocoloSalvo.getSchemaVersion().equals(SchemaVersion.SCHEMA_VERSION_1));
    }

}
