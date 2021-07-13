package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoTamanho {

    @JsonProperty("FIXO")
    FIXO(1, "FIXO"),
    @JsonProperty("LLVAR")
    LLVAR(2, "LLVAR"),
    @JsonProperty("LLLVAR")
    LLLVAR(3, "LLLVAR");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoTamanho(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoTamanho get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
