package br.com.elo.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FluxoTransacionalRequestCriteriaDTO implements Serializable {

    private Long fluxoTransacionalSequence;
    private String id;
    private Long mensagemSolicitacaoPernaUmSequence;
    private Long mensagemSolicitacaoPernaDoisSequence;
    private Long mensagemRespostaPernaTresSequence;
    private Long mensagemRespostaPernaQuatroSequence;
    private Integer bitVinculacaoMensagensPernasUmQuatro;
    private Integer bitVinculacaoMensagensPernasDoisTres;
    private String descricao;

}
