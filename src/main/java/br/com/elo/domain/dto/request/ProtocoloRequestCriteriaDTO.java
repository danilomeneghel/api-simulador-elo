package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.Status;
import br.com.elo.domain.TipoProtocolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProtocoloRequestCriteriaDTO implements Serializable {

    private String id;

    private Long protocoloSequence;

    private Status status;

    private TipoProtocolo tipo;

    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

    @Size(max = 30, message = "Tamanho máximo da versão deve ser 30 caracteres")
    private String versao;

}
