package br.com.elo.domain.dto.request;


import br.com.elo.domain.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class ValidationRulesRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    @Test
    @DisplayName("Test o name do validationRules")
    public void testName() {
        //name nulo
        ValidationRulesRequestDTO dto = new ValidationRulesRequestDTO();
        Set<ConstraintViolation<ValidationRulesRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("name")).count()).isEqualTo(1);

        //name em vazio
        dto.setName("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("name")).count()).isEqualTo(1);

        //name em branco
        dto.setName("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("name")).count()).isEqualTo(1);

        //name valido
        dto.setName("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("name")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o status da validationRules")
    public void testStatus() {
        //Status nulo
        ValidationRulesRequestDTO dto = new ValidationRulesRequestDTO();
        Set<ConstraintViolation<ValidationRulesRequestDTO>> violations = validator.validate(dto);
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