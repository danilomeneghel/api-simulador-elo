package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitMensagemResponseDTO implements Serializable {

    private Integer numeroDoBit;
    private FillingMode fillingMode;
    private CondicaoPresencaBit condicaoPresenca;
    private String conteudoBit;
}
