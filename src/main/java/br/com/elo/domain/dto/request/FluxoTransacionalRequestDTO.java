package br.com.elo.domain.dto.request;

import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
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

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 90, message = "Tamanho máximo da descriação deve ser 90 caracteres")
    private String descricao;

}
