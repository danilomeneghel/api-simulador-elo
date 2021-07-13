package br.com.elo.domain.dto.request;

import br.com.elo.domain.*;
import br.com.elo.model.Protocolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortaServidorRequestDTO implements Serializable {

    @NotNull(message = "Nome Porta inválido ou nulo")
    private String nomePorta;

    private String nomeHost;

    @NotNull(message = "Numero Porta inválido ou nulo")
    private Integer numeroPorta;

    private Double tempoTimeout;
    private Double tempoTimeoutSockets;

    @NotNull(message = "Protocolo Sequence inválido ou nulo")
    private Long protocoloSequence;

    @NotNull(message = "Cabecalho inválido ou nulo")
    private Cabecalho cabecalho;
    private Integer numeroSockets;

    @NotNull(message = "Status inválido ou nulo")
    private Status status;

    @NotNull(message = "Tipo MLI  inválido ou nulo")
    private TipoMLI tipoMLI;

    @NotNull(message = "Codificacao MLI  inválido ou nulo")
    private CodificacaoMLI codificacaoMLI;

    @NotNull(message = "Comprimento MLI  inválido ou nulo")
    private ComprimentoMLI comprimentoMLI;

    @NotNull(message = "Swapped MLI  inválido ou nulo")
    private Boolean swappedMLI;
}
