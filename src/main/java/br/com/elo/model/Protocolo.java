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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "protocolo")
public class Protocolo {

    @Transient
    public static final String SEQUENCE_NAME = "protocolo_sequence";

    @Id
    private String id;

    @Column
    private Long protocoloSequence;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAlteracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private TipoProtocolo tipo;

    @Column(length = 90)
    private String descricao;

    @Column(length = 30)
    private String versao;

    @Enumerated(EnumType.STRING)
    private EncodeCodigoMensagem encodeCodigoMensagem;

    @Enumerated(EnumType.STRING)
    private EncodeMapaDeBits encodeMapaDeBits;

    @Enumerated(EnumType.STRING)
    private EncodeBCDLLVARLLLVAR encodeBCDLLVARLLLVAR;

    List<LayoutBitsProtocolo> bitsProtocolo;

    List<MensagemPadrao> mensagensPadrao;

    @Column
    private String schemaVersion;
}
