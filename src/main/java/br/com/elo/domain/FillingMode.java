package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum FillingMode {

    @JsonProperty("VALORFIXO")
    VALORFIXO(1, "Valor Fixo"),
    @JsonProperty("SINTAXE")
    SINTAXE(2, "Sintaxe");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    FillingMode(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FillingMode get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
