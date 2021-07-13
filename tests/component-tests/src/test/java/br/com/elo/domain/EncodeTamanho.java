package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum EncodeTamanho {

    @JsonProperty("ASCII")
    ASCII(1, "ASCII"),
    @JsonProperty("BCD")
    BCD(2, "BCD"),
    @JsonProperty("EBCDIC")
    EBCDIC(3, "EBCDIC"),
    @JsonProperty("BINARIO")
    BINARIO(4, "BINARIO");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    EncodeTamanho(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EncodeTamanho get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
