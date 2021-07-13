package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoPlataforma {

    @JsonProperty("CREDITO")
    CREDITO(1, "Crédito"),
    @JsonProperty("DEBITO")
    DEBITO(2, "Débito");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoPlataforma(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoPlataforma get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
