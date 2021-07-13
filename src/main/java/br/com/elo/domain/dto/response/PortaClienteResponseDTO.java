package br.com.elo.domain.dto.response;

import br.com.elo.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortaClienteResponseDTO implements Serializable {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;
}
