package br.com.elo.model;

import br.com.elo.domain.Status;
import br.com.elo.domain.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mensagem_iso8583")
public class MensagemISO8583 {

    @Transient
    public static final String SEQUENCE_NAME = "mensagem_sequence";

    @Id
    private String id;

    @Column
    private Long mensagemSequence;

    @Column
    private Integer codigoMensagem;

    @Column(length = 90)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoMensagem tipoMensagem;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Long protocoloSequence;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    List<BitMensagem> bitsMensagem;

    @Column
    private String schemaVersion;
}
