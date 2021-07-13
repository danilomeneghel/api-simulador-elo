package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoDadosRequestDTO implements Serializable {

    private String conteudoCampo;
    private String descricao;
}
