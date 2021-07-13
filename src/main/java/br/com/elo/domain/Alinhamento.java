package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum Alinhamento {

    @JsonProperty("ESQUERDA")
    ESQUERDA(1, "Esquerda"),
    @JsonProperty("DIREITA")
    DIREITA(2, "Direita");


    //@JsonValue
    private final Integer id;
    private final String descricao;

    Alinhamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Alinhamento get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
