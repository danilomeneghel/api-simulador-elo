package br.com.elo.domain.dto.request;


import br.com.elo.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MensagemISO8583RequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa a descrição da MensagemISO8583")
    public void testDescricao() {

        MensagemISO8583RequestDTO dto = new MensagemISO8583RequestDTO();
        Set<ConstraintViolation<MensagemISO8583RequestDTO>> violations = validator.validate(dto);

        //descricao nulo
        dto.setDescricao(null);
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

        //descricao valido
        dto.setDescricao("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o tipoMensagem da MensagemISO8583")
    public void testTipoMensagem() {

        MensagemISO8583RequestDTO dto = new MensagemISO8583RequestDTO();
        Set<ConstraintViolation<MensagemISO8583RequestDTO>> violations = validator.validate(dto);

        //tipo nulo
        dto.setTipoMensagem(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoMensagem")).count()).isEqualTo(1);

        //tipo em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> TipoProtocolo.valueOf(""));

        //tipo em branco
        assertThatIllegalArgumentException().isThrownBy(() -> TipoProtocolo.valueOf("   "));

        //tipo invalido
        dto.setTipoMensagem(TipoMensagem.get(4));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoMensagem")).count()).isEqualTo(1);

        //tipo valido
        dto.setTipoMensagem(TipoMensagem.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoMensagem")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o status da MensagemISO8583")
    public void testStatus() {

        MensagemISO8583RequestDTO dto = new MensagemISO8583RequestDTO();
        Set<ConstraintViolation<MensagemISO8583RequestDTO>> violations = validator.validate(dto);

        //Status nulo
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("status")).count()).isEqualTo(1);

        //Status em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> Status.valueOf(""));

        //Status em branco
        assertThatIllegalArgumentException().isThrownBy(() -> Status.valueOf("   "));

        //Status invalido
        dto.setStatus(Status.get(13));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("status")).count()).isEqualTo(1);

        //Status valido
        dto.setStatus(Status.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("status")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o protocoloSequence da MensagemISO8583")
    public void testProtocoloSequence() {

        MensagemISO8583RequestDTO dto = new MensagemISO8583RequestDTO();
        Set<ConstraintViolation<MensagemISO8583RequestDTO>> violations = validator.validate(dto);

        //protocoloCodigo nulo
        dto.setTipoMensagem(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("protocoloSequence")).count()).isEqualTo(1);

        //protocoloCodigo valido
        dto.setProtocoloSequence(1L);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("protocoloSequence")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o codigoMensagem da MensagemISO8583")
    public void testCodigoMensagem() {

        MensagemISO8583RequestDTO dto = new MensagemISO8583RequestDTO();
        Set<ConstraintViolation<MensagemISO8583RequestDTO>> violations = validator.validate(dto);

        //codigoMensagem nulo
        dto.setCodigoMensagem(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoMensagem")).count()).isEqualTo(1);

        //codigoMensagem zerado
        dto.setCodigoMensagem(0);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoMensagem")).count()).isEqualTo(1);

        //codigoMensagem mairo que 9999
        dto.setCodigoMensagem(10000);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoMensagem")).count()).isEqualTo(1);

        //codigoMensagem valido
        dto.setCodigoMensagem(1200);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoMensagem")).count()).isEqualTo(0);
    }


}