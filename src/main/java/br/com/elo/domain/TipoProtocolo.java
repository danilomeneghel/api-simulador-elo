package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoProtocolo {

    @JsonProperty("BANDEIRA")
    BANDEIRA(1, "Bandeira"),
    @JsonProperty("EMISSOR")
    EMISSOR(2, "Emissor"),
    @JsonProperty("CREDENCIADORA")
    CREDENCIADORA(3, "Credenciadora");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoProtocolo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoProtocolo get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
