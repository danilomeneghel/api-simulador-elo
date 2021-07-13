package br.com.elo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Transient;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "estabelecimento_comercial")
public class EstabelecimentoComercial {

    @Transient
    public static final String SEQUENCE_NAME = "estabelecimento_comercial_sequence";

    @Column
    private String nome;

    @Column
    private Integer codigoPais;

    @Column
    private Integer mcc;

    @Column(length = 14)
    private String cpfCnpj;
}