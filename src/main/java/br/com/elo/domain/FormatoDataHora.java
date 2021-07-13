package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum FormatoDataHora {

    @JsonProperty("DDMM")
    DDMM(1, "DDMM"),
    @JsonProperty("MMDD")
    MMDD(2, "MMDD"),
    @JsonProperty("MMAA")
    MMAA(3, "MMAA"),
    @JsonProperty("AAMM")
    AAMM(4, "AAMM"),
    @JsonProperty("DDMMAA")
    DDMMAA(5, "DDMMAA"),
    @JsonProperty("AAMMDD")
    AAMMDD(6, "AAMMDD"),
    @JsonProperty("DDMMAAAA")
    DDMMAAAA(7, "DDMMAAAA"),
    @JsonProperty("AAAAMMDD")
    AAAAMMDD(8, "AAAAMMDD"),
    @JsonProperty("MMAAAA")
    MMAAAA(9, "MMAAAA"),
    @JsonProperty("AAAAMM")
    AAAAMM(10, "AAAAMM"),
    @JsonProperty("hhmm")
    hhmm(11, "hhmm"),
    @JsonProperty("hhmmss")
    hhmmss(12, "hhmmss"),
    @JsonProperty("DDMMhhmmss")
    DDMMhhmmss(13, "DDMMhhmmss"),
    @JsonProperty("MMDDhhmmss")
    MMDDhhmmss(14, "MMDDhhmmss"),
    @JsonProperty("MMAAhhmmss")
    MMAAhhmmss(15, "MMAAhhmmss"),
    @JsonProperty("AAMMhhmmss")
    AAMMhhmmss(16, "AAMMhhmmss"),
    @JsonProperty("DDMMAAhhmmss")
    DDMMAAhhmmss(17, "DDMMAAhhmmss"),
    @JsonProperty("AAMMDDhhmmss")
    AAMMDDhhmmss(18, "AAMMDDhhmmss"),
    @JsonProperty("DDMMAAAAhhmmss")
    DDMMAAAAhhmmss(19, "DDMMAAAAhhmmss"),
    @JsonProperty("AAAAMMDDhhmmss")
    AAAAMMDDhhmmss(20, "AAAAMMDDhhmmss"),
    @JsonProperty("MMAAAAhhmmss")
    MMAAAAhhmmss(21, "MMAAAAhhmmss"),
    @JsonProperty("AAAAMMhhmmss")
    AAAAMMhhmmss(22, "AAAAMMhhmmss");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    FormatoDataHora(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FormatoDataHora get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
