package br.com.elo.domain.dto.request;


import br.com.elo.domain.FillingMode;
import br.com.elo.domain.Status;
import br.com.elo.domain.ValidationRuleConditions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class ValidationBitsRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    @Test
    @DisplayName("Testa o bitnumber do validationBits")
    public void testBitNumber() {
        //bitnumber nulo
        ValidationBitsRequestDTO dto = new ValidationBitsRequestDTO();
        Set<ConstraintViolation<ValidationBitsRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitNumber")).count()).isEqualTo(1);

        //name valido
        dto.setBitNumber(2);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("bitNumber")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o value do validationBits")
    public void testValue() {
        //value nulo
        ValidationBitsRequestDTO dto = new ValidationBitsRequestDTO();
        Set<ConstraintViolation<ValidationBitsRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("value")).count()).isEqualTo(0);

        //value em vazio
        dto.setValue("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("value")).count()).isEqualTo(0);

        //value em branco
        dto.setValue("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("value")).count()).isEqualTo(0);

        //value preenchido
        dto.setValue("1");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("value")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o fillingMode da validationRules")
    public void testFillingMode() {
        //fillingMode nulo
        ValidationBitsRequestDTO dto = new ValidationBitsRequestDTO();
        Set<ConstraintViolation<ValidationBitsRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("fillingMode")).count()).isEqualTo(0);

        //fillingMode em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> FillingMode.valueOf(""));

        //fillingMode em branco
        assertThatIllegalArgumentException().isThrownBy(() -> FillingMode.valueOf("   "));

        //fillingMode invalido
        assertThatIllegalArgumentException().isThrownBy(() -> FillingMode.valueOf("ABCD"));

        //fillingMode valido
        dto.setFillingMode(FillingMode.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("fillingMode")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o validationRule da validationRules")
    public void testValidationRule() {
        //validationRule nulo
        ValidationBitsRequestDTO dto = new ValidationBitsRequestDTO();
        Set<ConstraintViolation<ValidationBitsRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("validationRule")).count()).isEqualTo(1);

        //validationRule em vazio
        assertThatIllegalArgumentException().isThrownBy(() -> ValidationRuleConditions.valueOf(""));

        //validationRule em branco
        assertThatIllegalArgumentException().isThrownBy(() -> ValidationRuleConditions.valueOf("   "));

        //validationRule invalido
        dto.setValidationRule(ValidationRuleConditions.get(13));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("validationRule")).count()).isEqualTo(1);

        //validationRule valido
        dto.setValidationRule(ValidationRuleConditions.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("validationRule")).count()).isEqualTo(0);
    }

}