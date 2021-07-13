package br.com.elo.dto;


import br.com.elo.domain.TipoPlataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO implements Serializable {

    private String id;
    private Integer codigoBandeira;
    private Integer codigoProduto;
    private TipoPlataforma tipoPlataforma;
    private String descricao;
    private String dataCriacao;
    private String dataAlteracao;
}
