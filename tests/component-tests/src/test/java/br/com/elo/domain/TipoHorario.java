package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum TipoHorario {

    @JsonProperty("LOCAL")
    LOCAL(1, "Local"),
    @JsonProperty("GMT")
    GMT(2, "GMT");

    //@JsonValue
    private final Integer id;
    private final String descricao;

    TipoHorario(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TipoHorario get(Integer id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

}
