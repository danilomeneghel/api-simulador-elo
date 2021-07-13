package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.TipoPlataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoRequestDTO implements Serializable {

    @NotNull(message = "O código Bandeira não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer codigoBandeira;

    @NotNull(message = "O código Produto não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer codigoProduto;

    @NotNull(message = "Tipo de Plataforma inválida")
    private TipoPlataforma tipoPlataforma;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descrição deve ser 90 caracteres")
    private String descricao;

}
