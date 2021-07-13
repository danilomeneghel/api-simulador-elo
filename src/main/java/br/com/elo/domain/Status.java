package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum Status {

    @JsonProperty("ATIVO")
    ATIVO(1, "Ativo"),
    @JsonProperty("INATIVO")
    INATIVO(2, "Inativo");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    Status(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Status get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }


}
