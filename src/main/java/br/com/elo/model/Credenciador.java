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
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credenciador")
public class Credenciador {


    @Id
    private String id;

    @Column(unique = true)
    private Integer credenciadorCodigo;

    @Column(length = 30)
    private String nome;

    @Column
    private String schemaVersion;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private List<EstabelecimentoComercial> estabelecimentoComercial;
}