package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum BCDFillerPosition {

    @JsonProperty("INICIO")
    INICIO(1, "Inicio"),
    @JsonProperty("FINAL")
    FINAL(2, "Final");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    BCDFillerPosition(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static BCDFillerPosition get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
