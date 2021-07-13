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
public class CredenciadorRequestCriteriaDTO implements Serializable {

    private String id;
    private Long codigo;
    private Long credenciadorCodigo;
    private String nome;
    private Integer status;
}
