package br.com.elo.repository;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.model.BitMensagem;
import br.com.elo.model.LayoutBitsProtocolo;
import br.com.elo.model.MensagemISO8583;
import br.com.elo.model.Protocolo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
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
@DisplayName("Deve testar os métodos da camada repository da entidade MensagemISO8583")
public class MensagemISO8583RepositoryTest {

    @Autowired
    MensagemISO8583Repository repository;

    @Autowired
    SequencesApiParamsRepository sequencesApiParamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final MensagemISO8583 mensagemISO8583Repository = DadosMockUtil.criaMensagemISO8583TestRepository();

    @BeforeEach
    public void setUp() {
        sequencesApiParamsRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar uma MensagemISO8583 que contenha dados válidos")
    public void deveSalvarMensagemISO8583ComDadosValidos() {
        //cenário
        MensagemISO8583 mensagem = mensagemISO8583Repository;

        //execução
        MensagemISO8583 mensagemISO8583Salva = repository.save(mensagem);

        //verificação
        assertThat(mensagemISO8583Salva.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir uma mensagem na base com o codigo informado")
    public void deveVerificarMensagemCadastrada() {
        //cenário
        Long codigo = 1L;
        MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository;
        //execução
        repository.save(mensagemISO8583);
        boolean exists = repository.findByMensagemSequence(codigo) != null;

        //verificação
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve alterar com sucesso os dados de uma mensagem cadastrada")
    public void deveAlterarMensagemCadastrada() {
        //cenário
        Long codigo = 1L;
        MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository;
        //execução
        MensagemISO8583 mensagemISO8583Salva = repository.save(mensagemISO8583);
        MensagemISO8583 mensagemISO8583Alterada = repository.save(mensagemISO8583Salva);

        //verificação
        assertThat(mensagemISO8583Alterada.getMensagemSequence()).isEqualTo(codigo);

    }

    @Test
    @DisplayName("Deve retornar false quando não existir um protocolo na base com o código informado")
    public void deveTestarMensagemNaoCadastrada() {
        //cenario
        Long codigo = 1L;
        //execucao
        boolean exists = repository.findByMensagemSequence(codigo) != null;

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true quando existir um numero bit na lista de bits do protcolo na base com o código informado")
    public void deveTestarNumeroBitProtocoloCadastradoNaBase() {

        //cenário
        Long codigo = 1L;
        MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository;

        BitMensagem numeroBit = BitMensagem.builder().numeroDoBit(2).build();
        List<BitMensagem> listaBitsMensagem = new ArrayList<>();
        listaBitsMensagem.add(numeroBit);
        mensagemISO8583.setBitsMensagem(listaBitsMensagem);

        //execução
        MensagemISO8583 mensagemISO8583Salvo = repository.save(mensagemISO8583);

        //execucao
        boolean exists = repository.findNroBitByMensagemSequence(codigo, 2) == null ? false : true;

        //verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando não existir um numero bit na lista de bits do protocolo na base com o código informado")
    public void deveTestarNumeroBitNaoCadastradoNaBase() {
        //cenario
        Long codigo = 1L;
        //execucao
        boolean exists = repository.findNroBitByMensagemSequence(codigo, 2) == null ? false : true;

        //verificacao
        assertThat(exists).isFalse();
    }


}
