package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO implements Serializable {

    private Integer codigoBandeira;
    private Integer codigoProduto;
    private TipoPlataforma tipoPlataforma;
    private String descricao;
}
