package br.com.elo.domain.dto.request;

import br.com.elo.domain.TipoPlataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoRequestCriteriaDTO {

    private String id;
    
    private Integer codigoBandeira;

    private Integer codigoProduto;

    private TipoPlataforma tipoPlataforma;

    @Size(max = 90, message = "Tamanho máximo da descrição deve ser 90 caracteres")
    private String descricao;

}
