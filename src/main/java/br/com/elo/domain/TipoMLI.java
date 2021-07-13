package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoMLI {

    @JsonProperty("BOTH")
    BOTH(1, "BOTH"),
    @JsonProperty("INCLUSIVE")
    INCLUSIVE(2, "INCLUSIVE"),
    @JsonProperty("EXCLUSIVE")
    EXCLUSIVE(3, "EXCLUSIVE");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoMLI(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoMLI get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
