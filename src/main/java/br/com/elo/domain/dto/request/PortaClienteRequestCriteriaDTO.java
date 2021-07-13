package br.com.elo.domain.dto.request;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortaClienteRequestCriteriaDTO implements Serializable {

    private String nomePorta;
    private String nomeHost;
    private Integer numeroPorta;
    private Double tempoTimeout;
    private Long protocoloSequence;
    private Cabecalho cabecalho;
    private Status status;
    private Integer numeroSockets;
    private TipoMLI tipoMLI;
    private CodificacaoMLI codificacaoMLI;
    private ComprimentoMLI comprimentoMLI;
    private Boolean swappedMLI;
}
