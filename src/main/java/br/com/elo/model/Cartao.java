package br.com.elo.model;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cartao")
public class Cartao {

    @Id
    private String id;

    @Column
    private Integer codigoBandeira;

    @Column
    private Integer codigoEmissor;

    @Column
    private TipoCartao tipoCartao;

    @Column
    private String nomeCartao;

    @Column(unique = true)
    private String pan;

    @Column
    private String pin;

    @Column
    private Integer cardSequenceNumber;

    @Column
    private String cve2;

    @Column
    private String dataExpiracao;

    @Column
    private TokenCartao tokenCartao;

    @Column
    private String applicationIdentifier;

    @Column
    private String cardAppInterchangeProfile;

    @Column
    private String issuerDiscrData;

    @Column
    private Integer cardAppTransactionCounter;

    @Column
    private String track1;

    @Column
    private String track2;

    @Column
    private String track2EquivalentData;

    @Column
    private CvnCartao cvnCartao;

    @Column
    private String cvr;

    @Column
    private String dkiKdi;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Column
    private String schemaVersion;
}
