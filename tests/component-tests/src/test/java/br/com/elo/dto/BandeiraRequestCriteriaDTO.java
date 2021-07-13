package br.com.elo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandeiraRequestCriteriaDTO implements Serializable {

    private String id;
    private Integer codigoBandeira;
    private String descricao;

}
