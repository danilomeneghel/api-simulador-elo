package br.com.elo.domain.dto.request;

import br.com.elo.domain.*;
import br.com.elo.model.Protocolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortaClienteRequestDTO implements Serializable {

    @NotNull(message = "Nome porta inválido ou nulo")
    private String nomePorta;

    @NotNull(message = "Nome Host inválido ou nulo")
    private String nomeHost;

    @NotNull(message = "Número porta inválido ou nulo")
    private Integer numeroPorta;
    private Double tempoTimeout;

    @NotNull(message = "Protocolo sequence inválido ou nulo")
    private Long protocoloSequence;

    @NotNull(message = "Cabecalho inválido ou nulo")
    private Cabecalho cabecalho;

    @NotNull(message = "Status inválido ou nulo")
    private Status status;

    private Integer numeroSockets;

    @NotNull(message = "Tipo MLI  inválido ou nulo")
    private TipoMLI tipoMLI;

    @NotNull(message = "Codificacao MLI  inválido ou nulo")
    private CodificacaoMLI codificacaoMLI;

    @NotNull(message = "Comprimento MLI  inválido ou nulo")
    private ComprimentoMLI comprimentoMLI;

    @NotNull(message = "Swapped MLI  inválido ou nulo")
    private Boolean swappedMLI;
}
