package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum FormatacaoDadosCampo {

    @JsonProperty("DATAHORA")
    DATAHORA(1, "Data/Hora"),
    @JsonProperty("MONETARIO")
    MONETARIO(2, "Valor Monetário"),
    @JsonProperty("TRILHA2")
    TRILHA2(3, "Trilha 2"),
    @JsonProperty("TRILHA1")
    TRILHA1(4, "Trilha 1"),
    @JsonProperty("CODPAIS")
    CODPAIS(5, "Código País (ISO 3166)"),
    @JsonProperty("CODMOEDA")
    CODMOEDA(6, "Código de Moeda (ISO 4217)"),
    @JsonProperty("PORCENTAGEM")
    PORCENTAGEM(7, "Porcentagem"),
    @JsonProperty("SRVCODE")
    SRVCODE(8, "Service Code"),
    @JsonProperty("DADOSEMV")
    DADOSEMV(9, "Dados EMV (TLV)"),
    @JsonProperty("COMPROVANTE")
    COMPROVANTE(10, "Comprovante"),
    @JsonProperty("FORMATDATA003")
    FORMATDATA003(11, "Format Data 003 (POS Logistics)"),
    @JsonProperty("FORMATDATA004")
    FORMATDATA004(12, "Format Data 004 (POS Logistics)"),
    @JsonProperty("BIT47V40")
    BIT47V40(13, "Bit 47 V40"),
    @JsonProperty("BIT106")
    BIT106(14, "Bit 106 Tokenizador"),
    @JsonProperty("MCC")
    MCC(15, "MCC (Merchant Category Code)");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    FormatacaoDadosCampo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FormatacaoDadosCampo get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
