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

public class CartaoRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa o Tipo do Cartao")
    public void testTipoCartao() {

        CartaoRequestDTO dto = new CartaoRequestDTO();
        Set<ConstraintViolation<CartaoRequestDTO>> violations = validator.validate(dto);

        //tipoCartao nulo
        dto.setTipoCartao(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoCartao")).count()).isEqualTo(1);

        //tipoCartao vazio
        assertThatIllegalArgumentException().isThrownBy(() -> TipoCartao.valueOf(""));

        //tipoCartao em branco
        assertThatIllegalArgumentException().isThrownBy(() -> TipoCartao.valueOf("   "));

        //tipoCartao valido
        dto.setTipoCartao(TipoCartao.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoCartao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o Token do Cartao")
    public void testTokenCartao() {

        CartaoRequestDTO dto = new CartaoRequestDTO();
        Set<ConstraintViolation<CartaoRequestDTO>> violations = validator.validate(dto);

        //tokenCartao nulo
        dto.setTokenCartao(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tokenCartao")).count()).isEqualTo(1);

        //tokenCartao vazio
        assertThatIllegalArgumentException().isThrownBy(() -> TokenCartao.valueOf(""));

        //tokenCartao em branco
        assertThatIllegalArgumentException().isThrownBy(() -> TokenCartao.valueOf("   "));

        //tokenCartao valido
        dto.setTokenCartao(TokenCartao.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tokenCartao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o CVN do Cartao")
    public void testCvnCartao() {

        CartaoRequestDTO dto = new CartaoRequestDTO();
        Set<ConstraintViolation<CartaoRequestDTO>> violations = validator.validate(dto);

        //cvnCartao nulo
        dto.setCvnCartao(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("cvnCartao")).count()).isEqualTo(1);

        //cvnCartao vazio
        assertThatIllegalArgumentException().isThrownBy(() -> CvnCartao.valueOf(""));

        //cvnCartao em branco
        assertThatIllegalArgumentException().isThrownBy(() -> CvnCartao.valueOf("   "));

        //cvnCartao valido
        dto.setCvnCartao(CvnCartao.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("cvnCartao")).count()).isEqualTo(0);
    }
    @Test
    @DisplayName("Testa o PAN do Cartao")
    public void testPanCartao() {
        CartaoRequestDTO dto = new CartaoRequestDTO();
        Set<ConstraintViolation<CartaoRequestDTO>> violations = validator.validate(dto);

        //pan nulo
        dto.setPan(null);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("pan")).count()).isEqualTo(1);

        //pan vazio
        dto.setPan("");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("pan")).count()).isEqualTo(2);

        //pan em branco
        dto.setPan("   ");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("pan")).count()).isEqualTo(2);


        //pan invalido
        dto.setPan("123");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("pan")).count()).isEqualTo(1);

        //pan valido
        dto.setPan("12345678");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("pan")).count()).isEqualTo(0);


    }

}