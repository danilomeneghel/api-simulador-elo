package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum Plataforma {

    @JsonProperty("CREDITO")
    CREDITO(1, "Crédito"),
    @JsonProperty("DEBITO")
    DEBITO(2, "Débito"),
    @JsonProperty("MULTIPLOS")
    MULTIPLOS(3, "Multiplos");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    Plataforma(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Plataforma get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
