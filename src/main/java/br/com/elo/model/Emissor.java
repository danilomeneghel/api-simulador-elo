package br.com.elo.model;

import br.com.elo.domain.Plataforma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emissor")
public class Emissor implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "emissor_sequence";
    @Id
    private String id;

    private Integer codigoBandeira;
    private Integer codigoEmissor;

    @Column(length = 90)
    private String nomeEmissor;

    @Column(length = 4)
    private Integer codigoProcessadora;

    @Enumerated(EnumType.STRING)
    private Plataforma plataforma;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Column(length = 90)
    private String schemaVersion;

}
