package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum EncodeMapaDeBits {

    @JsonProperty("ASCII")
    ASCII(1, "ASCII"),
    @JsonProperty("EBCDIC")
    EBCDIC(2, "EBCDIC"),
    @JsonProperty("BINARIO")
    BINARIO(3, "BINARIO");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    EncodeMapaDeBits(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EncodeMapaDeBits get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
