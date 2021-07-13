package br.com.elo.domain.dto.request;

import br.com.elo.domain.TipoPlataforma;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ProdutoRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Testa o codigoBandeira do Produto")
    public void testCodigoBandeira() {
        //codigoBandeira nulo
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        Set<ConstraintViolation<ProdutoRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //codigoBandeira invalido
        dto.setCodigoBandeira(0);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(1);

        //codigoBandeira valido
        dto.setCodigoBandeira(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoBandeira")).count()).isEqualTo(0);

    }

    @Test
    @DisplayName("Testa o codigoProduto do Produto")
    public void testCodigoEmissor() {
        //codigoProduto nulo
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        Set<ConstraintViolation<ProdutoRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProduto")).count()).isEqualTo(1);

        //codigoProduto invalido
        dto.setCodigoProduto(0);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProduto")).count()).isEqualTo(1);

        //codigoProduto valido
        dto.setCodigoProduto(1);
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("codigoProduto")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa a descrição do Produto")
    public void testDescricao() {
        //descricao nula
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        Set<ConstraintViolation<ProdutoRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(1);

        //descricao vazia
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

        //descricao valida
        dto.setDescricao("1234");
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("descricao")).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o Tipo Plataforma do Produto")
    public void testTipoPlataforma() {
        //tipo nulo
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        Set<ConstraintViolation<ProdutoRequestDTO>> violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoPlataforma")).count()).isEqualTo(1);

        //tipo vazio
        assertThatIllegalArgumentException().isThrownBy(() -> TipoPlataforma.valueOf(""));

        //tipo em branco
        assertThatIllegalArgumentException().isThrownBy(() -> TipoPlataforma.valueOf("   "));

        //tipo invalido
        dto.setTipoPlataforma(TipoPlataforma.get(8));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoPlataforma")).count()).isEqualTo(1);

        //tipo valido
        dto.setTipoPlataforma(TipoPlataforma.get(1));
        violations = validator.validate(dto);
        assertThat(violations.stream().filter(v -> v.getPropertyPath().toString().equals("tipoPlataforma")).count()).isEqualTo(0);
    }

}