package br.com.elo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat
public enum ShouldReturn {

    @JsonProperty("SIM")
    SIM("SIM", "Sim"),
    @JsonProperty("NAO")
    NAO("NAO", "Não");

    //@JsonValue
    private final String id;
    private final String descricao;

    ShouldReturn(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    //@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ShouldReturn get(String id) {
        return Arrays.stream(values()).filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }


}
