package br.com.elo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FluxoTransacionalRequestDTO implements Serializable {

    private Long mensagemSolicitacaoPernaUmSequence;

    private Long mensagemSolicitacaoPernaDoisSequence;

    private Long mensagemRespostaPernaTresSequence;

    private Long mensagemRespostaPernaQuatroSequence;

    private Integer bitVinculacaoMensagensPernasUmQuatro;

    private Integer bitVinculacaoMensagensPernasDoisTres;

    private String descricao;

}
