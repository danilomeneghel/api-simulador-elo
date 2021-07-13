package br.com.elo.domain.dto.request;


import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ProtocoloRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa a descrição do Protocolo")
    public void testDescricao() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

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
    @DisplayName("Testa a versão do Protocolo")
    public void testVersao() {
        //versao nulo
        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("versao")).count()).isEqualTo(1);

        //versao em vazio
        dto.setVersao("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("versao")).count()).isEqualTo(1);

        //versao em branco
        dto.setVersao("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("versao")).count()).isEqualTo(1);

        //versao invalida
        dto.setVersao("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("versao")).count()).isEqualTo(1);

        //versao valido
        dto.setVersao("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("versao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o tipo do Protocolo")
    public void testTipo() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

        //tipo nulo
        dto.setTipo(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipo")).count()).isEqualTo(1);

        //tipo em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> TipoProtocolo.valueOf(""));

        //tipo em branco
        assertThatIllegalArgumentException().isThrownBy(() -> TipoProtocolo.valueOf("   "));

        //Status invalido
        dto.setTipo(TipoProtocolo.get(4));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("status")).count()).isEqualTo(1);

        //tipo valido
        dto.setTipo(TipoProtocolo.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipo")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o status do Protocolo")
    public void testStatus() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

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
    @DisplayName("Testa o EncodeCodigoMensagem")
    public void testEncodeCodigoMensagem() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

        //EncodeCodigoMensagem nulo
        dto.setEncodeCodigoMensagem(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeCodigoMensagem")).count()).isEqualTo(1);

        //EncodeCodigoMensagem em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeCodigoMensagem.valueOf(""));

        //EncodeCodigoMensagem em branco
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeCodigoMensagem.valueOf("   "));

        //EncodeCodigoMensagem valido
        dto.setEncodeCodigoMensagem(EncodeCodigoMensagem.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeCodigoMensagem")).count()).isEqualTo(0);

        //EncodeCodigoMensagem invalido
        dto.setEncodeCodigoMensagem(EncodeCodigoMensagem.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeCodigoMensagem")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o EncodeMapaDeBits")
    public void testEncodeMapaDeBits() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

        //EncodeMapaDeBits nulo
        dto.setTipo(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeMapaDeBits")).count()).isEqualTo(1);

        //EncodeMapaDeBits em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeMapaDeBits.valueOf(""));

        //EncodeMapaDeBits em branco
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeMapaDeBits.valueOf("   "));

        //EncodeMapaDeBits valido
        dto.setEncodeMapaDeBits(EncodeMapaDeBits.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeMapaDeBits")).count()).isEqualTo(0);

        //EncodeMapaDeBits invalido
        dto.setEncodeMapaDeBits(EncodeMapaDeBits.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeMapaDeBits")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o EncodeBCDLLVARLLLVAR")
    public void testEncodeBCDLLVARLLLVAR() {

        ProtocoloRequestDTO dto = new ProtocoloRequestDTO();
        Set<ConstraintViolation<ProtocoloRequestDTO>> violations = validator.validate(dto);

        //EncodeMapaDeBits nulo
        dto.setTipo(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeBCDLLVARLLLVAR")).count()).isEqualTo(1);

        //EncodeMapaDeBits em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeMapaDeBits.valueOf(""));

        //EncodeMapaDeBits em branco
        assertThatIllegalArgumentException().isThrownBy(() -> EncodeMapaDeBits.valueOf("   "));

        //EncodeMapaDeBits valido
        dto.setEncodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeBCDLLVARLLLVAR")).count()).isEqualTo(0);

        //EncodeMapaDeBits invalido
        dto.setEncodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("encodeBCDLLVARLLLVAR")).count()).isEqualTo(0);
    }

}