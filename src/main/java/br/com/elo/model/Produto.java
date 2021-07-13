package br.com.elo.model;

import br.com.elo.domain.TipoPlataforma;
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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produto")
public class Produto {

    @Id
    private String id;

    @Column
    private Integer codigoBandeira;

    @Column
    private Integer codigoProduto;

    @Enumerated(EnumType.STRING)
    private TipoPlataforma tipoPlataforma;

    @Column(length = 90)
    private String descricao;

    @Column
    private String schemaVersion;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

}
