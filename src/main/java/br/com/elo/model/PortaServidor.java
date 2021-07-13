package br.com.elo.model;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "porta_servidor")
public class PortaServidor {

    @Transient
    public static final String SEQUENCE_NAME = "porta_servidor_sequence";

    @Id
    private String id;

    @Column
    private Long codigo;

    @Column(length = 90)
    private String nomePorta;

    @Column(length = 34)
    private String nomeHost;

    @Column
    private Integer numeroPorta;

    @Column
    private Double tempoTimeout;

    @Column
    private Double tempoTimeoutSockets;

    private Long protocoloSequence;

    @Enumerated(EnumType.STRING)
    private Cabecalho cabecalho;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Integer numeroSockets;

    @Enumerated(EnumType.STRING)
    private TipoMLI tipoMLI;

    @Enumerated(EnumType.STRING)
    private CodificacaoMLI codificacaoMLI;

    @Enumerated(EnumType.STRING)
    private ComprimentoMLI comprimentoMLI;

    @Column
    private Boolean swappedMLI;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Column
    private String schemaVersion;

}
