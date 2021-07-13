package br.com.elo.domain.dto.request;

import br.com.elo.domain.Plataforma;
import br.com.elo.domain.dto.request.EmissorRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EmissorRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {

        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa a descrição do Emissor")
    public void testDescricao() {
        //nomeEmissor nulo
        EmissorRequestDTO dto = new EmissorRequestDTO();
        Set<ConstraintViolation<EmissorRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nomeEmissor")).count()).isEqualTo(1);

        //nomeEmissor em vazio
        dto.setNomeEmissor("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nomeEmissor")).count()).isEqualTo(1);

        //nomeEmissor em branco
        dto.setNomeEmissor("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nomeEmissor")).count()).isEqualTo(1);

        //nomeEmissor invalida
        dto.setNomeEmissor("123456789012345678901234567890123456789012345678901234567890");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nomeEmissor")).count()).isEqualTo(0);

        //nomeEmissor valido
        dto.setNomeEmissor("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nomeEmissor")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o campo bandeiraCodigo do Emissor")
    public void testCodigoBandeira() {
        //bandeiraCodigo nulo
        EmissorRequestDTO dto = new EmissorRequestDTO();
        Set<ConstraintViolation<EmissorRequestDTO>> violations = validator.validate(dto);

        //bandeiraCodigo igual a zero
       dto.setCodigoBandeira(0);
       violations = validator.validate(dto);
       assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //bandeiraCodigo negativo
        dto.setCodigoBandeira(-1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //bandeiraCodigo valido
        dto.setCodigoBandeira(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o código da processadora")
    public void testCodigoProcessadora() {
        //codigoProcessadora nulo
        EmissorRequestDTO dto = new EmissorRequestDTO();
        Set<ConstraintViolation<EmissorRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProcessadora")).count()).isEqualTo(1);

        //codigoProcessadora igual a zero
        dto.setCodigoProcessadora(0);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProcessadora")).count()).isEqualTo(1);

        //codigoProcessadora negativo
        dto.setCodigoProcessadora(-1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProcessadora")).count()).isEqualTo(1);

        //codigoProcessadora valido
        dto.setCodigoProcessadora(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProcessadora")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o código do emissor")
    public void testCodigoEmissor() {
        //codigoEmissor nulo
        EmissorRequestDTO dto = new EmissorRequestDTO();
        Set<ConstraintViolation<EmissorRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoEmissor")).count()).isEqualTo(1);

        //codigoEmissor igual a zero
        dto.setCodigoEmissor(0);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoEmissor")).count()).isEqualTo(1);

        //codigoEmissor negativo
        dto.setCodigoEmissor(-1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoEmissor")).count()).isEqualTo(1);

        //codigoEmissor valido
        dto.setCodigoEmissor(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoEmissor")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa plataforma")
    public void testPlataforma() {

        EmissorRequestDTO dto = new EmissorRequestDTO();
        Set<ConstraintViolation<EmissorRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("plataforma")).count()).isEqualTo(1);

        //plataforma igual a zero
        dto.setPlataforma(null);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("plataforma")).count()).isEqualTo(1);

        //plataforma valido
        dto.setPlataforma(Plataforma.CREDITO);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("plataforma")).count()).isEqualTo(0);
    }



}
