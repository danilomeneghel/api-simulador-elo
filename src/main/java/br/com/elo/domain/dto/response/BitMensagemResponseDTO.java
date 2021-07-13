package br.com.elo.domain.dto.response;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BitMensagemResponseDTO implements Serializable {

    private Integer numeroDoBit;
    private String descricao;
    private FillingMode fillingMode;
    private CondicaoPresencaBit condicaoPresenca;
    private String conteudoBit;
}
