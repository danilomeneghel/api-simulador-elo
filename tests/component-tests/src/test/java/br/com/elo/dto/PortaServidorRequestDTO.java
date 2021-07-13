package br.com.elo.dto;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortaServidorRequestDTO implements Serializable {

    private String nomePorta;
    private String nomeHost;
    private Integer numeroPorta;
    private Double tempoTimeout;
    private Double tempoTimeoutSockets;
    private Long protocoloSequence;
    private Cabecalho cabecalho;
    private Integer numeroSockets;
    private TipoMLI tipoMLI;
    private CodificacaoMLI codificacaoMLI;
    private ComprimentoMLI comprimentoMLI;
    private Status status;
    private Boolean swappedMLI;
}
