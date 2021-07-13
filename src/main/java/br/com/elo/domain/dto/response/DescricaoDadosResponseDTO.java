package br.com.elo.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoDadosResponseDTO implements Serializable {

    private Integer conteudoCampo;
    private String descricao;

}
