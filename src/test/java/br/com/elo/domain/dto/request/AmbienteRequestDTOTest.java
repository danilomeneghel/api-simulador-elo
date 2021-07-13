package br.com.elo.domain.dto.request;


import br.com.elo.domain.Status;
import br.com.elo.domain.Tipo;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class AmbienteRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    @Test
    @DisplayName("Testa a descrição do Simulador")
    public void testDescricao() {
        //descricao nulo
        AmbienteRequestDTO dto = new AmbienteRequestDTO();
        Set<ConstraintViolation<AmbienteRequestDTO>> violations = validator.validate(dto);
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
        dto.setDescricao("123456789012345678901234567890123456789012345678901234567890");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(0);

        //descricao valido
        dto.setDescricao("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o status do Simulador")
    public void testStatus() {
        //Status nulo
        AmbienteRequestDTO dto = new AmbienteRequestDTO();
        Set<ConstraintViolation<AmbienteRequestDTO>> violations = validator.validate(dto);
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