package br.com.elo.domain.dto.response;

import br.com.elo.domain.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;
}
