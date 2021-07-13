package br.com.elo.dto;


import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayoutBitsProtocoloResponseDTO implements Serializable {

    private Integer numeroDoBit;
    private String descricao;
    private TipoTamanho tipoTamCampo;
    private EncodeTamanho encodeTamCampo;
    private EncodeDadosCampo encodeDadosCampo;
    private Integer tam;
    private Integer tamMinimo;
    private Integer tamMaximo;
    private Alinhamento alinhamento;
    private String caracterPreenchimento;
    private Boolean expandirDadosBinarios;
    private BCDFillerPosition bcdFillerNibblePosition;
    private String bcdFillerNibbleValue;
    private FormatacaoDadosCampo formatoDadosCampo;
    private String formatacaoDataHora;
    private List<DescricaoDadosResponseDTO> descricaoDadosCampo;
}
