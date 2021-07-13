package br.com.elo.dto;

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
public class FluxoTransacionalResponseDTO implements Serializable {

    private String id;
    private Long fluxoTransacionalSequence;
    private String descricao;
    private Long mensagemSolicitacaoPernaUmSequence;
    private Long mensagemSolicitacaoPernaDoisSequence;
    private Long mensagemRespostaPernaTresSequence;
    private Long mensagemRespostaPernaQuatroSequence;
    private Integer bitVinculacaoMensagensPernasUmQuatro;
    private Integer bitVinculacaoMensagensPernasDoisTres;
    private String dataCriacao;
    private String dataAlteracao;
}
