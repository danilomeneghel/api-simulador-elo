package br.com.elo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.elo.domain.*;

import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemISO8583RequestDTO implements Serializable {

    private Integer codigoMensagem;
    private String descricao;
    private TipoMensagem tipoMensagem;
    private Status status;
    private List<BitMensagemRequestDTO> bitsMensagem;
    private Long protocoloSequence;
}
