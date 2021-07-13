package br.com.elo.model;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bit_mensagem")
public class BitMensagem {

    @Column
    private Integer numeroDoBit;

    @Column(length = 80)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private FillingMode fillingMode;

    @Column
    private CondicaoPresencaBit condicaoPresenca;

    @Column
    private String conteudoBit;

}
