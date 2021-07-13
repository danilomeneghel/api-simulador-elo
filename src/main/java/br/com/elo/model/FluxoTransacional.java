package br.com.elo.model;

import br.com.elo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fluxo_transacional")
public class FluxoTransacional {

    @Transient
    public static final String SEQUENCE_NAME = "fluxo_transacional_sequence";

    @Id
    private String id;

    @Column
    private Long fluxoTransacionalSequence;

    @Column
    private Long mensagemSolicitacaoPernaUmSequence;

    @Column
    private Long mensagemSolicitacaoPernaDoisSequence;

    @Column
    private Long mensagemRespostaPernaTresSequence;

    @Column
    private Long mensagemRespostaPernaQuatroSequence;

    @Column
    private Integer bitVinculacaoMensagensPernasUmQuatro;

    @Column
    private Integer bitVinculacaoMensagensPernasDoisTres;


    @Column(length = 90)
    private String descricao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Column
    private String schemaVersion;
}
