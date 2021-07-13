package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import br.com.elo.domain.dto.request.CredenciadorRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CredenciadorRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa o nome do credenciador")
    public void testNome() {
         //nome nulo
        CredenciadorRequestDTO dto = new CredenciadorRequestDTO();
        Set<ConstraintViolation<CredenciadorRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nome")).count()).isEqualTo(1);

        //nome vazio
        dto.setNome("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nome")).count()).isEqualTo(1);

        //nome em branco
        dto.setNome(" ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nome")).count()).isEqualTo(1);

        //nome invalido
        dto.setNome("123456789012345678901234567890123456789012345678901234567890");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nome")).count()).isEqualTo(0);

        //nome valido
        dto.setNome("elo");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("nome")).count()).isEqualTo(0);

    }

    @Test
    @DisplayName("Testa o status do Credenciador")
    public void testStatus() {

        CredenciadorRequestDTO dto = new CredenciadorRequestDTO();
        Set<ConstraintViolation<CredenciadorRequestDTO>> violations = validator.validate(dto);
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
}