package br.com.elo.model;

import br.com.elo.domain.Status;
import br.com.elo.domain.Tipo;
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
@Document(collection = "ambiente")
public class Ambiente {

    @Transient
    public static final String SEQUENCE_NAME = "ambiente_sequence";

    @Id
    private String id;

    @Column(unique = true)
    private Long ambienteSequence;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 90)
    private String descricao;

    @Column
    private String schemaVersion;

    @Column
    private List<PortaCliente> portasClientes;

    @Column
    private List<PortaServidor> portasServidores;

    @Column
    private Chaves chaves;

    @Column
    private Long nsu;

}
