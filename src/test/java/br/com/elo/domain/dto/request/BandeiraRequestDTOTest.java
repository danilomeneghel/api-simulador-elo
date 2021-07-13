package br.com.elo.domain.dto.request;



import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class BandeiraRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    @Test
    @DisplayName("Testa a descrição da Bandeira")
    public void testDescricao() {

        BandeiraRequestDTO dto = new BandeiraRequestDTO();
        Set<ConstraintViolation<BandeiraRequestDTO>> violations = validator.validate(dto);

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
    @DisplayName("Testa o codigo da Bandeira do objeto Bandeira")
    public void testCodigoBandeira() {

        BandeiraRequestDTO dto = new BandeiraRequestDTO();
        Set<ConstraintViolation<BandeiraRequestDTO>> violations = validator.validate(dto);


        //codigo bandeira invalido
        dto.setCodigoBandeira(0);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //codigo bandeira invalido
        dto.setCodigoBandeira(1234);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //codigo bandeira invalido
        dto.setCodigoBandeira(null);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //codigo bandeira min valida
        dto.setCodigoBandeira(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(0);

        //codigo bandeira max valida
        dto.setCodigoBandeira(999);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(0);
    }


}