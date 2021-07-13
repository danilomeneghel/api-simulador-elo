package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import br.com.elo.domain.Tipo;
import br.com.elo.domain.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemISO8583RequestCriteriaDTO implements Serializable {

    private String id;

    private Long mensagemSequence;

    private Integer codigoMensagem;

    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    private TipoMensagem tipoMensagem;

    private Status status;

    private Long protocoloSequence;

}
