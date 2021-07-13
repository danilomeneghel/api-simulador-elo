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
public class MensagemISO8583RequestCriteriaDTO implements Serializable {

    private String id;
    private Long codigo;
    private Integer codigoMensagem;
    private String descricao;
    private Integer tipoMensagem;
    private Integer status;
}
