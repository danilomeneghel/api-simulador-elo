package br.com.elo.model;

import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayoutBitsProtocolo {

    @Column
    private Integer numeroDoBit;

    @Column(length = 80)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoTamanho tipoTamCampo;

    @Enumerated(EnumType.STRING)
    private EncodeTamanho encodeTamCampo;

    @Enumerated(EnumType.STRING)
    private EncodeDadosCampo encodeDadosCampo;

    // Se tipoTamCampo 'F'  -> Nro de bytes do campo, Se tipoTamCampo 'V' -> Nro de bytes do tamanho
    @Column
    private Integer tam;


    @Column
    private Integer tamMinimo;

    //Somente usado para campos com tamanho variável (LLVAR OU LLLVAR)
    @Column
    private Integer tamMaximo;

    @Enumerated(EnumType.STRING)
    private Alinhamento alinhamento;

    @Column(length = 1)
    private String caracterPreenchimento;

    //Opcional quando utilizado o encodeDadosCampo DadosBinarios
    @Column
    private Boolean expandirDadosBinarios;

    //Obrigatorio quando utilizado o encodeDadosCampo NumeroBCD e tipo tamanho LLVAR/LLLVAR
    @Enumerated(EnumType.STRING)
    private BCDFillerPosition bcdFillerNibblePosition;

    //Obrigatorio quando utilizado o encodeDadosCampo NumeroBCD e tipo tamanho LLVAR/LLLVAR
    @Column(length = 1)
    private String bcdFillerNibbleValue;

    @Column
    private FormatacaoDadosCampo formatoDadosCampo;

    @Column(length = 30)
    private String formatacaoDataHora;

    //Somente utilizado para as formatacoes de dados CODPAIS,CODMOEDA,SRVCODE e MCC
    @Column
    List<DescricaoDados> descricaoDadosCampo;

}
