package br.com.elo.domain.dto.request;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxoTransacionalRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    @Test
    @DisplayName("Testa a descrição do fluxo transacional")
    public void testDescricao() {

        FluxoTransacionalRequestDTO dto = new FluxoTransacionalRequestDTO();
        Set<ConstraintViolation<FluxoTransacionalRequestDTO>> violations = validator.validate(dto);

        //descricao nulo
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(1);

        //descricao em vazio
        dto.setDescricao("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(1);

        //descricao em branco
        dto.setDescricao("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(1);

        //descricao invalida
        dto.setDescricao("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(1);

        //descricao valida
        dto.setDescricao("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o mensagemSolicitacaoCodigo e mensagemRespostaCodigo do objeto FluxoTransacional")
    public void testCodigoMensagemSolicitacaoResposta() {

        FluxoTransacionalRequestDTO dto = new FluxoTransacionalRequestDTO();
        Set<ConstraintViolation<FluxoTransacionalRequestDTO>> violations = validator.validate(dto);

        //mensagemSolicitacaoCodigoPernaUmCodigo valido
        dto.setMensagemSolicitacaoPernaUmSequence(1L);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("mensagemSolicitacaoCodigo")).count()).isEqualTo(0);

        //mensagemSolicitacaoPernaDoisCodigo valido
        dto.setMensagemSolicitacaoPernaDoisSequence(1L);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("mensagemSolicitacaoCodigo")).count()).isEqualTo(0);


        //mensagemRespostaPernaTresCodigo valido
        dto.setMensagemRespostaPernaTresSequence(1L);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("mensagemRespostaCodigo")).count()).isEqualTo(0);


        //mensagemRespostaPernaQuatroCodigo valido
        dto.setMensagemRespostaPernaQuatroSequence(1L);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("mensagemRespostaCodigo")).count()).isEqualTo(0);

    }

//    @Test
//    @DisplayName("Testa o bitVinculacaoMensagens do objeto FluxoTransacional")
//    public void testBitVinculacaoMensagens() {
//
//        FluxoTransacionalRequestDTO dto = new FluxoTransacionalRequestDTO();
//        Set<ConstraintViolation<FluxoTransacionalRequestDTO>> violations = validator.validate(dto);
//
//
//        //bitVinculacaoMensagensUmQuatro invalido
//        dto.setBitVinculacaoMensagensUmQuatro(null);
//        violations = validator.validate(dto);
//        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitVinculacaoMensagens")).count()).isEqualTo(1);
//
//        //bitVinculacaoMensagensUmQuatro valido
//        dto.setBitVinculacaoMensagensUmQuatro(1);
//        violations = validator.validate(dto);
//        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitVinculacaoMensagens")).count()).isEqualTo(0);
//
//        //bitVinculacaoMensagensPernaDoisTres invalido
//        dto.setBitVinculacaoMensagensPernaDoisTres(null);
//        violations = validator.validate(dto);
//        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitVinculacaoMensagens")).count()).isEqualTo(1);
//
//        //bitVinculacaoMensagensPernaDoisTres valido
//        dto.setBitVinculacaoMensagensPernaDoisTres(1);
//        violations = validator.validate(dto);
//        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitVinculacaoMensagens")).count()).isEqualTo(0);
//
//    }


}